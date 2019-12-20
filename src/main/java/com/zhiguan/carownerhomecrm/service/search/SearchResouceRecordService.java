package com.zhiguan.carownerhomecrm.service.search;

import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.search.SearchResouceRecord;

public interface SearchResouceRecordService {
    PageUtils pageListAll(SearchResouceRecord entity);
}
