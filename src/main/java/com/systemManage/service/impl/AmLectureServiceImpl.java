package com.systemManage.service.impl;

import com.systemManage.common.utils.JsonUtils;
import com.systemManage.common.utils.StringUtil;
import com.systemManage.dao.base.AmLectureMapper;
import com.systemManage.dao.ext.AmLectureMapperExt;
import com.systemManage.pojo.base.AmConference;
import com.systemManage.pojo.base.AmLecture;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.service.AmLectureService;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmLectureServiceImpl implements AmLectureService {
    @Autowired
    private AmLectureMapper amLectureMapper;
    @Autowired
    private AmLectureMapperExt amLectureMapperExt;

    private static final Logger logger = LoggerFactory.getLogger(AmLectureServiceImpl.class);
    
    @Override
    public JSONObject insertSelective(AmLecture amLecture, HttpServletRequest request) {
        int res = 0;
        JSONObject result = null;
        if(amLecture!=null){
            String lectureId=amLecture.getLectureId();
            if (StringUtil.isEmpty(lectureId)) {
                //设置主键
                amLecture.setLectureId(StringUtil.getUUID());
                //设置新增时间
                amLecture.setLectureCreateTime(new Date());
                //设置删除状态（0.未删除1.已删除）
                amLecture.setLectureDel("0");
            }
            //保存基本信息
            if (StringUtil.isEmpty(lectureId)) {
                res = this.amLectureMapper.insertSelective(amLecture);
            }else{
                res = this.amLectureMapper.updateByPrimaryKeySelective(amLecture);
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
        int count = this.amLectureMapperExt.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    @Override
    public AmLecture selectByPrimaryKey(String lectureId) {
        return this.amLectureMapper.selectByPrimaryKey(lectureId);
    }

    @Override
    public List<AmLecture> selectByExample(Criteria example) {
        return this.amLectureMapperExt.selectByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(String lectureId) {
        return this.amLectureMapper.deleteByPrimaryKey(lectureId);
    }

    @Override
    public int updateByPrimaryKeySelective(AmLecture record) {
        return this.amLectureMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AmLecture record, String lectureDel) {
     // lectureDel=0:恢复;lectureDel=1:删除;lectureDel=2:彻底删除
        if(!("2").equals(lectureDel)){
            //更改数据状态
            record.setLectureDel(lectureDel);
            return this.amLectureMapper.updateByPrimaryKey(record);
        }else{
            //从数据库中删除
            return this.amLectureMapper.deleteByPrimaryKey(record.getLectureId());
        }
    }

    @Override
    public int deleteByExample(Criteria example) {
        return this.amLectureMapper.deleteByExample(example);
    }

    @Override
    public int updateByExampleSelective(AmLecture record, Criteria example) {
        return this.amLectureMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(AmLecture record, Criteria example) {
        return this.amLectureMapper.updateByExample(record, example);
    }

    @Override
    public int insert(AmLecture record) {
        return this.amLectureMapper.insert(record);
    }

    @Override
    public int insertSelective(AmLecture record) {
        return this.amLectureMapper.insertSelective(record);
    }
}