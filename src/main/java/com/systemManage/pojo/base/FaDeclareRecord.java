package com.systemManage.pojo.base;

import java.io.Serializable;

public class FaDeclareRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    //申报人
    private String baseInfoId;
    //履历id
    private String recordId;
    //项目id
    private String projectId;
    //资金类别
    private String recordType;
    //申请报销金额
    private Double rbAmount;
    //报销状态（0申请中，1通过，2驳回）
    private String recordStatus;
    //文件id
    private String fileId;

    public String getBaseInfoId() {
        return baseInfoId;
    }

    public void setBaseInfoId(String baseInfoId) {
        this.baseInfoId = baseInfoId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public Double getRbAmount() {
        return rbAmount;
    }

    public void setRbAmount(Double rbAmount) {
        this.rbAmount = rbAmount;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }
}
