package com.erchuinet.manage.core.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erchuinet.manage.core.service.RecruitService;



@RequestMapping("recruit")
@Controller
public class RecruitController {
	Logger logger = Logger.getLogger(getClass());
	@Autowired
	RecruitService recruitService;

	/**	 * 查询条件
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getSelection.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getSelection(
			@RequestBody String params) {
		return recruitService.getSelection(params);
	}
	
}
