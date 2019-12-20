package com.zhiguan.carownerhomecrm.service.membe.impl;

import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.membe.MembePayOrder;
import com.zhiguan.carownerhomecrm.mapper.membe.MembePayOrderMapper;
import com.zhiguan.carownerhomecrm.service.membe.MembePayOrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import java.util.List;

public class MembePayOrderServiceImpl implements MembePayOrderService {

    private static Logger logger = LogManager.getLogger("service");

    @Resource
    MembePayOrderMapper membePayOrderMapper;

    @Override
    public PageUtils pageListAll(MembePayOrder entity) {
        List<MembePayOrder> list = membePayOrderMapper.pageListAll(entity);
        int totalCount = membePayOrderMapper.pageListAllSize(entity);
        PageUtils result = new PageUtils(list, totalCount, entity.getLimit(), entity.getCurrPage());
        return result;
    }
}
