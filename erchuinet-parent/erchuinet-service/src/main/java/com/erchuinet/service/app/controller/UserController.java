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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.erchuinet.common.BaseController;
import com.erchuinet.common.FileVo;
import com.erchuinet.common.JsonUtil;
import com.erchuinet.common.MD5;
import com.erchuinet.common.ModelVo;
import com.erchuinet.service.app.service.UserService;


@SuppressWarnings({ "unchecked", "rawtypes" })
@RequestMapping("users")
@Controller
public class UserController extends BaseController {

	Logger logger = Logger.getLogger(getClass());

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/userLogin.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String userLogin(@RequestBody String params) {
		ModelVo vo = new ModelVo();
		if (params == null || "".equals(params)) {
			vo.setRetcode("100");
			vo.setRetmsg("参数为空");
			return JSONObject.toJSONString(vo);
		}
		logger.info("请求参数:" + params);
		try {
			Map map = JsonUtil.jsonCovertToMap(params);
				logger.info("json参数:" + map.toString());
				if (null == map.get("phone")) {
					vo.setRetcode("304");
					vo.setRetmsg("账号不能填写为空");
					return JSONObject.toJSONString(vo);
				}
				Map<String, Object> ls = userService.userLogin(map);
				//密码登录  查询无结果
				if (ls!=null&&null==ls.get("state")&&map.get("loginStyle").toString().equals("1") ){
					vo.setRetcode("302");
					vo.setRetmsg("账号或者密码错误");
					return JSONObject.toJSONString(vo);
				//验证码登录查询无结果
				}else if (ls!=null&&null==ls.get("state")&&map.get("loginStyle").toString().equals("2")) {
					vo.setRetcode("307");
					vo.setRetmsg("该手机号未注册,请先注册");
					return JSONObject.toJSONString(vo);
				//有结果
				}else {
					//账号被冻结
					if(ls.get("state").equals("1")){
						vo.setRetmsg("该账号已被冻结,不能登录");
						vo.setRetcode("307");	
						return JSONObject.toJSONString(vo);
					}
					ls.remove("code");
					vo.setData(ls);
					vo.setRetmsg("登录成功");
					vo.setRetcode("200");
					String result = JSONObject.toJSONString(vo);
					logger.info("返回参数:" + result);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			vo.setRetmsg("出错了");
			vo.setRetcode("500");
		}
		return JSONObject.toJSONString(vo);
	}

	// 注册/找回密码验证码
	@RequestMapping(value = "/sendModifyMsg.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String sendModifyMsg(@RequestBody String params) {
		ModelVo vo = new ModelVo();
		if (params == null || "".equals(params)) {
			vo.setRetcode("100");
			vo.setRetmsg("参数为空");
			return JSONObject.toJSONString(vo);
		}
		logger.info("请求参数:" + params);
		JSONObject object = JSONObject.parseObject(params);
		String phone = object.getString("phone");
		logger.info("json参数:" + object);
		if (null == phone) {
			vo.setRetcode("304");
			vo.setRetmsg("账号不能为空");
			return JSONObject.toJSONString(vo);
		}
		Map map = new HashMap<String, Object>();
		map.put("phone", phone);
		map.put("flag", object.getString("flag"));
		return userService.sendModifyMsg(map);

	}

	@RequestMapping(value = "/modifyUserPwd.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String modifyUserPwd(@RequestBody String params) {
		ModelVo vo = new ModelVo();
		if (params == null || "".equals(params)) {
			vo.setRetcode("100");
			vo.setRetmsg("参数为空");
			return JSONObject.toJSONString(vo);
		}
		logger.info("请求参数:" + params);
		try {
			JSONObject object = JSONObject.parseObject(params);
			String phone = object.getString("phone");
			String pwd = MD5.GetMD5Code(object.getString("newPwd"));
			logger.info("json参数:" + object);
			if (null == phone || null == pwd) {
				vo.setRetcode("304");
				vo.setRetmsg("账号不能为空");
				return JSONObject.toJSONString(vo);
			}
			Map map = new HashMap<String, Object>();
			map.put("phone", phone);
			map.put("pwd", pwd);
			if (userService.saveUserPwd(map)) {
				vo.setRetmsg("密码修改成功");
				vo.setRetcode("200");
				String result = JSONObject.toJSONString(vo);
				logger.info("返回参数:" + result);
			} else {
				vo.setRetmsg("服务器错误");
				vo.setRetcode("500");
				return JSONObject.toJSONString(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(vo);
	}

	/**
	 * 个人用户注册
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/userRegister.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String userRegister(@RequestBody String params) {
		ModelVo vo = new ModelVo();
		if (params == null || "".equals(params)) {
			vo.setRetcode("100");
			vo.setRetmsg("参数为空");
			return JSONObject.toJSONString(vo);
		}
		logger.info("请求参数:" + params);
		JSONObject object = JSONObject.parseObject(params);
		String phone = object.getString("phone");
		String pwd = object.getString("pwd");
		logger.info("json:" + object);

		Map map = new HashMap<String, Object>();
		map.put("phone", phone);
		map.put("pwd", MD5.GetMD5Code(pwd));
		map.put("result", "1");

		return userService.saveUserRegister(map);
	}

	/**
	 * 编辑个人信息
	 * 
	 * @param params
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/completeUserInfo.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String completeUserInfo(@RequestBody String params, MultipartHttpServletRequest req) {
		ModelVo vo = new ModelVo();
		if (params == null || "".equals(params)) {
			vo.setRetcode("100");
			vo.setRetmsg("参数为空");
			return JSONObject.toJSONString(vo);
		}
		try {
			// JSONObject object =JSONObject.parseObject(params);
			List<FileVo> files = this.upLoadFile(req, "photo", "");
			Map map = JsonUtil.jsonCovertToMap(params);
			logger.info("参数:" + map);
			if (files.size() > 0) {
				for (int i = 0; i < files.size(); i++) {
					map.put("photo" + (i + 1), files.get(i).getPath());
				}
			}
			userService.completeUserInfo(map);
			vo.setRetcode("200");
			vo.setRetmsg("注册成功");
		} catch (Exception e) {
			vo.setRetmsg("出错了");
			vo.setRetcode("500");
		}
		return JSONObject.toJSONString(vo);
	}

	/**
	 * 我的报名
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/myRecruitList.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String myRecruitList(@RequestBody String params) {
		ModelVo vo = new ModelVo();
		if (params == null || "".equals(params)) {
			vo.setRetcode("100");
			vo.setRetmsg("参数为空");
			return JSONObject.toJSONString(vo);
		}
		try {
			// Map map = JsonUtil.jsonCovertToMap(params);
			JSONObject jsonObject = JSONObject.parseObject(params);
			// logger.info("参数:"+map);
			Map map = new HashMap<String, Object>();
			map.put("lon", jsonObject.getFloat("lon"));// 定位用户的经度
			map.put("lat", jsonObject.getFloat("lat"));// 定位用户的纬度
			map.put("page", (jsonObject.getInteger("page") - 1) * 10);
			map.put("pageSize", 10);
			map.put("state", jsonObject.getString("state"));
			map.put("userid", jsonObject.getString("userid"));
			List<Map<String, Object>> ls = userService.myRecruitList(map);
			if (ls == null || ls.size() == 0) {
				vo.setRetcode("400");
				vo.setRetmsg("没有查询到数据");
			} else {
				vo.setData(ls);
				vo.setRetcode("200");
				vo.setRetmsg("查询完成");
			}

		} catch (Exception e) {
			e.printStackTrace();
			vo.setRetmsg("出错了");
			vo.setRetcode("500");
		}
		return JSONObject.toJSONString(vo);
	}

	/**
	 * 我的收藏
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/myCollectList.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String myCollectList(@RequestBody String params) {
		ModelVo vo = new ModelVo();
		if (params == null || "".equals(params)) {
			vo.setRetcode("100");
			vo.setRetmsg("参数为空");
			return JSONObject.toJSONString(vo);
		}
		try {
			JSONObject jsonObject = JSONObject.parseObject(params);
			Map map = new HashMap<String, Object>();
			map.put("lon", jsonObject.getFloat("lon"));// 定位用户的经度
			map.put("lat", jsonObject.getFloat("lat"));// 定位用户的纬度
			map.put("page", (jsonObject.getInteger("page") - 1) * 10);
			map.put("pageSize", 10);
			map.put("userid", jsonObject.getString("userid"));

			List<Map<String, Object>> ls = userService.myCollectList(map);
			if (ls == null || ls.size() == 0) {
				vo.setRetcode("400");
				vo.setRetmsg("没有查询到数据");
			} else {
				vo.setData(ls);
				vo.setRetcode("200");
				vo.setRetmsg("查询完成");
			}

		} catch (Exception e) {
			e.printStackTrace();
			vo.setRetmsg("出错了");
			vo.setRetcode("500");
		}
		return JSONObject.toJSONString(vo);
	}

	/**
	 * 个人求职设置
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/recruitSetting.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String recruitSetting(@RequestBody String params) {

		ModelVo vo = new ModelVo();
		if (params == null || "".equals(params)) {
			vo.setRetcode("100");
			vo.setRetmsg("参数为空");
			return JSONObject.toJSONString(vo);
		}
		try {
			JSONObject jsonObject = JSONObject.parseObject(params);
			Map map = new HashMap<String, Object>();
			map.put("area", jsonObject.getFloat("area"));
			map.put("industry", jsonObject.getFloat("industry"));
			map.put("fond", jsonObject.getString("fond"));
			map.put("userid", jsonObject.getString("userid"));
			map.put("result", "1");
			map = userService.recruitSetting(map);
			if (map.get("result").toString().equals("1")) {
				vo.setRetcode("200");
				vo.setRetmsg("设置成功");
			} else {
				vo.setRetcode("300");
				vo.setRetmsg("设置失败");
			}

		} catch (Exception e) {
			e.printStackTrace();
			vo.setRetmsg("出错了");
			vo.setRetcode("500");
		}
		return JSONObject.toJSONString(vo);
	}

	/**
	 * 修改登录密码
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/updateUserPwd.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String updateUserPwd(@RequestBody String params) {

		ModelVo vo = new ModelVo();
		if (params == null || "".equals(params)) {
			vo.setRetcode("100");
			vo.setRetmsg("参数为空");
			return JSONObject.toJSONString(vo);
		}
		try {
			JSONObject jsonObject = JSONObject.parseObject(params);
			Map map = new HashMap<String, Object>();
			map.put("userid", jsonObject.getInteger("userid"));
			map.put("pwd", MD5.GetMD5Code(jsonObject.getString("pwd")));
			boolean bool = userService.updateUserPwd(map);
			if (bool) {
				vo.setRetcode("200");
				vo.setRetmsg("修改成功");
			} else {
				vo.setRetcode("300");
				vo.setRetmsg("修改失败");
			}

		} catch (Exception e) {
			e.printStackTrace();
			vo.setRetmsg("出错了");
			vo.setRetcode("500");
		}
		return JSONObject.toJSONString(vo);
	}

	/**
	 * 修改登录手机号
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/updateUserPhone.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String updateUserPhone(@RequestBody String params) {

		ModelVo vo = new ModelVo();
		if (params == null || "".equals(params)) {
			vo.setRetcode("100");
			vo.setRetmsg("参数为空");
			return JSONObject.toJSONString(vo);
		}
		try {
			JSONObject jsonObject = JSONObject.parseObject(params);
			Map map = new HashMap<String, Object>();
			map.put("userid", jsonObject.getInteger("userid"));
			map.put("phone", jsonObject.getString("phone"));
			boolean falg = userService.checkPhoneExists(map);
			if (falg) {
				vo.setRetcode("300");
				vo.setRetmsg("手机号已存在");
				return JSONObject.toJSONString(vo);
			}
			boolean bool = userService.updateUserPhone(map);
			if (bool) {
				vo.setRetcode("200");
				vo.setRetmsg("修改成功");
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

	/**
	 * 重置登录密码
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/replacementUserPwd.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String replacementUserPwd(@RequestBody String params) {

		ModelVo vo = new ModelVo();
		if (params == null || "".equals(params)) {
			vo.setRetcode("100");
			vo.setRetmsg("参数为空");
			return JSONObject.toJSONString(vo);
		}
		try {
			JSONObject jsonObject = JSONObject.parseObject(params);
			Map map = new HashMap<String, Object>();
			map.put("phone", jsonObject.getString("phone"));
			map.put("pwd", MD5.GetMD5Code(jsonObject.getString("pwd")));
			boolean bool = userService.forgetPwd(map);
			if (bool) {
				map=userService.queryUserInfo(map);
				vo.setData(map);
				vo.setRetcode("200");
				vo.setRetmsg("修改成功");
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

	/**
	 * 我的兼职凭证列表
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/myCertificateList.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String myCertificateList(@RequestBody String params) {

		ModelVo vo = new ModelVo();
		if (params == null || "".equals(params)) {
			vo.setRetcode("100");
			vo.setRetmsg("参数为空");
			return JSONObject.toJSONString(vo);
		}
		try {
			JSONObject jsonObject = JSONObject.parseObject(params);
			Map map = new HashMap<String, Object>();
			map.put("userid", jsonObject.getInteger("userid"));
			List<Map<String, Object>> ls = userService.myCertificateList(map);
			if (ls != null && ls.size() > 0) {
				vo.setRetcode("200");
				vo.setRetmsg("查询成功");
				vo.setData(ls);
			} else {
				vo.setRetcode("300");
				vo.setRetmsg("没有数据");
			}

		} catch (Exception e) {
			vo.setRetmsg("出错了");
			vo.setRetcode("500");
		}
		return JSONObject.toJSONString(vo);
	}

	/**
	 * 凭证明细
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/myCertificateDetail.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String myCertificateDetail(@RequestBody String params) {
		ModelVo vo = new ModelVo();
		if (params == null || "".equals(params)) {
			vo.setRetcode("100");
			vo.setRetmsg("参数为空");
			return JSONObject.toJSONString(vo);
		}
		try {
			JSONObject jsonObject = JSONObject.parseObject(params);
			Map map = new HashMap<String, Object>();
			map.put("certificateid", jsonObject.getInteger("certificateid"));
			Map<String, Object> ls = userService.myCertificateDetail(map);
			if (ls != null && ls.size() > 0) {
				vo.setRetcode("200");
				vo.setRetmsg("查询成功");
				vo.setData(ls);
			} else {
				vo.setRetcode("300");
				vo.setRetmsg("没有数据");
			}

		} catch (Exception e) {
			vo.setRetmsg("出错了");
			vo.setRetcode("500");
		}
		return JSONObject.toJSONString(vo);
	}

	/**
	 * 查看用户明细
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/viewUserInfo.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String viewUserInfo(@RequestBody String params) {
		ModelVo vo = new ModelVo();
		if (params == null || "".equals(params)) {
			vo.setRetcode("100");
			vo.setRetmsg("参数为空");
			return JSONObject.toJSONString(vo);
		}
		try {
			JSONObject jsonObject = JSONObject.parseObject(params);
			Map map = new HashMap<String, Object>();
			map.put("userid", jsonObject.getInteger("userid"));
			Map<String, Object> ls = userService.viewUserInfo(map);
			if (ls != null && ls.size() > 0) {
				vo.setRetcode("200");
				vo.setRetmsg("查询成功");
				vo.setData(ls);
			} else {
				vo.setRetcode("300");
				vo.setRetmsg("没有数据");
			}

		} catch (Exception e) {
			vo.setRetmsg("出错了");
			vo.setRetcode("500");
		}
		return JSONObject.toJSONString(vo);
	}

	// 企业发布的所有招聘信息列表
	@RequestMapping(value = "hostQueryWordList.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String hostQueryWordList(@RequestBody String params) {
		ModelVo vo = new ModelVo();
		try {
			logger.info("请求参数" + params);
			if (params == null) {
				vo.setRetcode("100");
				vo.setRetmsg("参数为空");
				return JSONObject.toJSONString(vo);
			}
			// JSONObject jsonObject = JSONObject.parseObject(params);
			List<Map<String, Object>> ls = userService.hostQueryWordList();
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
	@RequestMapping(value = "threeLogin.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String threeLogin(@RequestBody String params) {
		ModelVo vo = new ModelVo();
		logger.info("请求参数" + params);
		if (params == null) {
			vo.setRetcode("100");
			vo.setRetmsg("参数为空");
			return JSONObject.toJSONString(vo);
		}
		Map map = JsonUtil.jsonCovertToMap(params);
		return userService.saveThreeUserLogin(map);
	}
}
