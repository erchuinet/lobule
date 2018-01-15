package com.erchuinet.manage.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.erchuinet.common.Config;

import com.erchuinet.vo.CompanyVo;
import com.erchuinet.vo.PageResult;

@SuppressWarnings("unchecked")
@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private RestTemplate template;

	@Override
	public PageResult<CompanyVo> companyList(String params) {
		return template.postForObject(Config.SERVICE_SERVER + "recruit/companylist.do", params, PageResult.class);
	}

}
