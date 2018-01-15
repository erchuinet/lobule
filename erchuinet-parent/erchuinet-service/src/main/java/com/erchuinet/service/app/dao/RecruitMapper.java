package com.erchuinet.service.app.dao;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 *
 */
@SuppressWarnings({ "rawtypes" })
public interface RecruitMapper {
	/**
	 * 查询城市
	 * 
	 * @param
	 * @return
	 */
	public List<Map<String, Object>> getCity();
	
	public List selectTrade();

	/**
	 * 职位
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getPostition();

	/**
	 * 广告
	 */
	public List<Map<String, Object>> getAdvertising();

	/**
	 * 列表
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
	 * @author Administrator 收藏岗位
	 */
	public void favoriteSave(Map map);
	
	public int checkFavorite(Map map);

	/**
	 * 投诉
	 * 
	 * @param map
	 */
	public void saveComplaint(Map map);

	/**
	 * 申请
	 * 
	 * @param map
	 */
	public void apply(Map map);

	/**
	 * @param map
	 */
	public void publishPartTimeJob(Map map);

	public List<Map<String, Object>> getCityList();

	public List<Map<String, Object>> recommendUserList(Map map);

	public void savePosition(Map map);

	// 企业发布的招聘
	public List<Map<String, Object>> queryCompanyRecruitList(Map map);

	// 取消招聘
	public void deleteRecruit(Map map);

    //查询兼职证据列表
	public List<Map<String, Object>> queryJobProvedList(Map map);
}
