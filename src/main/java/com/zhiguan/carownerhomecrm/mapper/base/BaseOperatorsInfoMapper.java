package com.zhiguan.carownerhomecrm.mapper.base;

import java.util.List;

import com.zhiguan.carownerhomecrm.domain.base.BaseOperatorsInfo;

public interface BaseOperatorsInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BaseOperatorsInfo record);

    int insertSelective(BaseOperatorsInfo record);

    BaseOperatorsInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BaseOperatorsInfo record);

    int updateByPrimaryKey(BaseOperatorsInfo record);
    
    List<BaseOperatorsInfo> selectInfo();
}