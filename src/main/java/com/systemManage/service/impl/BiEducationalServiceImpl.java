package com.systemManage.service.impl;

import com.systemManage.dao.base.BiEducationalMapper;
import com.systemManage.pojo.base.BiEducational;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.service.BiEducationalService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BiEducationalServiceImpl implements BiEducationalService {
    @Autowired
    private BiEducationalMapper biEducationalMapper;

    private static final Logger logger = LoggerFactory.getLogger(BiEducationalServiceImpl.class);

    @Override
    public int countByExample(Criteria example) {
        int count = this.biEducationalMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    @Override
    public BiEducational selectByPrimaryKey(String educationalId) {
        return this.biEducationalMapper.selectByPrimaryKey(educationalId);
    }

    @Override
    public List<BiEducational> selectByExample(Criteria example) {
        return this.biEducationalMapper.selectByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(String educationalId) {
        return this.biEducationalMapper.deleteByPrimaryKey(educationalId);
    }

    @Override
    public int updateByPrimaryKeySelective(BiEducational record) {
        return this.biEducationalMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BiEducational record) {
        return this.biEducationalMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByExample(Criteria example) {
        return this.biEducationalMapper.deleteByExample(example);
    }

    @Override
    public int updateByExampleSelective(BiEducational record, Criteria example) {
        return this.biEducationalMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(BiEducational record, Criteria example) {
        return this.biEducationalMapper.updateByExample(record, example);
    }

    @Override
    public int insert(BiEducational record) {
        return this.biEducationalMapper.insert(record);
    }

    @Override
    public int insertSelective(BiEducational record) {
        return this.biEducationalMapper.insertSelective(record);
    }
}