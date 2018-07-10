package com.systemManage.service;

import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.FaDeclare;
import com.systemManage.pojo.base.FaDeclareRecord;
import com.systemManage.pojo.dto.FaDeclareRecordDto;

import java.util.List;

public interface FaDeclareService {
    List<FaDeclareRecord> selectFaDeclareList(String projectId);

    int insertFaDeclareRecord(FaDeclareRecord faDeclareRecord);

    List<FaDeclareRecord> selectByRecord(FaDeclareRecord faDeclareRecord);

    int deleteFaDeclareRecord(String recordId);

    int countByExample(Criteria example);

    List<FaDeclareRecordDto> selectByExample(Criteria example);

    int updateStatusById(FaDeclareRecord record);

    int updateAmountById(FaDeclareRecord record);

    FaDeclareRecord selectByPrimaryKey(String recordId);
}
