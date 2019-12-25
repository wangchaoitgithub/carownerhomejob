package com.zhiguan.carownerhomecrm.mapper.org;

import com.zhiguan.carownerhomecrm.domain.org.OrgBaseInfo;

import java.util.List;

public interface OrgBaseInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrgBaseInfo record);

    int insertSelective(OrgBaseInfo record);

    OrgBaseInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrgBaseInfo record);

    int updateByPrimaryKey(OrgBaseInfo record);

    /*查询组织信息*/
    List<OrgBaseInfo> pageListAll(OrgBaseInfo enitty);

    /*查询的组织信息条数*/
    int pageListAllSize(OrgBaseInfo enitty);

    List<OrgBaseInfo> selectInfo();
}