package com.erchuinet.service.filter;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erchuinet.common.KeyWordRequestWrapper;
import com.erchuinet.common.KeyWordResponseWrapper;
import com.erchuinet.common.PropertiesUtil;





public class KeyWordFilter implements Filter{
	private FilterConfig filterConfig;
	
	public static HashMap keyMap = null;
	public static String path;
	public void destroy() {
		this.filterConfig = null; 
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		if(keyMap == null){
				keyMap = (HashMap)PropertiesUtil.readProperties(path);
		}
		if(req.getMethod().equals("POST")){
			//过滤服务器端的特殊字符
			  response.setCharacterEncoding("utf-8"); 
			  KeyWordResponseWrapper rs = new KeyWordResponseWrapper((HttpServletResponse)response);
			  chain.doFilter(new KeyWordRequestWrapper(req,keyMap), rs);
			  //得到response输出内容
			  String output = rs.getCw().toString();
			  //遍历所有敏感词
			  for (Object oj :keyMap.keySet()) {
			   String key = (String)oj;
			   //替换敏感词
			   output = output.replace(key, keyMap.get(key).toString());
			  }
			  //通过原来的response输出内容
			  response.getWriter().print(output);
			
		}else{
			chain.doFilter(request, response);
		}
		
	}
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig=filterConfig;
		String keyWordPath = filterConfig.getInitParameter("key");
		path = filterConfig.getServletContext().getRealPath(keyWordPath);
	}
	

}
