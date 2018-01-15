package com.erchuinet.service.log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.erchuinet.service.app.service.AccessLogService;
import com.erchuinet.service.app.service.UserService;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

@Component
@Aspect
@SuppressWarnings("rawtypes")
public class AccessLogAspect {
	public static Logger logger = Logger.getLogger(AccessLogAspect.class);
	@Autowired
	private AccessLogService accessLogService;

	@Autowired
	private UserService userService;

	@SuppressWarnings("unchecked")
	@AfterReturning(pointcut = "execution(* com.erchuinet.platform.core.controller.*Controller.*(..))", returning = "returnValue")
	public void afterReturn(JoinPoint point, Object returnValue) {
		if (returnValue != null) {
			compress(String.valueOf(returnValue));
		}
		Map map=getParmeters(point, returnValue);
		ExecutorService executor = Executors.newFixedThreadPool(1);  
		ListeningExecutorService listeningExecutor = MoreExecutors.listeningDecorator(executor);  
		ListenableFuture<String> lf = listeningExecutor.submit(new Callable<String>() {  
			@Override  
			public String call() throws Exception { 
				logger.info("记录请求日志....................");
				accessLogService.insertAccessLog(map);
				return "success";  
			}  
		});  
		Futures.addCallback(lf, new FutureCallback<String>() {  
			@Override  
			public void onFailure(Throwable t) {  
				logger.info("记录请求日志失败....................");  
				t.printStackTrace();
			}  
			
			@Override  
			public void onSuccess(String s) {  
				logger.info("记录请求日志完成...................."+s);
			}  
		});
	}
	@SuppressWarnings("unchecked")
	private Map getParmeters(JoinPoint point, Object returnValue) {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();
		String url = request.getRequestURL().toString();
		Object object[] = point.getArgs();
		logger.info("返回参数" + returnValue);
		Map map = new HashMap<>();
		map.put("funcname", url);
		map.put("art", "");
		map.put("loginuserid", 0);
		map.put("uniqueid", "");
		map.put("request_data", Arrays.toString(point.getArgs()));
		map.put("response_data", returnValue);
		map.put("remote_addr", getIp(request));
		map.put("server_addr", request.getServletPath());
		map.put("request_time_float", "");
		map.put("request_method", point.getSignature().getName());
		map.put("remote_port", "");
		map.put("server_port", request.getServerPort());
		map.put("request_uri", "");
		map.put("threadid", 0);
		if (object != null && object.length > 0&& object[0].getClass() == String.class) {
			String parmas =null;
			if(object.length==2) {
				 parmas = String.valueOf(object[1]);
					if (parmas != null && !"".equals(parmas)) {
						JSONObject jsonObject = JSONObject.parseObject(parmas);
						map.put("uniqueid", jsonObject.getString("UUID"));
						map.put("request_uri", jsonObject.getString("personORcompany"));
						if (jsonObject.containsKey("userid")) {
							map.put("loginuserid", jsonObject.getString("userid"));
						}
					}

			}else {
				parmas=Arrays.toString(object);
				if (parmas != null && !"".equals(parmas)) {
					JSONArray jsonObject = JSONArray.parseArray(parmas);
					map.put("uniqueid", jsonObject.getJSONObject(0).getString("UUID"));
					map.put("request_uri", jsonObject.getJSONObject(0).getString("personORcompany"));
					if (jsonObject.getJSONObject(0).containsKey("userid")) {
						map.put("loginuserid", jsonObject.getJSONObject(0).getString("userid"));
					}
				}
			}
			
			logger.debug("参数"+parmas);
		}
		return map;
	}
	public String compress(String str) {
		if (null == str || str.length() <= 0) {
			return str;
		}
		// 创建一个新的 byte 数组输出流
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		// 使用默认缓冲区大小创建新的输出流
		GZIPOutputStream gzip;
		try {
			gzip = new GZIPOutputStream(out);
			// 将 b.length 个字节写入此输出流
			gzip.write(str.getBytes("UTF-8"));
			gzip.close();
			return out.toString("UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	private static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}

	@SuppressWarnings("unchecked")
	@Around("execution(* com.erchuinet.platform.core.controller.*.*(..))")
	public Object process(ProceedingJoinPoint point) throws Throwable {
		// 访问目标方法的参数：
		Object[] args = point.getArgs();
		if (args != null && args.length > 0 && args[0].getClass() == String.class) {
			args[0] = URLDecoder.decode(args[0].toString(), "utf-8");
			logger.info("请求参数:" + args[0]);
			// 验证账户状态
			String params = String.valueOf(args[0]);
			if (params != null && !"".equals(params) && params.length() > 0) {
				JSONObject jsonObject = JSONObject.parseObject(params);
				JSONObject vo = new JSONObject();
				if (jsonObject.containsKey("userid")) {
					String userid = jsonObject.getString("userid");
					try {
						Map map = new HashMap<>();
						map.put("userid", userid);
						map = userService.queryUserInfoById(map);
						if (map != null && map.size() > 0) {
							String state = map.get("state").toString();
							if (!"0".equals(state)) {
								vo.put("data", jsonObject);
								vo.put("retcode", "400");
								vo.put("retmsg", "你的账号异常,请联系管理员");
								return vo.toString();
							}
						}

					} catch (Exception e) {
						e.printStackTrace();
						vo.put("data", jsonObject);
						vo.put("retcode", "500");
						vo.put("retmsg", "出错了");
						return vo.toString();
					}
				}
			}
		}

		return point.proceed(args);

	}
}
