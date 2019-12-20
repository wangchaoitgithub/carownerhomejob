package com.zhiguan.carownerhomecrm.service.secret.impl;

import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.secret.SecretWordRecord;
import com.zhiguan.carownerhomecrm.mapper.secret.SecretWordRecordMapper;
import com.zhiguan.carownerhomecrm.service.secret.SecretWordRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import java.util.List;

public class SecretWordRecordServiceImpl implements SecretWordRecordService{

    private static Logger logger = LogManager.getLogger("service");

    @Resource
    SecretWordRecordMapper secretWordRecordMapper;


    @Override
    public PageUtils pageListAll(SecretWordRecord entity) {
        List<SecretWordRecord> list = secretWordRecordMapper.pageListAll(entity);
        int totalCount = secretWordRecordMapper.pageListAllSize(entity);
        PageUtils result = new PageUtils(list, totalCount, entity.getLimit(), entity.getCurrPage());
        return result;
    }

}
