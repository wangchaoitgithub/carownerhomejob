package com.zhiguan.carownerhomecrm.service.vice.impl;

import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.vice.ViceSeeRecord;
import com.zhiguan.carownerhomecrm.mapper.vice.ViceSeeRecordMapper;
import com.zhiguan.carownerhomecrm.service.vice.ViceSeeRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import java.util.List;

public class ViceSeeRecordServiceImpl implements ViceSeeRecordService {
    private static Logger logger = LogManager.getLogger("service");

    @Resource
    ViceSeeRecordMapper viceSeeRecordMapper;


    @Override
    public PageUtils pageListAll(ViceSeeRecord entity) {
        List<ViceSeeRecord> list = viceSeeRecordMapper.pageListAll(entity);
        int totalCount = viceSeeRecordMapper.pageListAllSize(entity);
        PageUtils result = new PageUtils(list, totalCount, entity.getLimit(), entity.getCurrPage());
        return result;
    }
}