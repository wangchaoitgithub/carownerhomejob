package com.zhiguan.carownerhomecrm.service.org.impl;


import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.org.OrgBaseInfo;
import com.zhiguan.carownerhomecrm.mapper.org.OrgBaseInfoMapper;
import com.zhiguan.carownerhomecrm.service.org.OrgBaseInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import java.util.List;

public class OrgBaseInfoServiceImpl implements OrgBaseInfoService {

    private static Logger logger = LogManager.getLogger("service");

    @Resource
    OrgBaseInfoMapper orgBaseInfoMapper;


    @Override
    public PageUtils pageListAll(OrgBaseInfo entity) {
        List<OrgBaseInfo> list = orgBaseInfoMapper.pageListAll(entity);
        int totalCount = orgBaseInfoMapper.pageListAllSize(entity);
        PageUtils result = new PageUtils(list, totalCount, entity.getLimit(), entity.getCurrPage());
        return result;
    }
}
