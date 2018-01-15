package com.erchuinet.service.app.service;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public interface RecruitService {

	public String getSelection();

	/**
	 * 首页查询招聘列表
	 * 
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> queryRecruitList(Map map);

	/**
	 * 查看招聘详情
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, Object> findRecruitDetail(Map map);

	/**
	 * 收藏职位
	 */
	public String favoriteSave(Map map);

	
	/**
	 * 岗位投诉
	 */
	public void saveComplaint(Map map);

	/**
	 * 报名
	 */

	public String apply(Map map);

	/**
	 * 发布招聘信息
	 */
	public boolean publishPartTimeJob(Map map);

	public String getCityList();

	// 岗位推荐求职者
	public List<Map<String, Object>> recommendUserList(Map map);

	public void savePosition(Map map);

	// 企业发布的招聘
	public List<Map<String, Object>> queryCompanyRecruitList(Map map);

	// 取消招聘
	public boolean deleteRecruit(Map map);

	//查询兼职列表
	public List<Map<String, Object>> queryJobProvedList(Map map);
	

}
