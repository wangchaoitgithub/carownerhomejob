package com.zhiguan.carownerhomecrm.service.phone;

import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.phone.PhoneSmsRecord;



public interface PhoneSmsRecordService {

	PageUtils pageListAll(PhoneSmsRecord entity);
}
