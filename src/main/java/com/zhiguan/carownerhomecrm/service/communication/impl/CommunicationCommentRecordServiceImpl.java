package com.zhiguan.carownerhomecrm.service.communication.impl;

import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.communication.CommunicationCommentRecord;
import com.zhiguan.carownerhomecrm.mapper.communication.CommunicationCommentRecordMapper;
import com.zhiguan.carownerhomecrm.service.communication.CommunicationCommentRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import java.util.List;

public class CommunicationCommentRecordServiceImpl implements CommunicationCommentRecordService {

    private static Logger logger = LogManager.getLogger("service");

    @Resource
    CommunicationCommentRecordMapper communicationCommentRecordMapper;


    public PageUtils pageListAll(CommunicationCommentRecord enitty) {
        List<CommunicationCommentRecord> list = communicationCommentRecordMapper.pageListAll(enitty);
        int totalCount = communicationCommentRecordMapper.pageListAllSize(enitty);
        PageUtils result = new PageUtils(list, totalCount, enitty.getLimit(), enitty.getCurrPage());
        return result;
    }
}
