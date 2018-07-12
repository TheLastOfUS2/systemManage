package com.systemManage.dao.base;

import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.FaDeclareRecord;
import com.systemManage.pojo.dto.FaDeclareRecordDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaDeclareRecordMapper {
    //根据id
    List<FaDeclareRecord> selectAll(String projectId);

    int insertSelective(FaDeclareRecord faDeclareRecord);

    List<FaDeclareRecord> selectByRecord(FaDeclareRecord faDeclareRecord);

    int deleteByPrimaryKey(String recordId);

    int countByExample(Criteria example);

    int updateStatusByPrimaryKey(FaDeclareRecord faDeclareRecord);

    int updateMoneyByPrimaryKey(FaDeclareRecord faDeclareRecord);

    List<FaDeclareRecordDto> selectByExample(Criteria example);

    FaDeclareRecord selectByPrimaryKey(String recordId);

    int updateDelByPrimaryKey(FaDeclareRecord faDeclareRecord);
}
