package com.zhiguan.carownerhomecrm.mapper.communication;

import com.zhiguan.carownerhomecrm.domain.communication.CommunicationCommentRecord;
import com.zhiguan.carownerhomecrm.domain.order.OrderBalanceRecord;

import java.util.List;

public interface CommunicationCommentRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CommunicationCommentRecord record);

    int insertSelective(CommunicationCommentRecord record);

    CommunicationCommentRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommunicationCommentRecord record);

    int updateByPrimaryKey(CommunicationCommentRecord record);


    List<CommunicationCommentRecord> pageListAll(CommunicationCommentRecord enitty);

    int pageListAllSize(CommunicationCommentRecord enitty);
}