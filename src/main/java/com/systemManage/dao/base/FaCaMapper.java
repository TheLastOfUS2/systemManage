package com.systemManage.dao.base;

import com.systemManage.pojo.base.FaCa;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaCaMapper {
    int deleteByPrimaryKey(String faCaId);

    int insert(FaCa record);

    int insertSelective(FaCa record);

    FaCa selectByPrimaryKey(String faCaId);

    int updateByPrimaryKeySelective(FaCa record);

    int updateByPrimaryKey(FaCa record);

    List<FaCa> selectBySelective(FaCa record);

    int countByPid(String projectId);
}