package com.erchuinet.service.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

@SuppressWarnings("rawtypes")
public interface UserMapper {
	public Map userRegister(Map map);

	public void completeUserInfo(Map map);

	/**
	 * 登录查找用户是否存在
	 * 
	 * @param map
	 */
	int findUserIsExist(Map map);

	/**
	 * 密码登录
	 * 
	 * @param map
	 */
	Map<String, Object> loginByPwd(Map map);

	/**
	 * 保存密码
	 * 
	 * @param map
	 */
	Boolean saveUserPwd(Map map);
    
	public Map<String, Object> queryUserInfo(Map map);
	
	public List<Map<String, Object>> recruitSetting(Map map);

	// 我的兼职列表
	public List<Map<String, Object>> myRecruitList(Map map);

	// 我的求职列表
	List<Map<String, Object>> myCollectList(Map map);

	// 修改登录密码
	public void updateUserPwd(Map map);
    
	
	public int checkPhoneExists(Map map);
	
	// 修改登录手机号
	public void updateUserPhone(Map map);

	// 忘记密码
	public void forgetPwd(Map map);
	
	// 我的兼职凭证列表
	public List<Map<String, Object>> myCertificateList(Map map);

	// 兼职凭证明细
	public Map<String, Object> myCertificateDetail(Map map);
	
	public Map<String, Object> viewUserInfo(Map map);
    
	//热门词条
	public List<Map<String, Object>> hostQueryWordList();

	public Boolean saveToken(@Param("tokennum")String tokennum, @Param("userid") int userid);
	
	
	public  Map  threeUserLogin(Map map);
	
	
	public Map checkUser(Map map);
	
	public Map<String, Object> queryUserInfoById(Map map);
	
	public void bindingPhone(Map map);
}
