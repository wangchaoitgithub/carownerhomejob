package com.zhiguan.carownerhomecrm.service.order.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.order.OrderBalanceRecord;
import com.zhiguan.carownerhomecrm.mapper.order.OrderBalanceRecordMapper;
import com.zhiguan.carownerhomecrm.service.order.OrderBalanceRecordService;

public class OrderBalanceRecordServiceImpl implements OrderBalanceRecordService {
	private static Logger logger = LogManager.getLogger("service");
	
	@Resource
	OrderBalanceRecordMapper orderBalanceRecordMapper;


	public PageUtils pageListAll(OrderBalanceRecord enitty) {
		List<OrderBalanceRecord> list = orderBalanceRecordMapper.pageListAll(enitty);
		int totalCount = orderBalanceRecordMapper.pageListAllSize(enitty);
		PageUtils result = new PageUtils(list, totalCount, enitty.getLimit(), enitty.getCurrPage());
		return result;
	}
}
