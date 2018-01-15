package com.erchuinet.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

public class JsonUtil {
   public static Map<String,Object> jsonCovertToMap(String json){
    Map< String, Object> map =null;
   try {
	     map = new HashMap<>();
	    JSONObject jsonObject = JSONObject.parseObject(json);
	    Set<String> keys =jsonObject.keySet();
	    Iterator it =keys.iterator();
	    while (it.hasNext()) {
	    	String key = it.next().toString();
			map.put(key, jsonObject.getString(key));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
        return map; 	   
   }
}
