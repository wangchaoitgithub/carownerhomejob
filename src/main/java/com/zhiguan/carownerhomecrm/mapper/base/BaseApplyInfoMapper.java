package com.zhiguan.carownerhomecrm.mapper.base;

import java.util.List;

import com.zhiguan.carownerhomecrm.domain.base.BaseApplyInfo;

public interface BaseApplyInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BaseApplyInfo record);

    int insertSelective(BaseApplyInfo record);

    BaseApplyInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BaseApplyInfo record);

    int updateByPrimaryKey(BaseApplyInfo record);
    
    List<BaseApplyInfo> pageListAll(BaseApplyInfo enitty);
    
    int pageListAllSize(BaseApplyInfo enitty);
    
    List<BaseApplyInfo> selectInfo(Long customerId);
    
    int update(Long id, Long customerId, String desc, String lastOperator);
}