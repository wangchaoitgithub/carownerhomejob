package com.zhiguan.carownerhomecrm.service.process;

import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.process.ProcessBaseRecord;

public interface ProcessBaseRecordService {

    PageUtils pageListAll(ProcessBaseRecord entity);
}
