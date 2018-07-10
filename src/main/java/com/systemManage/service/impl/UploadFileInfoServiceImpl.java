package com.systemManage.service.impl;

import com.systemManage.dao.base.UploadFileInfoMapper;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.UploadFileInfo;
import com.systemManage.service.UploadFileInfoService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadFileInfoServiceImpl implements UploadFileInfoService {
    @Autowired
    private UploadFileInfoMapper uploadFileInfoMapper;

    private static final Logger logger = LoggerFactory.getLogger(UploadFileInfoServiceImpl.class);

    @Override
    public int countByExample(Criteria example) {
        int count = this.uploadFileInfoMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    @Override
    public UploadFileInfo selectByPrimaryKey(String fileId) {
        return this.uploadFileInfoMapper.selectByPrimaryKey(fileId);
    }

    @Override
    public List<UploadFileInfo> selectByExample(Criteria example) {
        return this.uploadFileInfoMapper.selectByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(String fileId) {
        return this.uploadFileInfoMapper.deleteByPrimaryKey(fileId);
    }

    @Override
    public int updateByPrimaryKeySelective(UploadFileInfo record) {
        return this.uploadFileInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UploadFileInfo record) {
        return this.uploadFileInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByExample(Criteria example) {
        return this.uploadFileInfoMapper.deleteByExample(example);
    }

    @Override
    public int updateByExampleSelective(UploadFileInfo record, Criteria example) {
        return this.uploadFileInfoMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(UploadFileInfo record, Criteria example) {
        return this.uploadFileInfoMapper.updateByExample(record, example);
    }

    @Override
    public int insert(UploadFileInfo record) {
        return this.uploadFileInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(UploadFileInfo record) {
        return this.uploadFileInfoMapper.insertSelective(record);
    }
}