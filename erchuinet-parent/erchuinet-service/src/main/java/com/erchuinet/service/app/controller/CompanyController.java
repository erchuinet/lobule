package com.erchuinet.service.app.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.erchuinet.common.BaseController;
import com.erchuinet.common.FileVo;
import com.erchuinet.common.JsonUtil;
import com.erchuinet.common.MD5;
import com.erchuinet.common.ModelVo;
import com.erchuinet.service.app.service.CompanyService;


@SuppressWarnings({ "unchecked", "rawtypes" })
@RequestMapping("company")
@Controller
public class CompanyController extends BaseController{
	Logger logger = Logger.getLogger(getClass());
	@Autowired
	CompanyService companyService;

	@RequestMapping(value = "/queryCompanyRecruit.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String queryCompanyRecruit(@RequestBody String params) {
		ModelVo vo = new ModelVo();
		try {
			logger.info("请求参数" + params);
			if (params == null) {
				vo.setRetcode("100");
				vo.setRetmsg("参数为空");
				return JSONObject.toJSONString(vo);
			}
			JSONObject jsonObject = JSONObject.parseObject(params);
			Map m = new HashMap<>();
			m.put("companyid", jsonObject.getInteger("companyid"));
			m.put("recruitmentid", jsonObject.getInteger("recruitmentid"));
			m.put("lon", jsonObject.getInteger("lon"));
			m.put("lat", jsonObject.getInteger("lat"));
			List<Map<String, Object>> map = companyService.queryCompanyRecruit(m);
			vo.setData(map);
			vo.setRetcode("200");
			vo.setRetmsg("查询成功");
		} catch (Exception e) {
			vo.setRetcode("500");
			vo.setRetmsg("出错了");
		}
		logger.info("结果" + JSONObject.toJSONString(vo));
		return JSONObject.toJSONString(vo);
	}

	/**
	 * 评价企业
	 *
	 */
	@RequestMapping(value = "/commentForCompany.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String commentForCompany(@RequestBody String params, MultipartHttpServletRequest req) {
		ModelVo vo = new ModelVo();
		if (params == null || "".equals(params)) {
			vo.setRetcode("100");
			vo.setRetmsg("参数为空");
			return JSONObject.toJSONString(vo);
		}
		try {
			Map map = JsonUtil.jsonCovertToMap(params);
			map.put("states", "0");
			map.put("result", "1");
			logger.info("参数:" + map);
			Map<String, Object> re = companyService.commentForCompany(map);
			if (re.get("result").toString().equals("1")) {
				vo.setRetcode("200");
				vo.setRetmsg("提交成功");
			} else if (re.get("result").toString().equals("2")) {
				vo.setRetcode("306");
				vo.setRetmsg("你已经提交过评论");
			} else {
				vo.setRetcode("300");
				vo.setRetmsg("提交失败");
			}
		} catch (Exception e) {
			vo.setRetmsg("出错了");
			vo.setRetcode("500");
		}
		return JSONObject.toJSONString(vo);
	}

	/**
	 * 企业注册
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/companyUserRegister.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String companyUserRegister(@RequestBody String params) {
		ModelVo vo = new ModelVo();
		if (params == null || "".equals(params)) {
			vo.setRetcode("100");
			vo.setRetmsg("参数为空");
			return JSONObject.toJSONString(vo);
		}
		try {
			JSONObject jsonObject = JSONObject.parseObject(params);
			Map map = new HashMap<>();
			map.put("u_phone", jsonObject.getString("phone"));
			map.put("u_pwd", jsonObject.getString("pwd"));
			map.put("u_companyname", jsonObject.getString("companyname"));
			map.put("u_registration", jsonObject.getString("registration"));
			map.put("result", "1");
			map = companyService.companyUserRegister(map);
			if (map.get("result").toString().equals("1")) {
				vo.setRetcode("200");
				vo.setRetmsg("提交成功");
			} else if (map.get("result").toString().equals("2")) {
				vo.setRetcode("402");
				vo.setRetmsg("手机号已注册");
			} else if (map.get("result").toString().equals("3")) {
				vo.setRetcode("403");
				vo.setRetmsg("企业已注册,联系注册企业将你加入子账号");
			} else {
				vo.setRetcode("401");
				vo.setRetmsg("注册失败");
			}
		} catch (Exception e) {
			vo.setRetmsg("出错了");
			vo.setRetcode("500");
		}
		return JSONObject.toJSONString(vo);
	}

	/**
	 * 企业登录
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/companyUserLogin.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String companyUserLogin(@RequestBody String params) {
		ModelVo vo = new ModelVo();
		if (params == null || "".equals(params)) {
			vo.setRetcode("100");
			vo.setRetmsg("参数为空");
			return JSONObject.toJSONString(vo);
		}
		try {
			JSONObject jsonObject = JSONObject.parseObject(params);
			Map map = new HashMap<>();
			map.put("phone", jsonObject.getString("phone"));
			String pwd = MD5.GetMD5Code(jsonObject.getString("pwd"));

			map = companyService.companyUserLogin(map);
			if (map != null && map.size() > 0) {
				String password = map.get("pwd").toString();
				if (pwd.equals(password)) {
					vo.setRetcode("200");
					vo.setRetmsg("登录成功");
					vo.setData(map);
				} else {
					vo.setRetcode("300");
					vo.setRetmsg("密码错误");
				}
			} else {
				vo.setRetcode("302");
				vo.setRetmsg("手机号不存在");
			}

		} catch (Exception e) {
			vo.setRetmsg("出错了");
			vo.setRetcode("500");
		}
		return JSONObject.toJSONString(vo);
	}
	// 修改登录密码

	@RequestMapping(value = "/updateCompanyPassWord.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String updateCompanyPassWord(@RequestBody String params) {
		ModelVo vo = new ModelVo();
		if (params == null || "".equals(params)) {
			vo.setRetcode("100");
			vo.setRetmsg("参数为空");
			return JSONObject.toJSONString(vo);
		}
		try {
			JSONObject jsonObject = JSONObject.parseObject(params);
			Map map = new HashMap<>();
			map.put("pwd", MD5.GetMD5Code(jsonObject.getString("pwd")));
			map.put("companyid", jsonObject.getString("companyid"));
			boolean bool = companyService.updateCompanyPassWord(map);

			if (bool) {
				vo.setRetcode("200");
				vo.setRetmsg("修改成功");
				vo.setData(map);
			} else {
				vo.setRetcode("300");
				vo.setRetmsg("修改失败");
			}

		} catch (Exception e) {
			vo.setRetmsg("出错了");
			vo.setRetcode("500");
		}
		return JSONObject.toJSONString(vo);
	}
	// 修改登录手机号

	@RequestMapping(value = "/updateLoginPhone.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String updateLoginPhone(@RequestBody String params) {
		ModelVo vo = new ModelVo();
		if (params == null || "".equals(params)) {
			vo.setRetcode("100");
			vo.setRetmsg("参数为空");
			return JSONObject.toJSONString(vo);
		}
		JSONObject jsonObject = JSONObject.parseObject(params);
		Map map = new HashMap<>();
		map.put("pwd", MD5.GetMD5Code(jsonObject.getString("pwd")));
		map.put("companyid", jsonObject.getString("companyid"));
		return companyService.updateLoginPhone(map);
	}
	//修改信息
	@RequestMapping(value = "/updateInfo.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String updateInfo(MultipartHttpServletRequest request) {
		try {
			List<FileVo> fileList =	this.upLoadFile(request, "file", "");
			for (int i = 0; i < fileList.size(); i++) {
				System.out.println(fileList.get(i).getPath());
			}
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
