package com.zhiguan.carownerhomecrm.service.vice;

import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.vice.VicePayNumber;

public interface VicePayNumberService {
    /*查询的所有信息*/
    PageUtils pageListAll(VicePayNumber entity);
}
