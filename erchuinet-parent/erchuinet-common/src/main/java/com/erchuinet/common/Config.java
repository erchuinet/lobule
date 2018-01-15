package com.erchuinet.common;

import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 系统共通配置
 *
 */
public class Config {

	/**
	 * Service服务器地址
	 */
	public static final String SERVICE_SERVER;
	/**
	 * 文件上传基础路径
	 */
	public static final String FILE_UPLOAD_BASEPATH;

	public static final String appKey;
	public static   final String appSecret;
	/**
	 * 系统参数配置Holder
	 */
	public static Properties config = null;

	public static final Logger logger = LoggerFactory.getLogger(Config.class);
	
	static {
		Resource resource = new ClassPathResource("config.properties");
		try {
			config = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			logger.error("初始化配置失败，请检查config.properties文件", e);
		}
		
		//文件上传基础路径
		SERVICE_SERVER = config.getProperty("SERVICE_SERVER");
		//文件上传基础路径
		FILE_UPLOAD_BASEPATH = config.getProperty("FILE_UPLOAD_BASEPATH");
		appKey= config.getProperty("appKey");
		appSecret = config.getProperty("appSecret");
	}

	public static String getConfigValue(String key) {
		return config == null ? null : config.getProperty(key);
	}

	/**
	 * 字符串全数字校验
	 * @param value
	 * @return
	 */
	public static boolean isAllNumber(String value){
        return checkRegex("^[0-9]+$", value);
	}

	/**
	 * 验证正则表达式
	 * @param regex
	 * @param value
	 * @return
	 * @author
	 */
	public static boolean checkRegex(String regex,String value){
		if (isEmpty(value))
			return true;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

	public static boolean isEmpty(String str){
		if(str == null || str.trim().length()==0){
			return true;
		}
		return false;
	}
}
