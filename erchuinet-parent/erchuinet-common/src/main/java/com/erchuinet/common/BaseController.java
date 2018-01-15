package com.erchuinet.common;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.erchuinet.common.BinderDateFormat;
import com.erchuinet.common.Config;
import com.erchuinet.common.DateUtil;
import com.erchuinet.common.FileVo;







public class BaseController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 将一个 Map 对象转化为一个 JavaBean
	 * 
	 * @param type
	 *            要转化的类型
	 * @param map
	 *            包含属性值的 map
	 * @return 转化出来的 JavaBean 对象
	 * @throws IntrospectionException
	 *             如果分析类属性失败
	 * @throws IllegalAccessException
	 *             如果实例化 JavaBean 失败
	 * @throws InstantiationException
	 *             如果实例化 JavaBean 失败
	 * @throws InvocationTargetException
	 *             如果调用属性的 setter 方法失败
	 */
	@SuppressWarnings("rawtypes")
	public static Object convertMap(Class type, Map map)
			throws IntrospectionException, IllegalAccessException,
			InstantiationException, InvocationTargetException {
		BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
		Object obj = type.newInstance(); // 创建 JavaBean 对象

		// 给 JavaBean 对象的属性赋值
		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();

			if (map.containsKey(propertyName)) {
				// 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
				Object value = map.get(propertyName);

				Object[] args = new Object[1];

				/**
				 * 日期类型转换
				 */
				if (descriptor.getPropertyType().equals(Date.class)) {
					if (value == null) {
						args[0] = null;
					} else {
						args[0] = new Date(Long.parseLong(value.toString()));
					}

					descriptor.getWriteMethod().invoke(obj, args);
				}
				/**
				 * BigDecimal类型转换
				 */
				else if (descriptor.getPropertyType().equals(BigDecimal.class)) {
					if (value == null) {
						args[0] = null;
					} else {
						args[0] = BigDecimal.valueOf(Double.parseDouble(value
								.toString()));
					}

					descriptor.getWriteMethod().invoke(obj, args);
				}

				/**
				 * short类型转换
				 */
				else if (descriptor.getPropertyType().equals(Short.class)) {
					if (value == null) {
						args[0] = null;
					} else {
						args[0] = new Short(value.toString());
					}
					descriptor.getWriteMethod().invoke(obj, args);
				}
				/**
				 * 数组类型转换
				 */
				else if (descriptor.getPropertyType().isArray()) {
					if (value == null) {
						args[0] = null;
					} else {
						@SuppressWarnings("unchecked")
						ArrayList<String> list = (ArrayList<String>) value;
						// 只支持String数组
						args[0] = list.toArray(new String[list.size()]);
					}
					descriptor.getWriteMethod().invoke(obj, args);
				} else {
					args[0] = value;

					descriptor.getWriteMethod().invoke(obj, args);
				}
			}
		}
		return obj;
	}

	/**
	 * Set up a custom property editor for converting form inputs to real
	 * objects
	 * 
	 * @param request
	 *            the current request
	 * @param binder
	 *            the data binder
	 */
	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		BinderDateFormat dateFormat = new BinderDateFormat();
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(
				dateFormat, true));
	}

	protected ModelAndView exceptionSolve(String sysErrorMsg) {
		ModelAndView mv = new ModelAndView("redirect:error.do");
		mv.addObject("messageId", sysErrorMsg);
		return mv;
	}

	public Logger getLogger() {
		return logger;
	}

	@SuppressWarnings("rawtypes")
	public List<FileVo> upLoadFile(MultipartHttpServletRequest request,
			String keyId, String index) throws IllegalStateException, IOException {
		String dir =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
		List<FileVo> fileList = new ArrayList<FileVo>();
		String filePath = "";// 文件保存相对路径
		String fileName = "";
		String suffix = "";
		String upload_filePath="";
		String dateFolder = DateUtil.getSystemDateYYYYMM();// 当前日期
		// 如果文件夹不存在 则建立新文件夹
		new File(Config.FILE_UPLOAD_BASEPATH + dateFolder).mkdirs();
		// 根据key获取文件对象
		MultiValueMap files = request.getMultiFileMap();
		LinkedList list1 = (LinkedList) files.get(keyId);
		if (list1 != null) {
			for (int i = 0; i < list1.size(); i++) {
				FileVo fileInfo = new FileVo();
				MultipartFile tmp_file = (MultipartFile) list1.get(i);
				fileName = tmp_file.getOriginalFilename();
				suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
				filePath = dateFolder + "/" + DateUtil.getCurrentTimeStamp();
				if(StringUtils.isNotEmpty(index)) {
					filePath = filePath + "_" + index;
				}
				filePath = filePath + "_" + i + "." + suffix;
				upload_filePath = upload_filePath+ i + "." + suffix;
//				File upload_file=new File(Config.FILE_UPLOAD_BASEPATH + upload_filePath);
				try {
				
						tmp_file.transferTo(new File(Config.FILE_UPLOAD_BASEPATH + filePath));// 转存文件
						fileInfo.setName(tmp_file.getOriginalFilename());
						fileInfo.setSuffix(suffix);
						fileInfo.setPath(dir+"upload/"+filePath);
						fileInfo.setSize(tmp_file.getSize());
						fileList.add(fileInfo);
				} catch (Exception e) {
					System.out.println("BaseController >> upLoadFile()  判断是否为图片错误");
					e.printStackTrace();
				}
			}
		}
		return fileList;
	}
	
	
	/**
	 * 当一级节点选中的时候去除掉二级的节点值
	 * @param value
	 * @return
	 */
	public String romove2JiNode(String value) {
		if (value == null || StringUtils.isEmpty(value)) {
			return value;
		}

		String strValue = "";
		StringBuffer strValues = new StringBuffer();
		String[] values = value.split(",");
		for (String s : values) {
			if (!strValue.equals(s)) {
				if (s.length() == 4 || s.length() == 5) {
					if (StringUtils.isEmpty(strValue)
							|| strValue.length() == s.length()
							|| !strValue.equals(s.substring(0,
									strValue.length()))) {
						strValue = s;
						strValues.append(",").append(s);
					}
				} else {
					strValue = s;
					strValues.append(",").append(s);
				}
			}
		}

		return strValues.toString().substring(1);
	}

	/**
	 * 去重
	 * @param num
	 * @return
	 */
	public static String[] getDistinct(String num[]) {
        List<String> list = new java.util.ArrayList<String>();
        for (int i = 0; i < num.length; i++) {
            if (!list.contains(num[i])) {//如果list数组不包括num[i]中的值的话，就返回true。
                list.add(num[i]); //在list数组中加入num[i]的值。已经过滤过。
            }
        }
        return list.toArray(new String[0]); 
    }

	/**
	 * 去交集
	 * @param newArray
	 * @param oldArray
	 * @return
	 */
	public static String[] romoveSame(String newArray[],String oldArray[]) {
		List<String> newArraylist = new java.util.ArrayList<String>();
		List<String> newlist = Arrays.asList(newArray);
		for(int i=0;i<newlist.size();i++){
			newArraylist.add(newlist.get(i));
		}

		List<String> oldArraylist = new java.util.ArrayList<String>();
		List<String> oldlist = Arrays.asList(oldArray);
		for(int j=0;j<oldlist.size();j++){
			oldArraylist.add(oldlist.get(j));
		}
		
		newArraylist.removeAll(oldArraylist);

        return newArraylist.toArray(new String[0]); 
    }
	public  String decoderParams(String params) throws UnsupportedEncodingException {
		if(params==null||"".equals(params)) {
			return null;
		}
		return URLDecoder.decode(params, "utf-8");
	}
	
	@SuppressWarnings("deprecation")
	/*public static boolean sendMailMessage(String yhid, String email, String type){

	    List<A> dataList = new ArrayList<A>();	    
	    dataList.add(new A(email, DateUtil.getCurrentDate(), email, yhid, DateUtil.getCurrentTimeStamp()));
	    final String vars = convert(dataList);
		HttpClient httpclient = new org.apache.http.impl.client.DefaultHttpClient();
	    HttpPost httpost = new HttpPost(Config.MAIL_SERVER);

	    List<NameValuePair> param = new ArrayList<NameValuePair>();
	    param.add(new BasicNameValuePair("api_user", Config.MAIL_API_USER));
	    param.add(new BasicNameValuePair("api_key", Config.MAIL_API_KEY));
	    param.add(new BasicNameValuePair("substitution_vars", vars));
	    if(Constants.TEMPLATE_REG_ACTIVATE.equals(type)) {
	    	param.add(new BasicNameValuePair("template_invoke_name", Config.MAIL_TEMPLATE_REGISTER));	
	    }
	    if(Constants.TEMPLATE_PASSWORD_ACTIVATE.equals(type)) {
	    	param.add(new BasicNameValuePair("template_invoke_name", Config.MAIL_TEMPLATE_FINDPASS));	
	    }
	    param.add(new BasicNameValuePair("from", Config.MAIL_FORM_MAIL));
	    param.add(new BasicNameValuePair("fromname", Config.MAIL_FROM_NAME));
	    param.add(new BasicNameValuePair("subject", "请完成邮箱验证"));
	    //param.add(new BasicNameValuePair("html", "欢迎使用SendCloud"));
	    param.add(new BasicNameValuePair("use_maillist", "false"));

	    try
	    {
	    	httpost.setEntity(new UrlEncodedFormEntity(param, "UTF-8"));

		    HttpResponse response = httpclient.execute(httpost);
		    
		    if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
		    	 // 读取xml文档
		        String result = EntityUtils.toString(response.getEntity());
		        System.out.println(result);
		        return true;
		    } else {
		        return false;
		    }
	    } catch (Exception ex){
	    	return false;
	    }
	}*/
	
	
	
	
	public static String convert(List<A> dataList) {

	    JSONObject ret = new JSONObject();
	    JSONArray to = new JSONArray();

	    JSONArray date = new JSONArray();
	    JSONArray email = new JSONArray();
	    JSONArray key = new JSONArray();
	    JSONArray time = new JSONArray();

	    for (A a : dataList) {
	        to.add(a.address);
	        date.add(a.date);
	        email.add(a.email);
	        key.add(a.key);
	        time.add(a.time);
	    }

	    JSONObject sub = new JSONObject();
	    sub.put("%date%", date);
	    sub.put("%email%", email);
	    sub.put("%key%", key);
	    sub.put("%time%", time);

	    ret.put("to", to);
	    ret.put("sub", sub);

	    return ret.toString();
	}
	
	/**
	 * 计算分页数据
	 * @param page
	 * @param rows
	 * @return
	 */
	public Integer editPage(Integer page,Integer rows){
		return (page-1)*rows;
	}
	/**
	 * 计算总页数
	 */
	public Integer getPageCount(Integer rows,Integer count){
		int pageCount = 0;
		if(count%rows==0){
			pageCount = count/rows;
		}else{
			pageCount = (count/rows)+1;
		}
		return pageCount;
	}
}

class A {
    String address;
    String date;
    String email;
    String key;
    String time;

    A(String address, String date, String email, String key, String time) {
        this.address = address;
        this.date = date;
        this.email = email;
        this.key = key;
        this.time = time;
    }
}
