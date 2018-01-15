package com.erchuinet.manage.core.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erchuinet.common.BaseController;
import com.erchuinet.manage.core.service.CompanyService;
import com.erchuinet.vo.CompanyVo;
import com.erchuinet.vo.PageResult;
@Controller
@RequestMapping("company")
public class CompanyController extends BaseController{
	Logger logger = Logger.getLogger(getClass());
	@Autowired
	private CompanyService companyService;
	@RequestMapping("companyList.do")
	@ResponseBody
	public PageResult<CompanyVo> companyList(@RequestBody String params){
		return companyService.companyList(params);
	}
}
