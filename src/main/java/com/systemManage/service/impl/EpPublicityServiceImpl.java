package com.systemManage.service.impl;

import com.systemManage.common.utils.JsonUtils;
import com.systemManage.common.utils.StringUtil;
import com.systemManage.dao.base.EpPublicityMapper;
import com.systemManage.dao.ext.EpPublicityMapperExt;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.EpPublicity;
import com.systemManage.pojo.base.EpReport;
import com.systemManage.service.EpPublicityService;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EpPublicityServiceImpl implements EpPublicityService {
    @Autowired
    private EpPublicityMapper epPublicityMapper;
    @Autowired
    private EpPublicityMapperExt epPublicityMapperExt;

    private static final Logger logger = LoggerFactory.getLogger(EpPublicityServiceImpl.class);
    
    @Override
    public JSONObject insertSelective(EpPublicity epPublicity, HttpServletRequest request) {
        int res = 0;
        JSONObject result = null;
        if(epPublicity!=null){
            String publicityId=epPublicity.getPublicityId();
            if (StringUtil.isEmpty(publicityId)) {
                //设置主键
                epPublicity.setPublicityId(StringUtil.getUUID());
                //设置新增时间
                epPublicity.setPublicityCreateTime(new Date());
                //设置删除状态（0.未删除1.已删除）
                epPublicity.setPublicityDel("0");
            }
            //保存基本信息
            if (StringUtil.isEmpty(publicityId)) {
                res = this.epPublicityMapper.insertSelective(epPublicity);
            }else{
                res = this.epPublicityMapper.updateByPrimaryKeySelective(epPublicity);
            }
        }
        if(res > 0){
            result = JsonUtils.setSuccess();    
            result.put("text", "操作成功");
        }else{
            result = JsonUtils.setError();
            result.put("text", "操作失败");
        }
        return result;
    }
    
    @Override
    public int countByExample(Criteria example) {
        int count = this.epPublicityMapperExt.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    @Override
    public EpPublicity selectByPrimaryKey(String publicityId) {
        return this.epPublicityMapper.selectByPrimaryKey(publicityId);
    }

    @Override
    public List<EpPublicity> selectByExample(Criteria example) {
        return this.epPublicityMapperExt.selectByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(String publicityId) {
        return this.epPublicityMapper.deleteByPrimaryKey(publicityId);
    }

    @Override
    public int updateByPrimaryKeySelective(EpPublicity record) {
        return this.epPublicityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(EpPublicity record, String publicityDel) {
        // publicityDel=0:恢复;publicityDel=1:删除;publicityDel=2:彻底删除
        if(!("2").equals(publicityDel)){
            //更改数据状态
            record.setPublicityDel(publicityDel);
            return this.epPublicityMapper.updateByPrimaryKey(record);
        }else{
            //从数据库中删除
            return this.epPublicityMapper.deleteByPrimaryKey(record.getPublicityId());
        }
    }

    @Override
    public int deleteByExample(Criteria example) {
        return this.epPublicityMapper.deleteByExample(example);
    }

    @Override
    public int updateByExampleSelective(EpPublicity record, Criteria example) {
        return this.epPublicityMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(EpPublicity record, Criteria example) {
        return this.epPublicityMapper.updateByExample(record, example);
    }

    @Override
    public int insert(EpPublicity record) {
        return this.epPublicityMapper.insert(record);
    }

    @Override
    public int insertSelective(EpPublicity record) {
        return this.epPublicityMapper.insertSelective(record);
    }
}