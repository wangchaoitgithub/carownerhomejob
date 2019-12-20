package com.zhiguan.carownerhomecrm.service.vice.impl;

import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.vice.VicePayNumber;
import com.zhiguan.carownerhomecrm.mapper.vice.VicePayNumberMapper;
import com.zhiguan.carownerhomecrm.service.vice.VicePayNumberService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import java.util.List;

public class VicePayNumberServiceImpl implements VicePayNumberService {

    private static Logger logger = LogManager.getLogger("service");

    @Resource
    VicePayNumberMapper vicePayNumberMapper;


    @Override
    public PageUtils pageListAll(VicePayNumber entity) {
        List<VicePayNumber> list = vicePayNumberMapper.pageListAll(entity);
        int totalCount = vicePayNumberMapper.pageListAllSize(entity);
        PageUtils result = new PageUtils(list, totalCount, entity.getLimit(), entity.getCurrPage());
        return result;
    }
}
