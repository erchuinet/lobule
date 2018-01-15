package com.erchuinet.service.manage.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erchuinet.service.manage.service.CompanyManageService;
import com.erchuinet.vo.CompanyVo;
import com.erchuinet.vo.PageResult;

@Controller
@RequestMapping("companymanage")
public class CompanyManageController {
    @Autowired
    private CompanyManageService companyManageService;
	
	@RequestMapping("companylist.do")
	@ResponseBody
	public PageResult<CompanyVo> companyList(@RequestBody String params){
		return companyManageService.companylist(params);
	}
}
