package com.erchuinet.service.app.service;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public interface UserService {

	public Map<String, Object> userLogin(Map map);

	public String saveUserRegister(Map map);

	public void completeUserInfo(Map map);

	public String sendModifyMsg(Map map);
	
	public Map<String, Object> queryUserInfo(Map map);
	
	
	public Map<String, Object> queryUserInfoById(Map map);

	/**
	 * 保存密码
	 * 
	 * @param list
	 * @return
	 */
	public Boolean saveUserPwd(Map map);

	/**
	 * 求职设置
	 * 
	 * @return
	 */
	public Map<String, Object> recruitSetting(Map map);

	// 我的兼职列表
	public List<Map<String, Object>> myRecruitList(Map map);

	// 我的求职列表
	List<Map<String, Object>> myCollectList(Map map);

	// 判断修改的手机号是否已存在
	public boolean checkPhoneExists(Map map);

	// 修改登录密码
	public boolean updateUserPwd(Map map);
	
	//查看用户信息
	public Map<String, Object> viewUserInfo(Map map);

	// 修改登录手机号
	public boolean updateUserPhone(Map map);

	// 忘记密码
	public boolean forgetPwd(Map map);

	// 我的兼职凭证列表
	public List<Map<String, Object>> myCertificateList(Map map);

	// 兼职凭证明细
	public Map<String, Object> myCertificateDetail(Map map);

	//热门搜索岗位查询
	public List<Map<String, Object>> hostQueryWordList();

	public Boolean saveToken(String tokennum, int userid);

	public String saveThreeUserLogin(Map map);
}
