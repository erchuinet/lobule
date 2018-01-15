package com.erchuinet.service.app.dao;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public interface CompanyMapper {

	public Map<String, Object> queryCompanyInfo(Map map);

	public List<Map<String, Object>> queryCompanyRecruit(Map map);

	public Map<String, Object> commentForCompany(Map map);

	public void companyUserRegister(Map map);

	/**
	 * 企业用户登录
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, Object> companyUserLogin(Map map);

	// 修改登录密码
	public boolean updateCompanyPassWord(Map map);

	// 修改登录手机
	public boolean updateLoginPhone(Map map);

	public int checkPhone(Map map);
}
