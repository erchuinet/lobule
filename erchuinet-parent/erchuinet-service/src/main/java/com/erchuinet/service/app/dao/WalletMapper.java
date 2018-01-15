package com.erchuinet.service.app.dao;

import java.util.List;
import java.util.Map;

public interface WalletMapper {
  
	/**
     * 查看个人钱包余额
     * @param map
     * @return
     */
	public Map<String, Object> viewWalletBlance(Map<String, Object> map);
    /**
     * 个人钱包收入/支出记录列表
     * @param map
     * @return
     */
	public List<Map<String, Object>> queryWalletIncomeOrWithdrawDepositList(Map<String, Object> map);
	
	
	public void settingPaymentCode(Map<String, Object> map);
	
	
	public void  bindingAlipayOrWebchat(Map<String, Object> map);
	
	
	public Map<String, Object> viewCompanyWalletBlance(Map<String, Object> map);
	
	/**
	 * 修改密码
	 * @return
	 */
	public void updateCompanySecurityCode(Map<String, Object> map);
}
