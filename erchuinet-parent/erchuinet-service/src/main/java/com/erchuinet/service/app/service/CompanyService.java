package com.erchuinet.service.app.service;

import java.util.List;
import java.util.Map;
@SuppressWarnings({"rawtypes"})
public interface CompanyService {

	/**
	 * 查询企业信息
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, Object> queryCompanyInfo(Map map);

	public List<Map<String, Object>> queryCompanyRecruit(Map map);
	/**
	 * 评价企业
	 * @param map
	 * @return
	 */
	public Map<String, Object> commentForCompany(Map map);
    /**
     * 企业用户注册
     * @param map
     * @return
     */
	public Map<String, Object> companyUserRegister(Map map);
	/**
	 * 企业用户登录
	 * @param map
	 * @return
	 */
	public Map<String, Object> companyUserLogin(Map map);
	
	//修改登录密码
	public boolean updateCompanyPassWord(Map map);
	
	//修改登录手机
	public String updateLoginPhone(Map map);
	
	
	
}
