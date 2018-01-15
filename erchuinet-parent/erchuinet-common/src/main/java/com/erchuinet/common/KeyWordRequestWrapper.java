package com.erchuinet.common;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public final class KeyWordRequestWrapper extends HttpServletRequestWrapper{
	
	public Map keyMap;
	
	public KeyWordRequestWrapper(HttpServletRequest servletRequest,Map keyMap){
		super(servletRequest);
		this.keyMap = keyMap;
	}
	
	@Override
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
	}

	
/*	@Override
	public String[] getParameterValues(String name) {
		// TODO Auto-generated method stub
		String[] resources = super.getParameterValues(name); 
		if (resources == null) 
		return null; 
		int count = resources.length; 
		String[] results = new String[count]; 
		for (int i = 0; i < count; i++) { 
			results[i] = this.replaceParam(resources[i]); 
		} 
		return results; 
	}*/

	public String replaceParam(String name){
		return PropertiesUtil.replaceCheck(keyMap,name);
	}
}
