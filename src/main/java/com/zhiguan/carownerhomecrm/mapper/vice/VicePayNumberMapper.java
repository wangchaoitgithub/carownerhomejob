package com.zhiguan.carownerhomecrm.mapper.vice;

import com.zhiguan.carownerhomecrm.domain.vice.VicePayNumber;

import java.util.List;

public interface VicePayNumberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VicePayNumber record);

    int insertSelective(VicePayNumber record);

    VicePayNumber selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VicePayNumber record);

    int updateByPrimaryKey(VicePayNumber record);



    List<VicePayNumber> pageListAll(VicePayNumber enitty);


    int pageListAllSize(VicePayNumber enitty);
}