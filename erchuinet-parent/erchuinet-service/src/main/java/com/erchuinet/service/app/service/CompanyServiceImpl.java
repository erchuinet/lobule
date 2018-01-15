package com.erchuinet.service.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.erchuinet.common.ModelVo;
import com.erchuinet.service.app.dao.CompanyMapper;
@SuppressWarnings("rawtypes")
@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	CompanyMapper companyMapper;

	@Override
	public Map<String, Object> queryCompanyInfo(Map map) {
		return companyMapper.queryCompanyInfo(map);
	}

	@Override
	public List<Map<String, Object>> queryCompanyRecruit(Map map) {
		return companyMapper.queryCompanyRecruit(map);
	}

	@Override
	public Map<String, Object> commentForCompany(Map map) {
		return companyMapper.commentForCompany(map);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> companyUserRegister(Map map) {

		companyMapper.companyUserRegister(map);
		return map;
	}

	@Override
	public Map<String, Object> companyUserLogin(Map map) {

		return companyMapper.companyUserLogin(map);
	}

	@Override
	public boolean updateCompanyPassWord(Map map) {
		try {
			companyMapper.updateCompanyPassWord(map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String updateLoginPhone(Map map) {
		int cnt = companyMapper.checkPhone(map);
		ModelVo vo = new ModelVo();
		if (cnt>0) {
			vo.setRetcode("300");
			vo.setRetmsg("手机号已注册");
		}else {
			try {
				companyMapper.updateLoginPhone(map);
				vo.setRetcode("200");
				vo.setRetmsg("修改成功");
				
			} catch (Exception e) {
				e.printStackTrace();
				vo.setRetcode("500");
				vo.setRetmsg("出错了");
			}
			
		}
		return JSONObject.toJSON(vo).toString();
	}
}
