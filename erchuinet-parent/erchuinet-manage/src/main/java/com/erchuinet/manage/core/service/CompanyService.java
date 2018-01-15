package com.erchuinet.manage.core.service;

import com.erchuinet.vo.CompanyVo;
import com.erchuinet.vo.PageResult;

public interface CompanyService {
	public PageResult<CompanyVo> companyList(String params);
}
