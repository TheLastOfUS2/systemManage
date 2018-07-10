package com.systemManage.service;

import com.systemManage.pojo.base.AmActivity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-common.xml"})
public class AmActivityServiceTest {

    /**
     * 测试用例
     * 等价类划分（互不相交）
     * 有效等价类：是否实现了规格说明中规定的功能和性能
     * 无效等价类：至少应有一个（不是一个数，是一类测试数据）
     * 边界值分析方法（对等价类划分法的补充）
     */
    @Resource
    private AmActivityService amActivityService;

    @Test
    public void insertSelective() {
        AmActivity amActivity = new AmActivity();
        amActivity.setActivityContent("safasf");
        amActivity.setActivityDel("0");
        amActivity.setActivityId("332DE23KIE932230XKEOO230");
        amActivity.setActivityName("测试  ");
        amActivityService.insertSelective(amActivity);

    }

    @Test
    public void countByExample() {
    }

    @Test
    public void selectByPrimaryKey() {
    }
}