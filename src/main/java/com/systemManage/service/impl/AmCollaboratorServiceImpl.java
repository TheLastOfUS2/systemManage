package com.systemManage.service.impl;

import com.systemManage.dao.base.AmCollaboratorMapper;
import com.systemManage.pojo.base.AmCollaborator;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.service.AmCollaboratorService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmCollaboratorServiceImpl implements AmCollaboratorService {
    @Autowired
    private AmCollaboratorMapper amCollaboratorMapper;

    private static final Logger logger = LoggerFactory.getLogger(AmCollaboratorServiceImpl.class);

    @Override
    public int countByExample(Criteria example) {
        int count = this.amCollaboratorMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    @Override
    public AmCollaborator selectByPrimaryKey(String collaboratorId) {
        return this.amCollaboratorMapper.selectByPrimaryKey(collaboratorId);
    }

    @Override
    public List<AmCollaborator> selectByExample(Criteria example) {
        return this.amCollaboratorMapper.selectByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(String collaboratorId) {
        return this.amCollaboratorMapper.deleteByPrimaryKey(collaboratorId);
    }

    @Override
    public int updateByPrimaryKeySelective(AmCollaborator record) {
        return this.amCollaboratorMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AmCollaborator record) {
        return this.amCollaboratorMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByExample(Criteria example) {
        return this.amCollaboratorMapper.deleteByExample(example);
    }

    @Override
    public int updateByExampleSelective(AmCollaborator record, Criteria example) {
        return this.amCollaboratorMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(AmCollaborator record, Criteria example) {
        return this.amCollaboratorMapper.updateByExample(record, example);
    }

    @Override
    public int insert(AmCollaborator record) {
        return this.amCollaboratorMapper.insert(record);
    }

    @Override
    public int insertSelective(AmCollaborator record) {
        return this.amCollaboratorMapper.insertSelective(record);
    }
}