package com.erchuinet.service.app.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erchuinet.service.app.dao.AccessLogMapper;



@Service
public class AccessLogServiceImpl implements AccessLogService {
    @Autowired
    AccessLogMapper accessLog; 
	
	@Override
	public void insertAccessLog(Map<String, Object> map) {
		accessLog.insertAccessLog(map);
	}

}
