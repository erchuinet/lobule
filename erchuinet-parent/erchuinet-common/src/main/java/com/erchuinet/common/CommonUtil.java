package com.erchuinet.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CommonUtil{
     public List MapSort(Map<String,Object> map,String orderField,String order) {
    	 List<Map.Entry<String,Object>> list = new ArrayList<Map.Entry<String,Object>>(map.entrySet());
	        //然后通过比较器来实现排序
	        Collections.sort(list,new Comparator<Map.Entry<String,Object>>() {
	            //升序排序
	            public int compare(Entry<String, Object> o1,
	                    Entry<String, Object> o2) {
	            	if(orderField!=null&&!"".equals(orderField)) {
                       if(orderField.toUpperCase().equals("KEY")) {
                    	   if(order!=null&&!"".equals(order)) {
                    		   if(order.toUpperCase().equals("ASC")) {
                    			   return String.valueOf(o1.getKey()).compareTo(String.valueOf(o2.getKey()));
                    		   }else {
                    			   return String.valueOf(o2.getKey()).compareTo(String.valueOf(o1.getKey()));
                    		   }
                    	   }
                    	   return String.valueOf(o1.getKey()).compareTo(String.valueOf(o2.getKey()));
                       }else {
                    	   return String.valueOf(o1.getValue()).compareTo(String.valueOf(o2.getValue()));
                       }   	            		
	            	}
	            	return String.valueOf(o1.getKey()).compareTo(String.valueOf(o2.getKey()));
	            }
	            
	        });
	        
	        for(Entry<String, Object> mapping:list){ 
	               System.out.println(mapping.getKey()+":"+mapping.getValue()); 
	          }
	        return list;
     }
}
