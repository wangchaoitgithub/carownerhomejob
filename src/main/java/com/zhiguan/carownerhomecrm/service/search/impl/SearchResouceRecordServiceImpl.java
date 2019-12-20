package com.zhiguan.carownerhomecrm.service.search.impl;

import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.search.SearchResouceRecord;
import com.zhiguan.carownerhomecrm.mapper.search.SearchResouceRecordMapper;
import com.zhiguan.carownerhomecrm.service.search.SearchResouceRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import java.util.List;

public class SearchResouceRecordServiceImpl implements SearchResouceRecordService {

    private static Logger logger = LogManager.getLogger("service");

    @Resource
    SearchResouceRecordMapper searchResouceRecordMapper;


    @Override
    public PageUtils pageListAll(SearchResouceRecord entity) {
        List<SearchResouceRecord> list = searchResouceRecordMapper.pageListAll(entity);
        int totalCount = searchResouceRecordMapper.pageListAllSize(entity);
        PageUtils result = new PageUtils(list, totalCount, entity.getLimit(), entity.getCurrPage());
        return result;
    }
}
