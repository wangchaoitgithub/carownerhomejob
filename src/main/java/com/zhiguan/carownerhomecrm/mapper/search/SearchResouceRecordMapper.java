package com.zhiguan.carownerhomecrm.mapper.search;

import com.zhiguan.carownerhomecrm.domain.search.SearchResouceRecord;

import java.util.List;

public interface SearchResouceRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SearchResouceRecord record);

    int insertSelective(SearchResouceRecord record);

    SearchResouceRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SearchResouceRecord record);

    int updateByPrimaryKey(SearchResouceRecord record);



    List<SearchResouceRecord> pageListAll(SearchResouceRecord enitty);

    int pageListAllSize(SearchResouceRecord enitty);
}