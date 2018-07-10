package com.systemManage.service;

import com.systemManage.pojo.base.FaCa;

import java.util.List;

public interface FaCaService {
    //根据用户id和人的id去fa_ca中查出对应的FaCa
    List<FaCa> selectByProBase(FaCa record);

    int insertFaCa(FaCa record);

    int updateFaCa(FaCa record);

    int countByPid(String pid);
}
