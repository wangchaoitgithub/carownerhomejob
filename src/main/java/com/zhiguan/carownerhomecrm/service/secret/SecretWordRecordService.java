package com.zhiguan.carownerhomecrm.service.secret;

import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.secret.SecretWordRecord;

public interface SecretWordRecordService {
    PageUtils pageListAll(SecretWordRecord entity);

}
