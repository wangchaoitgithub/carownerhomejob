package com.zhiguan.carownerhomecrm.mapper.membe;

import com.zhiguan.carownerhomecrm.domain.membe.MembePayOrder;
import com.zhiguan.carownerhomecrm.domain.org.OrgBaseInfo;

import java.util.List;

public interface MembePayOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MembePayOrder record);

    int insertSelective(MembePayOrder record);

    MembePayOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MembePayOrder record);

    int updateByPrimaryKey(MembePayOrder record);



    List<MembePayOrder> pageListAll(MembePayOrder enitty);


    int pageListAllSize(MembePayOrder enitty);
}