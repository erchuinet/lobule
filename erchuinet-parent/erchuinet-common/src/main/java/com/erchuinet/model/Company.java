package com.erchuinet.model;

import java.io.Serializable;

public class Company implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
      
	private Integer compayid;
	
	private String companyname;

	public Integer getCompayid() {
		return compayid;
	}

	public void setCompayid(Integer compayid) {
		this.compayid = compayid;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	
	
	
}
