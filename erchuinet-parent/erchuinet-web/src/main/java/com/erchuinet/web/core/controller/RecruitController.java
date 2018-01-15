package com.erchuinet.web.core.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erchuinet.web.core.service.RecruitService;


@RequestMapping("recruit")
@Controller
public class RecruitController {
	Logger logger = Logger.getLogger(getClass());
	@Autowired
	RecruitService recruitService;

	/**
	 * 查询条件
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getSelection.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getSelection(
//			@RequestHeader HttpHeaders headers,
			@RequestBody String params) {
//	     System.out.println("from parameter:" + headers.getFirst("code"));
//	     System.out.println("ContentType"+headers.getContentType());
//	     System.out.println(headers.getOrigin());
//	     System.out.println(headers.getExpires());
//	     System.out.println("Pragma"+headers.getPragma());
//	     System.out.println("Accept"+headers.getAccept());
//	     System.out.println("Charset"+headers.getAcceptCharset());
//	     System.out.println("RequestMethod"+headers.getAccessControlRequestMethod());
//	     System.out.println(headers.getAccessControlMaxAge());
//	     System.out.println(headers.getAccessControlAllowOrigin());
		return recruitService.getSelection(params);
	}

	
}
