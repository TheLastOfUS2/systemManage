package com.systemManage.service.impl;

import com.systemManage.dao.base.FaCaMapper;
import com.systemManage.pojo.base.FaCa;
import com.systemManage.service.FaCaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FaCaServiceImpl implements FaCaService {
    @Autowired
    private FaCaMapper faCaMapper;

    @Override
    public List<FaCa> selectByProBase(FaCa record) {
        List<FaCa> faCas = faCaMapper.selectBySelective(record);
        return faCas;
    }

    @Override
    public int insertFaCa(FaCa record) {
        int i = faCaMapper.insertSelective(record);
        return i;
    }

    @Override
    public int updateFaCa(FaCa record) {
        int i = faCaMapper.updateByPrimaryKeySelective(record);
        return i;
    }

    @Override
    public int countByPid(String pid){
        int i = faCaMapper.countByPid(pid);
        return i;
    }


}
