package com.systemManage.service.impl;

import com.systemManage.dao.base.FaDeclareRecordMapper;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.FaDeclare;
import com.systemManage.pojo.base.FaDeclareRecord;
import com.systemManage.pojo.dto.FaDeclareRecordDto;
import com.systemManage.service.FaDeclareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaDeclareServiceImpl implements FaDeclareService {

    @Autowired
    private FaDeclareRecordMapper faDeclareRecordMapper;

    @Override
    public List<FaDeclareRecord> selectFaDeclareList(String projectId) {
        List<FaDeclareRecord> faDeclareRecords = faDeclareRecordMapper.selectAll(projectId);
        return faDeclareRecords;
    }

    @Override
    public int insertFaDeclareRecord(FaDeclareRecord faDeclareRecord){
        int flag = faDeclareRecordMapper.insertSelective(faDeclareRecord);
        return flag;
    }

    @Override
    public List<FaDeclareRecord> selectByRecord(FaDeclareRecord faDeclareRecord){
        List<FaDeclareRecord> faDeclareRecords = faDeclareRecordMapper.selectByRecord(faDeclareRecord);
        return faDeclareRecords;
    }

    @Override
    public int deleteFaDeclareRecord(String recordId){
        int i = faDeclareRecordMapper.deleteByPrimaryKey(recordId);
        return i;
    }

    @Override
    public int countByExample(Criteria example) {
        int count = faDeclareRecordMapper.countByExample(example);
        return count;
    }

    @Override
    public List<FaDeclareRecordDto> selectByExample(Criteria example) {
        List<FaDeclareRecordDto> faDeclareRecords = faDeclareRecordMapper.selectByExample(example);
        return faDeclareRecords;
    }

    @Override
    public int updateStatusById(FaDeclareRecord record){
        int i = faDeclareRecordMapper.updateStatusByPrimaryKey(record);
        return i;
    }

    @Override
    public int updateAmountById(FaDeclareRecord record){
        int i = faDeclareRecordMapper.updateMoneyByPrimaryKey(record);
        return i;
    }

    @Override
    public FaDeclareRecord selectByPrimaryKey(String recordId) {
        FaDeclareRecord faDeclareRecord = faDeclareRecordMapper.selectByPrimaryKey(recordId);
        return faDeclareRecord;
    }


}
