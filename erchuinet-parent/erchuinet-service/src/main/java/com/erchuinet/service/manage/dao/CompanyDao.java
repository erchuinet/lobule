package com.erchuinet.service.manage.dao;

import java.util.List;
import java.util.Map;

import com.erchuinet.model.Company;
import com.erchuinet.vo.CompanyVo;
import com.erchuinet.vo.PageParam;



public interface CompanyDao {

	public List<CompanyVo> companylist(PageParam<Company> pageParam);
	
	public Integer getCompantCount(PageParam<Company> pageParam);
	
	
}
