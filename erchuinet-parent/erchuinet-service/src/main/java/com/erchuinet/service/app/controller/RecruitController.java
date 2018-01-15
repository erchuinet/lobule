package com.erchuinet.service.app.controller;

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

import com.alibaba.fastjson.JSONObject;
import com.erchuinet.common.JsonUtil;
import com.erchuinet.common.ModelVo;
import com.erchuinet.service.app.service.CompanyService;
import com.erchuinet.service.app.service.RecruitService;



@RequestMapping("recruit")
@Controller
@SuppressWarnings({ "unchecked", "rawtypes" })
public class RecruitController {
	Logger logger = Logger.getLogger(getClass());
	@Autowired
	RecruitService recruitService;

	@Autowired
	CompanyService companyService;

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
		return recruitService.getSelection();
	}

	/**
	 * 查询列表
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "queryRecruitList.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String queryRecruitList(@RequestBody String params) {
		ModelVo vo = new ModelVo();
		try {
			logger.info("请求参数" + params);
			if (params == null) {
				vo.setRetcode("100");
				vo.setRetmsg("参数为空");
				return JSONObject.toJSONString(vo);
			}
			JSONObject jsonObject = JSONObject.parseObject(params);
			String version = jsonObject.getString("version");
			if (version != null && "1.0".equals(version)) {
				Map map = new HashMap<String, Object>();
				map.put("ensure", jsonObject.getInteger("ensure"));
				map.put("search", jsonObject.getString("search"));// 关键字搜索
				map.put("location", jsonObject.getString("location"));// 定位城市名称
				map.put("positionid", jsonObject.getString("positionid"));// 选择岗位
				map.put("payments", jsonObject.getString("payments"));// 结算方式
				map.put("gender", jsonObject.getString("gender"));// 性别
				map.put("identitys", jsonObject.getString("identitys"));// 身份
				map.put("order", jsonObject.getString("order"));// 排序
				map.put("lon", jsonObject.getFloat("lon"));// 定位用户的经度
				map.put("lat", jsonObject.getFloat("lat"));// 定位用户的纬度
				map.put("page", (jsonObject.getInteger("page") - 1) * 10);
				map.put("pageSize", 10);
				List<Map<String, Object>> ls = recruitService.queryRecruitList(map);
				if (ls == null || ls.size() == 0) {
					vo.setRetcode("400");
					vo.setRetmsg("没有查询到数据");
				} else {
					vo.setData(ls);
					vo.setRetcode("200");
					vo.setRetmsg("查询完成");
				}
			}

		} catch (Exception e) {
			vo.setRetcode("500");
			vo.setRetmsg("出错了");
			e.printStackTrace();
		}

		logger.info("响应参数" + JSONObject.toJSONString(vo));
		return JSONObject.toJSONString(vo);
	}

	/**
	 * 投诉招聘信息
	 */
	@RequestMapping(value = "saveComplaint.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String saveComplaint(@RequestBody String params) {
		ModelVo vo = new ModelVo();
		try {
			logger.info("请求参数" + params);
			if (params == null) {
				vo.setRetcode("100");
				vo.setRetmsg("参数为空");
				return JSONObject.toJSONString(vo);
			}
			Map map = new HashMap<>();
			JSONObject jsonObject = JSONObject.parseObject(params);
			map.put("content", jsonObject.getString("content"));
			map.put("userid", jsonObject.getInteger("userid"));
			recruitService.saveComplaint(map);
			vo.setRetcode("200");
			vo.setRetmsg("提交成功");
		} catch (Exception e) {
			vo.setRetcode("500");
			vo.setRetmsg("出错了");
		}
		logger.info("结果" + JSONObject.toJSONString(vo));
		return JSONObject.toJSONString(vo);

	}

	/**
	 * 招聘报名
	 */
	@RequestMapping(value = "apply.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String apply(@RequestBody String params) {
		   ModelVo vo = new ModelVo();
			logger.info("请求参数" + params);
			if (params == null) {
				vo.setRetcode("100");
				vo.setRetmsg("参数为空");
				return JSONObject.toJSONString(vo);
			}
			Map map = new HashMap<>();
			JSONObject jsonObject = JSONObject.parseObject(params);
			map.put("content", "成功报名");
			map.put("userid", jsonObject.getInteger("userid"));
			map.put("recruitmentid", jsonObject.getInteger("recruitmentid"));
			map.put("workdate", jsonObject.getInteger("workdate"));
			map.put("states", "0");
			map.put("result", "1");
			return recruitService.apply(map);
		

	}

	/**
	 * 招聘明细
	 * 
	 * @return
	 */
	@RequestMapping(value = "findRecruitDetail.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String findRecruitDetail(@RequestBody String params) {
		ModelVo vo = new ModelVo();
		Map result = new HashMap<>();
		try {
			logger.info("请求参数" + params);
			if (params == null) {
				vo.setRetcode("100");
				vo.setRetmsg("参数为空");
				return JSONObject.toJSONString(vo);
			}
			Map map = new HashMap<>();
			JSONObject jsonObject = JSONObject.parseObject(params);
			map.put("recruitmentid", jsonObject.getInteger("recruitmentid"));
			Map recruitment = recruitService.findRecruitDetail(map);
			Map company = companyService.queryCompanyInfo(map);
			result.put("recruitment", recruitment);
			result.put("company", company);

			vo.setRetcode("200");
			vo.setRetmsg("查询成功");
			vo.setData(result);
		} catch (Exception e) {
			vo.setRetcode("500");
			vo.setRetmsg("出错了");
		}
		logger.info("结果" + JSONObject.toJSONString(vo));
		return JSONObject.toJSONString(vo);
	}

	/**
	 * 发布招聘信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "publishPartTimeJob.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String publishPartTimeJob(@RequestBody String params) {
		ModelVo vo = new ModelVo();
		try {
			logger.info("请求参数" + params);
			if (params == null) {
				vo.setRetcode("100");
				vo.setRetmsg("参数为空");
				return JSONObject.toJSONString(vo);
			}
			Map map = JsonUtil.jsonCovertToMap(params);
			map.put("states", "0");
			map.put("result", "1");
			logger.info("参数:" + map);
			boolean bool = recruitService.publishPartTimeJob(map);
			if(bool) {
				vo.setRetcode("200");
				vo.setRetmsg("发布成功");
				
			}else {
				vo.setRetcode("400");
				vo.setRetmsg("发布失败");

			}
		} catch (Exception e) {
			vo.setRetcode("500");
			vo.setRetmsg("出错了");
		}
		logger.info("结果" + JSONObject.toJSONString(vo));
		return JSONObject.toJSONString(vo);
	}

	/**
	 * 城市列表
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "cityList.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String cityList() {
		return recruitService.getCityList();
	}

	// 推荐用户列表

	@RequestMapping(value = "recommendUserList.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String recommendUserList(@RequestBody String params) {
		ModelVo vo = new ModelVo();
		try {
			logger.info("请求参数" + params);
			if (params == null) {
				vo.setRetcode("100");
				vo.setRetmsg("参数为空");
				return JSONObject.toJSONString(vo);
			}
			JSONObject jsonObject = JSONObject.parseObject(params);

			Map map = new HashMap<String, Object>();
			map.put("recruitmentid", jsonObject.getString("recruitmentid"));
			List<Map<String, Object>> ls = recruitService.recommendUserList(map);
			if (ls == null || ls.size() == 0) {
				vo.setRetcode("400");
				vo.setRetmsg("没有查询到数据");
			} else {
				vo.setData(ls);
				vo.setRetcode("200");
				vo.setRetmsg("查询完成");
			}

		} catch (Exception e) {
			vo.setRetcode("500");
			vo.setRetmsg("出错了");
			e.printStackTrace();
		}

		logger.info("响应参数" + JSONObject.toJSONString(vo));
		return JSONObject.toJSONString(vo);
	}

	/**
	 * 新增职位
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "savePosition.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String savePosition(@RequestBody String params) {
		ModelVo vo = new ModelVo();
		try {
			logger.info("请求参数" + params);
			if (params == null) {
				vo.setRetcode("100");
				vo.setRetmsg("参数为空");
				return JSONObject.toJSONString(vo);
			}
			JSONObject jsonObject = JSONObject.parseObject(params);

			Map map = new HashMap<String, Object>();
			map.put("jobname", jsonObject.getString("jobname"));
			map.put("parentid", jsonObject.getString("parentid"));
			recruitService.savePosition(map);
			vo.setRetcode("200");
			vo.setRetmsg("查询完成");

		} catch (Exception e) {
			vo.setRetcode("500");
			vo.setRetmsg("出错了");
			e.printStackTrace();
		}

		logger.info("响应参数" + JSONObject.toJSONString(vo));
		return JSONObject.toJSONString(vo);
	}

	// 企业发布的所有招聘信息列表
	@RequestMapping(value = "queryCompanyRecruitList.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String queryCompanyRecruitList(@RequestBody String params) {
		ModelVo vo = new ModelVo();
		try {
			logger.info("请求参数" + params);
			if (params == null) {
				vo.setRetcode("100");
				vo.setRetmsg("参数为空");
				return JSONObject.toJSONString(vo);
			}
			JSONObject jsonObject = JSONObject.parseObject(params);

			Map map = new HashMap<String, Object>();
			map.put("companyid", jsonObject.getString("companyid"));
			List<Map<String, Object>> ls = recruitService.queryCompanyRecruitList(map);
			if (ls != null && ls.size() > 0) {
				vo.setRetcode("200");
				vo.setRetmsg("查询完成");
				vo.setData(ls);
			} else {
				vo.setRetcode("400");
				vo.setRetmsg("没有数据");
			}
		} catch (Exception e) {
			vo.setRetcode("500");
			vo.setRetmsg("出错了");
			e.printStackTrace();
		}

		logger.info("响应参数" + JSONObject.toJSONString(vo));
		return JSONObject.toJSONString(vo);
	}

	// 取消招聘
	@RequestMapping(value = "deleteRecruit.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String deleteRecruit(@RequestBody String params) {
		ModelVo vo = new ModelVo();
		try {
			logger.info("请求参数" + params);
			if (params == null) {
				vo.setRetcode("100");
				vo.setRetmsg("参数为空");
				return JSONObject.toJSONString(vo);
			}
			JSONObject jsonObject = JSONObject.parseObject(params);
			Map map = new HashMap<String, Object>();
			map.put("recruitmentid", jsonObject.getString("recruitmentid"));
			boolean bool = recruitService.deleteRecruit(map);
			if (bool) {
				vo.setRetcode("200");
				vo.setRetmsg("删除成功");
			} else {
				vo.setRetcode("200");
				vo.setRetmsg("删除失败");
			}

		} catch (Exception e) {
			vo.setRetcode("500");
			vo.setRetmsg("出错了");
			e.printStackTrace();
		}

		logger.info("响应参数" + JSONObject.toJSONString(vo));
		return JSONObject.toJSONString(vo);
	}

	// 企业查看某个兼职的凭证
	@RequestMapping(value = "queryJobProved.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String queryJobProved(@RequestBody String params) {
		ModelVo vo = new ModelVo();
		try {
			logger.info("请求参数" + params);
			if (params == null) {
				vo.setRetcode("100");
				vo.setRetmsg("参数为空");
				return JSONObject.toJSONString(vo);
			}
			JSONObject jsonObject = JSONObject.parseObject(params);

			Map map = new HashMap<String, Object>();
			map.put("companyid", jsonObject.getString("companyid"));
			map.put("recruitmentid", jsonObject.getString("recruitmentid"));
			List<Map<String, Object>> ls = recruitService.queryJobProvedList(map);
			if (ls != null && ls.size() > 0) {
				vo.setRetcode("200");
				vo.setRetmsg("查询完成");
				vo.setData(ls);
			} else {
				vo.setRetcode("400");
				vo.setRetmsg("没有数据");
			}
		} catch (Exception e) {
			vo.setRetcode("500");
			vo.setRetmsg("出错了");
			e.printStackTrace();
		}

		logger.info("响应参数" + JSONObject.toJSONString(vo));
		return JSONObject.toJSONString(vo);
	}

	/**
	 * 收藏招聘
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "saveCollection.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String saveCollection(@RequestBody String params) {
		ModelVo vo = new ModelVo();
		logger.info("请求参数" + params);
		if (params == null) {
			vo.setRetcode("100");
			vo.setRetmsg("参数为空");
			return JSONObject.toJSONString(vo);
		}
		Map map = new HashMap<>();
		JSONObject jsonObject = JSONObject.parseObject(params);
		map.put("recruitmentid", jsonObject.getString("recruitmentid"));
		map.put("userid", jsonObject.getInteger("userid"));
		return recruitService.favoriteSave(map);
	}
}
