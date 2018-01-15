package com.erchuinet.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;



@SuppressWarnings("rawtypes")
public class PropertiesUtil{
	
	public static Map readProperties(String src) {
		Properties props = new Properties();
		Map map = new HashMap();
		try {
//			Resource resource = new ClassPathResource("keyword.properties");
//				props = PropertiesLoaderUtils.loadProperties(resource);
		    File file=new File(src);
			InputStream in=new FileInputStream(file);
			props.load(in);
			Enumeration en = props.propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				String value = props.getProperty(key);
				map.put(key, value);//把properties文件中的key-value存放到一个map中
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String replaceCheck(Map map,String name) {
		Set<String> keys = map.keySet();
		Iterator<String> iter = keys.iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			String value = (String) map.get(key);
			if (name.contains(key)) {
				name=name.replace(key, value);//对于符合map中的key值实现替换功能
				
			}
		}
		return name;
	}

}
