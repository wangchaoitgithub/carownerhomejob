package com.zhiguan.carownerhomecrm.service.vice;

import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.vice.ViceSeeRecord;

public interface ViceSeeRecordService {
    /*查询的所有信息*/
    PageUtils pageListAll(ViceSeeRecord entity);
}
