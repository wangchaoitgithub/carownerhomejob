package com.zhiguan.carownerhomecrm.service.base.impl;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zhiguan.carownerhomecrm.domain.base.BaseCustomerInfo;
import com.zhiguan.carownerhomecrm.mapper.base.BaseCustomerInfoMapper;
import com.zhiguan.carownerhomecrm.service.base.BaseCustomerInfoService;

public class BaseCustomerInfoServiceImpl implements BaseCustomerInfoService {
	private static Logger logger = LogManager.getLogger("service");
	
	@Resource
	BaseCustomerInfoMapper baseCustomerInfoMapper;


	public BaseCustomerInfo getCustomerInfo(String loginName) {
		return baseCustomerInfoMapper.getCustomerInfo(loginName);
	}
}
