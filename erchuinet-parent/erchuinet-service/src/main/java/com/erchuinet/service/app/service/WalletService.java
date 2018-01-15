package com.erchuinet.service.app.service;

import java.util.List;
import java.util.Map;

public interface WalletService {
    /**
     * 查看个人钱包余额
     * @param map
     * @return
     */
	public Map<String, Object> viewWalletBlance(Map<String, Object> map);
    /**
     * 个人钱包收入/提现记录列表
     * @param map
     * @return
     */
	public List<Map<String, Object>> queryWalletIncomeOrWithdrawDepositList(Map<String, Object> map);
	/**
	 * 设置/修改支付密码
	 * @param map
	 */
	public boolean settingPaymentCode(Map<String, Object> map);
	
	/**
	 * 绑定支付宝/微信
	 * @param map
	 * @return
	 */
	public boolean  bindingAlipayOrWebchat(Map<String, Object> map);
	
	/**
	 * 企业查看余额
	 * @return
	 */
	public Map<String, Object> viewCompanyWalletBlance(Map<String, Object> map);
	
	/**
	 * 修改密码
	 * @return
	 */
	public boolean updateCompanySecurityCode(Map<String, Object> map);
	
	
}
