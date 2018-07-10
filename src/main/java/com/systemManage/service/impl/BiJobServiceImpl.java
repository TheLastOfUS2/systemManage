package com.systemManage.service.impl;

import com.systemManage.dao.base.BiJobMapper;
import com.systemManage.pojo.base.BiJob;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.service.BiJobService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BiJobServiceImpl implements BiJobService {
    @Autowired
    private BiJobMapper biJobMapper;

    private static final Logger logger = LoggerFactory.getLogger(BiJobServiceImpl.class);

    @Override
    public int countByExample(Criteria example) {
        int count = this.biJobMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    @Override
    public BiJob selectByPrimaryKey(String jobId) {
        return this.biJobMapper.selectByPrimaryKey(jobId);
    }

    @Override
    public List<BiJob> selectByExample(Criteria example) {
        return this.biJobMapper.selectByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(String jobId) {
        return this.biJobMapper.deleteByPrimaryKey(jobId);
    }

    @Override
    public int updateByPrimaryKeySelective(BiJob record) {
        return this.biJobMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BiJob record) {
        return this.biJobMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByExample(Criteria example) {
        return this.biJobMapper.deleteByExample(example);
    }

    @Override
    public int updateByExampleSelective(BiJob record, Criteria example) {
        return this.biJobMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(BiJob record, Criteria example) {
        return this.biJobMapper.updateByExample(record, example);
    }

    @Override
    public int insert(BiJob record) {
        return this.biJobMapper.insert(record);
    }

    @Override
    public int insertSelective(BiJob record) {
        return this.biJobMapper.insertSelective(record);
    }
}