package com.zhiguan.carownerhomecrm.mapper.process;

import com.zhiguan.carownerhomecrm.domain.process.ProcessBaseRecord;

import java.util.List;

public interface ProcessBaseRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProcessBaseRecord record);

    int insertSelective(ProcessBaseRecord record);

    ProcessBaseRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProcessBaseRecord record);

    int updateByPrimaryKey(ProcessBaseRecord record);



    List<ProcessBaseRecord> pageListAll(ProcessBaseRecord enitty);

    int pageListAllSize(ProcessBaseRecord enitty);
}