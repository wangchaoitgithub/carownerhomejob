package com.zhiguan.carownerhomecrm.service.phone.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.phone.PhoneSmsRecord;
import com.zhiguan.carownerhomecrm.mapper.phone.PhoneSmsRecordMapper;
import com.zhiguan.carownerhomecrm.service.phone.PhoneSmsRecordService;

public class PhoneSmsRecordServiceImpl implements PhoneSmsRecordService {
	private static Logger logger = LogManager.getLogger("service");
	
	@Resource
	PhoneSmsRecordMapper phoneSmsRecordMapper;


	public PageUtils pageListAll(PhoneSmsRecord enitty) {
		List<PhoneSmsRecord> list = phoneSmsRecordMapper.pageListAll(enitty);
		int totalCount = phoneSmsRecordMapper.pageListAllSize(enitty);
		PageUtils result = new PageUtils(list, totalCount, enitty.getLimit(), enitty.getCurrPage());
		return result;
	}
}
