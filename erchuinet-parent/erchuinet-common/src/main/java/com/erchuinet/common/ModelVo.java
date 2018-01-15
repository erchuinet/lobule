package com.erchuinet.common;

/**
 * 封装返回数据
 */
public class ModelVo {

	private String retcode = "200";
	private String retmsg = "操作成功";
	private Object data;
	
	public ModelVo(String retcode, String retmsg, Object data){
		this.retcode = retcode;
		this.retmsg = retmsg;
		this.data = data;
	}
	
	public ModelVo(String retcode, String retmsg){
		this.retcode = retcode;
		this.retmsg = retmsg;
	}
	
	public ModelVo(Object data){
		this.retmsg = "查询成功";
		this.data = data;
	}
	

	
	public ModelVo(){
		
	}

	public String getRetcode() {
		return retcode;
	}
	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}
	public String getRetmsg() {
		return retmsg;
	}
	public void setRetmsg(String retmsg) {
		this.retmsg = retmsg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	public ModelVo(String retmsg) {
		super();
		this.retmsg = retmsg;
	}


	
}
