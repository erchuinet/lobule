package com.erchuinet.common;


/**
 * 共通常量类
 *
 */
public class Constants {

	/** 服务路径 */
	public static final String SERVICE_BASE_URL = Config.SERVICE_SERVER;
	public static final String SUCCESS="Success";
	
	public static final String FAILURE="Failure";
	
	/** 提示 **/
	public static enum TASKCONTENT{
	     CHANGECONTRACT("您提交申请订单，请等待系统确认"), 
	     ENTRY("您提交了订单，请等待系统确认"), 
	     RENEWPROCESS("您提交了订单，请等待系统确认"), 
	     RENOVATIONPROCESS("您提交订单，请等待系统确认"),
	     RENTPROCESS("您提交了订单，请等待系统确认"),
	     PROPERTYCOMPLAINT("您提交了，请等待系统确认"),
	     ESTATEREPAIR("您申请，请等待系统确认"),
	     SUCCESS("已完成"),
	     SETOUT("请耐心等待");
	     
	    private String content ;
	     
	    private TASKCONTENT( String content ){
	        this.content = content ;
	    }

		public String getContent() {
			return content;
		}
	}
}
