package com.systemManage.pojo.dto;

import com.systemManage.pojo.base.FaDeclareRecord;

public class FaDeclareRecordDto extends FaDeclareRecord{
    private static final long serialVersionUID = 1L;

    private String projectName;
    private String baseInfoName;
    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBaseInfoName() {
        return baseInfoName;
    }

    public void setBaseInfoName(String baseInfoName) {
        this.baseInfoName = baseInfoName;
    }
}
