package com.zhiguan.carownerhomecrm.mapper.base;

import com.zhiguan.carownerhomecrm.domain.base.BaseCustomerInfo;

public interface BaseCustomerInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BaseCustomerInfo record);

    int insertSelective(BaseCustomerInfo record);

    BaseCustomerInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BaseCustomerInfo record);

    int updateByPrimaryKey(BaseCustomerInfo record);
    
    BaseCustomerInfo  getCustomerInfo(String loginName);
}