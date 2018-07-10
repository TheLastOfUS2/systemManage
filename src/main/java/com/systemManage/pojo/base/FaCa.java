package com.systemManage.pojo.base;

public class FaCa {
    private String faCaId;

    private String projectId;

    private Double amount;

    private String baseInfoId;

    public String getFaCaId() {
        return faCaId;
    }

    public void setFaCaId(String faCaId) {
        this.faCaId = faCaId == null ? null : faCaId.trim();
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getBaseInfoId() {
        return baseInfoId;
    }

    public void setBaseInfoId(String baseInfoId) {
        this.baseInfoId = baseInfoId == null ? null : baseInfoId.trim();
    }
}