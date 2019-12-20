package com.zhiguan.carownerhomecrm.mapper.order;

import java.util.List;

import com.zhiguan.carownerhomecrm.domain.order.OrderBalanceRecord;

public interface OrderBalanceRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderBalanceRecord record);

    int insertSelective(OrderBalanceRecord record);

    OrderBalanceRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderBalanceRecord record);

    int updateByPrimaryKey(OrderBalanceRecord record);


    List<OrderBalanceRecord> pageListAll(OrderBalanceRecord enitty);
    
    int pageListAllSize(OrderBalanceRecord enitty);
}