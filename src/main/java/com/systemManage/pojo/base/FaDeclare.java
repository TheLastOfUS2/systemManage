package com.systemManage.pojo.base;

import java.io.Serializable;

public class FaDeclare implements Serializable {
    private static final long serialVersionUID = 1L;

    private String projectId;
    private String projectName;
    private Double projectAmount;
    private Double typeAMoney;
    private Double rbAmountA;
    private Double surplusAmountA;
    private Double typeBMoney;
    private Double rbAmountB;
    private Double surplusAmountB;
    private Double typeCMoney;
    private Double rbAmountC;
    private Double surplusAmountC;
//    private Double typeDMoney;
//    private Double rbAmountD;
//    private Double surplusAmountD;
    private String projectTime;
    private Double projectSurplusAmount;
    private String endTime;

    public Double getProjectSurplusAmount() {
        return projectSurplusAmount;
    }

    public void setProjectSurplusAmount(Double projectSurplusAmount) {
        this.projectSurplusAmount = projectSurplusAmount;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Double getProjectAmount() {
        return projectAmount;
    }

    public void setProjectAmount(Double projectAmount) {
        this.projectAmount = projectAmount;
    }

    public Double getTypeAMoney() {
        return typeAMoney;
    }

    public void setTypeAMoney(Double typeAMoney) {
        this.typeAMoney = typeAMoney;
    }

    public Double getRbAmountA() {
        return rbAmountA;
    }

    public void setRbAmountA(Double rbAmountA) {
        this.rbAmountA = rbAmountA;
    }



    public Double getTypeBMoney() {
        return typeBMoney;
    }

    public void setTypeBMoney(Double typeBMoney) {
        this.typeBMoney = typeBMoney;
    }

    public Double getRbAmountB() {
        return rbAmountB;
    }

    public void setRbAmountB(Double rbAmountB) {
        this.rbAmountB = rbAmountB;
    }



    public Double getTypeCMoney() {
        return typeCMoney;
    }

    public void setTypeCMoney(Double typeCMoney) {
        this.typeCMoney = typeCMoney;
    }

    public Double getRbAmountC() {
        return rbAmountC;
    }

    public void setRbAmountC(Double rbAmountC) {
        this.rbAmountC = rbAmountC;
    }



//    public Double getTypeDMoney() {
//        return typeDMoney;
//    }
//
//    public void setTypeDMoney(Double typeDMoney) {
//        this.typeDMoney = typeDMoney;
//    }
//
//    public Double getRbAmountD() {
//        return rbAmountD;
//    }
//
//    public void setRbAmountD(Double rbAmountD) {
//        this.rbAmountD = rbAmountD;
//    }

    public Double getSurplusAmountA() {
        return surplusAmountA;
    }

    public void setSurplusAmountA(Double surplusAmountA) {
        this.surplusAmountA = surplusAmountA;
    }

    public Double getSurplusAmountB() {
        return surplusAmountB;
    }

    public void setSurplusAmountB(Double surplusAmountB) {
        this.surplusAmountB = surplusAmountB;
    }

    public Double getSurplusAmountC() {
        return surplusAmountC;
    }

    public void setSurplusAmountC(Double surplusAmountC) {
        this.surplusAmountC = surplusAmountC;
    }

//    public Double getSurplusAmountD() {
//        return surplusAmountD;
//    }
//
//    public void setSurplusAmountD(Double surplusAmountD) {
//        this.surplusAmountD = surplusAmountD;
//    }

    public String getProjectTime() {
        return projectTime;
    }

    public void setProjectTime(String projectTime) {
        this.projectTime = projectTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
