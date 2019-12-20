package com.zhiguan.carownerhomecrm.service.customer.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.customer.CustomerRechargeRecord;
import com.zhiguan.carownerhomecrm.mapper.customer.CustomerRechargeRecordMapper;
import com.zhiguan.carownerhomecrm.service.customer.CustomerRechargeRecordService;

public class CustomerRechargeRecordServiceImpl implements CustomerRechargeRecordService {
	private static Logger logger = LogManager.getLogger("service");
	
	@Resource
	CustomerRechargeRecordMapper customerRechargeRecordMapper;


	public PageUtils pageListAll(CustomerRechargeRecord enitty) {
		List<CustomerRechargeRecord> list = customerRechargeRecordMapper.pageListAll(enitty);
		int totalCount = customerRechargeRecordMapper.pageListAllSize(enitty);
		PageUtils result = new PageUtils(list, totalCount, enitty.getLimit(), enitty.getCurrPage());
		return result;
	}
}
