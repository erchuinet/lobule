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
import com.erchuinet.common.ModelVo;
import com.erchuinet.service.app.service.WalletService;

@SuppressWarnings({"unchecked","rawtypes"})
@RequestMapping("wallet")
@Controller
public class WalletController {
	public Logger logger = Logger.getLogger(getClass());
	
    @Autowired
    private WalletService walletService;
    /**
     * 个人钱包余额
     * @param params
     * @return
     */
	@RequestMapping(value="/viewWalletBlance.do",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String viewWalletBlance(@RequestBody String params) {
		
		ModelVo vo = new ModelVo(); 
		if(params==null||"".equals(params)) {
			vo.setRetcode("100");
			vo.setRetmsg("参数为空");
			return JSONObject.toJSONString(vo);
		}
		try {
			JSONObject jsonObject = JSONObject.parseObject(params);
			Map map = new HashMap<String, Object>();
			map.put("userid", jsonObject.getFloat("userid"));
			map = walletService.viewWalletBlance(map);
				vo.setRetcode("200");
				vo.setRetmsg("查询成功");
			    vo.setData(map);
			
		} catch (Exception e) {
			vo.setRetmsg("出错了");
			vo.setRetcode("500");
		}
	    return JSONObject.toJSONString(vo);
	}
	/**
	 * 收入记录列表
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/queryWalletIncomeOrWithdrawDepositList.do",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryWalletIncomeOrWithdrawDepositList(@RequestBody String params) {
		
		ModelVo vo = new ModelVo(); 
		if(params==null||"".equals(params)) {
			vo.setRetcode("100");
			vo.setRetmsg("参数为空");
			return JSONObject.toJSONString(vo);
		}
		try {
			JSONObject jsonObject = JSONObject.parseObject(params);
			Map map = new HashMap<String, Object>();
			map.put("userid", jsonObject.getFloat("userid"));
			map.put("mode", jsonObject.getFloat("mode")); //0 收入/1提现
			List<Map<String, Object>> record = walletService.queryWalletIncomeOrWithdrawDepositList(map);
			if(record!=null && record.size()>0) {
				vo.setRetcode("200");
				vo.setRetmsg("查询完成");
				vo.setData(record);
			}else {
				vo.setRetcode("300");
				vo.setRetmsg("没有交易记录");
			 }
			
		} catch (Exception e) {
			vo.setRetmsg("出错了");
			vo.setRetcode("500");
		}
	    return JSONObject.toJSONString(vo);
	}
	/**
	 * 设置支付密码
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/settingPaymentCode.do",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String settingPaymentCode(@RequestBody String params) {
		ModelVo vo = new ModelVo(); 
		if(params==null||"".equals(params)) {
			vo.setRetcode("100");
			vo.setRetmsg("参数为空");
			return JSONObject.toJSONString(vo);
		}
		try {
			JSONObject jsonObject = JSONObject.parseObject(params);
			Map map = new HashMap<String, Object>();
			map.put("userid", jsonObject.getFloat("userid"));
			map.put("securitycode", jsonObject.getFloat("securitycode")); 
			boolean bool = walletService.settingPaymentCode(map);
			if(bool) {
				vo.setRetcode("200");
				vo.setRetmsg("设置成功");
			}else {
				vo.setRetcode("300");
				vo.setRetmsg("设置失败");
			}
		} catch (Exception e) {
			vo.setRetmsg("出错了");
			vo.setRetcode("500");
		}
	    return JSONObject.toJSONString(vo);
	}
	/**
	 * 绑定支付宝/微信
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/bindingAlipayOrWebchat.do",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String bindingAlipayOrWebchat(@RequestBody String params) {
		ModelVo vo = new ModelVo(); 
		if(params==null||"".equals(params)) {
			vo.setRetcode("100");
			vo.setRetmsg("参数为空");
			return JSONObject.toJSONString(vo);
		}
		try {
			JSONObject jsonObject = JSONObject.parseObject(params);
			Map map = new HashMap<String, Object>();
			map.put("userid", jsonObject.getFloat("userid"));
			map.put("alipay", jsonObject.getFloat("alipay")); 
			map.put("webchat", jsonObject.getFloat("webchat")); 
			boolean bool = walletService.bindingAlipayOrWebchat(map);
			if(bool) {
				vo.setRetcode("200");
				vo.setRetmsg("设置成功");
			}else {
				vo.setRetcode("300");
				vo.setRetmsg("设置失败");
			}
		} catch (Exception e) {
			vo.setRetmsg("出错了");
			vo.setRetcode("500");
		}
	    return JSONObject.toJSONString(vo);
	}
	//企业余额
	@RequestMapping(value="/viewCompanyWalletBlance.do",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String viewCompanyWalletBlance(@RequestBody String params) {
		ModelVo vo = new ModelVo(); 
		if(params==null||"".equals(params)) {
			vo.setRetcode("100");
			vo.setRetmsg("参数为空");
			return JSONObject.toJSONString(vo);
		}
		try {
			JSONObject jsonObject = JSONObject.parseObject(params);
			Map map = new HashMap<String, Object>();
			map.put("companyid", jsonObject.getInteger("companyid"));
			
			Map map2 = walletService.viewCompanyWalletBlance(map);
				vo.setRetcode("200");
				vo.setRetmsg("设置成功");
				vo.setData(map2);
			
		} catch (Exception e) {
			vo.setRetmsg("出错了");
			vo.setRetcode("500");
		}
	    return JSONObject.toJSONString(vo);
	}
	//企业修改支付密码
	
	@RequestMapping(value="/updateCompanySecurityCode.do",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String updateCompanySecurityCode(@RequestBody String params) {
		ModelVo vo = new ModelVo(); 
		if(params==null||"".equals(params)) {
			vo.setRetcode("100");
			vo.setRetmsg("参数为空");
			return JSONObject.toJSONString(vo);
		}
		try {
			JSONObject jsonObject = JSONObject.parseObject(params);
			Map map = new HashMap<String, Object>();
			map.put("companyid", jsonObject.getInteger("companyid"));
			map.put("securitycode", jsonObject.getInteger("securitycode"));
			boolean bool= walletService.updateCompanySecurityCode(map);
			if (bool) {
				vo.setRetcode("200");
				vo.setRetmsg("修改成功");
            
			}else {
				vo.setRetcode("300");
				vo.setRetmsg("修改失败");

			}
							
		} catch (Exception e) {
			vo.setRetmsg("出错了");
			vo.setRetcode("500");
		}
	    return JSONObject.toJSONString(vo);
	}
}
