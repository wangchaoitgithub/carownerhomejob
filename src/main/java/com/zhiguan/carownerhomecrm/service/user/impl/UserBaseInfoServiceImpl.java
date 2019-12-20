package com.zhiguan.carownerhomecrm.service.user.impl;

import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.customer.CustomerRechargeRecord;
import com.zhiguan.carownerhomecrm.domain.user.UserBaseInfo;
import com.zhiguan.carownerhomecrm.mapper.user.UserBaseInfoMapper;
import com.zhiguan.carownerhomecrm.service.user.UserBaseInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import java.util.List;


public class UserBaseInfoServiceImpl implements UserBaseInfoService {

    private static Logger logger = LogManager.getLogger("service");

    @Resource
    UserBaseInfoMapper userBaseInfoMapper;


    @Override
    public PageUtils pageListAll(UserBaseInfo entity) {
        List<UserBaseInfo> list = userBaseInfoMapper.pageListAll(entity);
        int totalCount = userBaseInfoMapper.pageListAllSize(entity);
        PageUtils result = new PageUtils(list, totalCount, entity.getLimit(), entity.getCurrPage());
        return result;
    }
}
