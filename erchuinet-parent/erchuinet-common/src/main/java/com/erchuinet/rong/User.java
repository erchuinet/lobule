package com.erchuinet.rong;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

import com.erchuinet.rong.util.GsonUtil;
import com.erchuinet.rong.util.HostType;
import com.erchuinet.rong.util.HttpUtil;









public class User {

	private static final String UTF8 = "UTF-8";
	private String appKey;
	private String appSecret;
	
	public User(String appKey, String appSecret) {
		this.appKey = appKey;
		this.appSecret = appSecret;

	}
	
	
	/**
	 * 获取 Token 方法 
	 * 
	 * @param  userId:用户 Id，最大长度 64 字节.是用户在 App 中的唯一标识码，必须保证在同一个 App 内不重复，重复的用户 Id 将被当作是同一用户。（必传）
	 * @param  name:用户名称，最大长度 128 字节.用来在 Push 推送时显示用户的名称.用户名称，最大长度 128 字节.用来在 Push 推送时显示用户的名称。（必传）
	 * @param  portraitUri:用户头像 URI，最大长度 1024 字节.用来在 Push 推送时显示用户的头像。（必传）
	 *
	 * @return TokenResult
	 **/
	public TokenResult getToken(String userId, String name, String portraitUri) throws Exception {
		if (userId == null) {
			throw new IllegalArgumentException("Paramer 'userId' is required");
		}
		
		if (name == null) {
			throw new IllegalArgumentException("Paramer 'name' is required");
		}
		
		if (portraitUri == null) {
			throw new IllegalArgumentException("Paramer 'portraitUri' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&userId=").append(URLEncoder.encode(userId.toString(), UTF8));
	    sb.append("&name=").append(URLEncoder.encode(name.toString(), UTF8));
	    sb.append("&portraitUri=").append(URLEncoder.encode(portraitUri.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/user/getToken.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (TokenResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), TokenResult.class);
	}
	
	
	 
}