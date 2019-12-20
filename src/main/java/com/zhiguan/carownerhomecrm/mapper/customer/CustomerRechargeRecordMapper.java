package com.zhiguan.carownerhomecrm.mapper.customer;

import java.util.List;

import com.zhiguan.carownerhomecrm.domain.customer.CustomerRechargeRecord;

public interface CustomerRechargeRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerRechargeRecord record);

    int insertSelective(CustomerRechargeRecord record);

    CustomerRechargeRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerRechargeRecord record);

    int updateByPrimaryKey(CustomerRechargeRecord record);
    
    List<CustomerRechargeRecord> pageListAll(CustomerRechargeRecord enitty);
    
    int pageListAllSize(CustomerRechargeRecord enitty);
}