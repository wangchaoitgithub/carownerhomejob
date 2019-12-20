package com.zhiguan.carownerhomecrm.service.base.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.base.BaseApplyInfo;
import com.zhiguan.carownerhomecrm.mapper.base.BaseApplyInfoMapper;
import com.zhiguan.carownerhomecrm.service.base.BaseApplyInfoService;

public class BaseApplyInfoServiceImpl implements BaseApplyInfoService {
	private static Logger logger = LogManager.getLogger("service");
	
	@Resource
	BaseApplyInfoMapper baseApplyInfoMapper;

	@Override
	public PageUtils pageListAll(BaseApplyInfo enitty) {
		List<BaseApplyInfo> list = baseApplyInfoMapper.pageListAll(enitty);
		int totalCount = baseApplyInfoMapper.pageListAllSize(enitty);
		PageUtils result = new PageUtils(list, totalCount, enitty.getLimit(), enitty.getCurrPage());
		return result;
	}
}
