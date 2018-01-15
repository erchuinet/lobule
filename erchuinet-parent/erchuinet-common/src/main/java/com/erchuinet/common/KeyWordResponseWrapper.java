package com.erchuinet.common;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public final class KeyWordResponseWrapper extends HttpServletResponseWrapper{
	
	

	private CharArrayWriter cw = new CharArrayWriter();
	 
	 public KeyWordResponseWrapper(HttpServletResponse response) {
	  super(response);
	 }
	 @Override
	 public PrintWriter getWriter() throws IOException {
	  return new PrintWriter(cw);
	 }
	 public CharArrayWriter getCw() {
	  return cw;
	 }
	 
	/*@Override
	public Map getParameterMap() {
		super.getContextPath();
		Map<String,String[]> map = super.getParameterMap();
		if(!map.isEmpty()){
			Set<String> keySet = map.keySet();
			Iterator<String> keyIt = keySet.iterator();
			while(keyIt.hasNext()){
				String key = keyIt.next();
//				String value = map.get(key)[0];
//				map.get(key)[0] = this.replaceParam(value);
				//这边实现对整个数组的判断。
				String[] values=map.get(key);
				for(int i=0;i<values.length;i++){
					map.get(key)[i]=this.replaceParam(values[i]);
				}
			}
		}
		return map;
	}*/

	


	
	
}
