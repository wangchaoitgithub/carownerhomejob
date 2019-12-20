package com.zhiguan.carownerhomecrm.service.process.impl;

import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.process.ProcessBaseRecord;
import com.zhiguan.carownerhomecrm.mapper.process.ProcessBaseRecordMapper;
import com.zhiguan.carownerhomecrm.service.process.ProcessBaseRecordService;

import javax.annotation.Resource;
import java.util.List;

public class ProcessBaseRecordServiceImpl implements ProcessBaseRecordService {

    @Resource
    ProcessBaseRecordMapper processBaseRecordMapper;

    @Override
    public PageUtils pageListAll(ProcessBaseRecord entity) {
        List<ProcessBaseRecord> list = processBaseRecordMapper.pageListAll(entity);
        int totalCount = processBaseRecordMapper.pageListAllSize(entity);
        PageUtils result = new PageUtils(list, totalCount, entity.getLimit(), entity.getCurrPage());
        return result;
    }
}
