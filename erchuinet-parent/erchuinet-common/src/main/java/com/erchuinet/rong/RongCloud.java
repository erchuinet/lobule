
package com.erchuinet.rong;

import java.util.concurrent.ConcurrentHashMap;



public class RongCloud {

	private static ConcurrentHashMap<String, RongCloud> rongCloud = new ConcurrentHashMap<String,RongCloud>();
	
	public User user;
	

	private RongCloud(String appKey, String appSecret) {
		user = new User(appKey, appSecret);
		
	}

	public static RongCloud getInstance(String appKey, String appSecret) {
		if (null == rongCloud.get(appKey)) {
			rongCloud.putIfAbsent(appKey, new RongCloud(appKey, appSecret));
		}
		return rongCloud.get(appKey);
	}
	 
}