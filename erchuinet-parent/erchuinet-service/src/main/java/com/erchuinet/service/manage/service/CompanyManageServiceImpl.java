package com.erchuinet.service.manage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.erchuinet.model.Company;
import com.erchuinet.service.manage.dao.CompanyDao;
import com.erchuinet.vo.CompanyVo;
import com.erchuinet.vo.PageParam;
import com.erchuinet.vo.PageResult;

@Service
public class CompanyManageServiceImpl implements CompanyManageService{
    @Resource
    private  CompanyDao companyDao;
	@Override
	public PageResult<CompanyVo> companylist(String params) {
		JSONObject object = JSONObject.parseObject(params);
		Integer page  = object.getInteger("page");
		Integer rows = object.getInteger("rows");
		Company company = JSONObject.parseObject(params, Company.class);
		
		PageParam<Company> pageParam = new PageParam<Company>();
		List<CompanyVo> list = new ArrayList<CompanyVo>();
		//设置每页显示的条数
        pageParam.setPage(page);
        //开始的记录数
        pageParam.setRows(rows);
        //设置查询条件
        pageParam.setT(company);
        //创建结果集对象
        PageResult<CompanyVo> pageResult = new PageResult<CompanyVo>();
        //获取总记录数
        int total = companyDao.getCompantCount(pageParam);
        
        /* 防止分页 最后一页没有数据   */
        int totalPage=total/rows;
        int pageNumber = page/rows+1;
        if(total%rows!=0){
        	totalPage=totalPage+1;
        } 
        if(pageNumber>totalPage){
        	pageParam.setPage((totalPage-1)*rows);
        }
        /* 防止分页 最后一页没有数据   */
        
        if(total>0){
        	 list = companyDao.companylist(pageParam);
        }
        //设置总记录数
        pageResult.setTotal(total);
        //设置列表
        pageResult.setRows(list);
        //返回结果
		return pageResult;
	}
   
}
