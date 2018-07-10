package com.systemManage.service.impl;

import com.systemManage.dao.base.BiWorkMapper;
import com.systemManage.pojo.base.BiWork;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.service.BiWorkService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BiWorkServiceImpl implements BiWorkService {
    @Autowired
    private BiWorkMapper biWorkMapper;

    private static final Logger logger = LoggerFactory.getLogger(BiWorkServiceImpl.class);

    @Override
    public int countByExample(Criteria example) {
        int count = this.biWorkMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    @Override
    public BiWork selectByPrimaryKey(String workId) {
        return this.biWorkMapper.selectByPrimaryKey(workId);
    }

    @Override
    public List<BiWork> selectByExample(Criteria example) {
        return this.biWorkMapper.selectByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(String workId) {
        return this.biWorkMapper.deleteByPrimaryKey(workId);
    }

    @Override
    public int updateByPrimaryKeySelective(BiWork record) {
        return this.biWorkMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BiWork record) {
        return this.biWorkMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByExample(Criteria example) {
        return this.biWorkMapper.deleteByExample(example);
    }

    @Override
    public int updateByExampleSelective(BiWork record, Criteria example) {
        return this.biWorkMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(BiWork record, Criteria example) {
        return this.biWorkMapper.updateByExample(record, example);
    }

    @Override
    public int insert(BiWork record) {
        return this.biWorkMapper.insert(record);
    }

    @Override
    public int insertSelective(BiWork record) {
        return this.biWorkMapper.insertSelective(record);
    }
}