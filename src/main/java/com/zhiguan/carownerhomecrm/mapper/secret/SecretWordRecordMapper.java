package com.zhiguan.carownerhomecrm.mapper.secret;

import com.zhiguan.carownerhomecrm.domain.secret.SecretWordRecord;

import java.util.List;

public interface SecretWordRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SecretWordRecord record);

    int insertSelective(SecretWordRecord record);

    SecretWordRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SecretWordRecord record);

    int updateByPrimaryKey(SecretWordRecord record);




    List<SecretWordRecord> pageListAll(SecretWordRecord enitty);

    int pageListAllSize(SecretWordRecord enitty);
}