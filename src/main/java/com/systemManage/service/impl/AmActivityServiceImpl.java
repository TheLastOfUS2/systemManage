package com.systemManage.service.impl;

import com.systemManage.common.utils.JsonUtils;
import com.systemManage.common.utils.StringUtil;
import com.systemManage.dao.base.AmActivityMapper;
import com.systemManage.dao.ext.AmActivityMapperExt;
import com.systemManage.pojo.base.AmActivity;
import com.systemManage.pojo.base.AmAdopt;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.service.AmActivityService;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmActivityServiceImpl implements AmActivityService {
    @Autowired
    private AmActivityMapper amActivityMapper; // 特色活动
    @Autowired
    private AmActivityMapperExt amActivityMapperExt; // 特色活动

    private static final Logger logger = LoggerFactory.getLogger(AmActivityServiceImpl.class);
    
    @Override
    public JSONObject insertSelective(AmActivity amActivity, HttpServletRequest request) {
        int res = 0;
        JSONObject result = null;
        if(amActivity!=null){
            String activityId=amActivity.getActivityId();
            if (StringUtil.isEmpty(activityId)) {
                //设置主键
                amActivity.setActivityId(StringUtil.getUUID());
                //设置新增时间
                amActivity.setActivityCreateTime(new Date());
                //设置删除状态（0.未删除1.已删除）
                amActivity.setActivityDel("0");
            }
            //保存基本信息
            if (StringUtil.isEmpty(activityId)) {
                res = this.amActivityMapper.insertSelective(amActivity);
            }else{
                res = this.amActivityMapper.updateByPrimaryKeySelective(amActivity);
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
        int count = this.amActivityMapperExt.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    @Override
    public AmActivity selectByPrimaryKey(String activityId) {
        return this.amActivityMapper.selectByPrimaryKey(activityId);
    }

    @Override
    public List<AmActivity> selectByExample(Criteria example) {
        return this.amActivityMapperExt.selectByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(String activityId) {
        return this.amActivityMapper.deleteByPrimaryKey(activityId);
    }

    @Override
    public int updateByPrimaryKeySelective(AmActivity record) {
        return this.amActivityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AmActivity record, String activityDel) {
     // baseInfoDel=0:恢复;baseInfoDel=1:删除;baseInfoDel=2:彻底删除
        if(!("2").equals(activityDel)){
            //更改数据状态
            record.setActivityDel(activityDel);
            return this.amActivityMapper.updateByPrimaryKey(record);
        }else{
            //从数据库中删除
            return this.amActivityMapper.deleteByPrimaryKey(record.getActivityId());
        }
    }

    @Override
    public int deleteByExample(Criteria example) {
        return this.amActivityMapper.deleteByExample(example);
    }

    @Override
    public int updateByExampleSelective(AmActivity record, Criteria example) {
        return this.amActivityMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(AmActivity record, Criteria example) {
        return this.amActivityMapper.updateByExample(record, example);
    }

    @Override
    public int insert(AmActivity record) {
        return this.amActivityMapper.insert(record);
    }

    @Override
    public int insertSelective(AmActivity record) {
        return this.amActivityMapper.insertSelective(record);
    }
}