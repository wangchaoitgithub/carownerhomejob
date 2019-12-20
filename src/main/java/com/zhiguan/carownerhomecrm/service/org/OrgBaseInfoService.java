package com.zhiguan.carownerhomecrm.service.org;

import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.org.OrgBaseInfo;

public interface OrgBaseInfoService {
    /*查询的组织信息*/
    PageUtils pageListAll(OrgBaseInfo entity);
}
