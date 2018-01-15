package com.erchuinet.manage.core.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.erchuinet.common.Config;


@Service
public class RecruitServiceImpl implements RecruitService {
	 @Autowired
	private RestTemplate template;

	@Override
	public String getSelection(String params) {
		System.out.println("1231231132132");
		return template.postForObject(Config.SERVICE_SERVER+"companymanage/getSelection.do", params, String.class);
	}

	


}
