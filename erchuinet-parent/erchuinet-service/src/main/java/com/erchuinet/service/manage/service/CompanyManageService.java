package com.erchuinet.service.manage.service;


import com.erchuinet.vo.CompanyVo;
import com.erchuinet.vo.PageResult;

public interface CompanyManageService {
	public PageResult<CompanyVo> companylist(String params);
}
