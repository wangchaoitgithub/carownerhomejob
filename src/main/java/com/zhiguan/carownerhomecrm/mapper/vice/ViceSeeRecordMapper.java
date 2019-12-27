package com.zhiguan.carownerhomecrm.mapper.vice;

import com.zhiguan.carownerhomecrm.domain.vice.ViceSeeRecord;

import java.util.List;

public interface ViceSeeRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ViceSeeRecord record);

    int insertSelective(ViceSeeRecord record);

    ViceSeeRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ViceSeeRecord record);

    int updateByPrimaryKey(ViceSeeRecord record);



    List<ViceSeeRecord> pageListAll(ViceSeeRecord enitty);


    int pageListAllSize(ViceSeeRecord enitty);

    List<ViceSeeRecord> selectInfo();
}