package com.erchuinet.service.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erchuinet.service.app.dao.WalletMapper;



@Service
public class WalletServiceImpl implements WalletService{
    @Autowired
    private WalletMapper walletMapper;
	@Override
	public Map<String, Object> viewWalletBlance(Map<String, Object> map) {
		return walletMapper.viewWalletBlance(map);
	}

	@Override
	public List<Map<String, Object>> queryWalletIncomeOrWithdrawDepositList(Map<String, Object> map) {
		return walletMapper.queryWalletIncomeOrWithdrawDepositList(map);
	}

	@Override
	public boolean settingPaymentCode(Map<String, Object> map) {
		try {
			walletMapper.settingPaymentCode(map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean bindingAlipayOrWebchat(Map<String, Object> map) {
		try {
			walletMapper.bindingAlipayOrWebchat(map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Map<String, Object> viewCompanyWalletBlance(Map<String, Object> map) {
		return walletMapper.viewCompanyWalletBlance(map);
	}

	@Override
	public boolean updateCompanySecurityCode(Map<String, Object> map) {
		try {
			walletMapper.updateCompanySecurityCode(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	

}
