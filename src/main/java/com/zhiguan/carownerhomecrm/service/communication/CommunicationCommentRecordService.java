package com.zhiguan.carownerhomecrm.service.communication;

import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.communication.CommunicationCommentRecord;

public interface CommunicationCommentRecordService {
    PageUtils pageListAll(CommunicationCommentRecord entity);
}
