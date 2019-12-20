package com.zhiguan.carownerhomecrm.mapper.phone;

import java.util.List;

import com.zhiguan.carownerhomecrm.domain.phone.PhoneSmsRecord;

public interface PhoneSmsRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PhoneSmsRecord record);

    int insertSelective(PhoneSmsRecord record);

    PhoneSmsRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PhoneSmsRecord record);

    int updateByPrimaryKey(PhoneSmsRecord record);
    
    List<PhoneSmsRecord> pageListAll(PhoneSmsRecord enitty);
    
    int pageListAllSize(PhoneSmsRecord enitty);
    
}