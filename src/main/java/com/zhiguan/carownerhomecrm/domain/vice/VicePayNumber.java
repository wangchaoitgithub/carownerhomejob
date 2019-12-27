package com.zhiguan.carownerhomecrm.domain.vice;

import java.util.Date;

public class VicePayNumber {
    private Long id;

    private String isDeleted;

    private String createOperator;

    private Date createTime;

    private String lastOperator;

    private Date lastModifyTime;

    private Long orgId;

    private Long userId;

    private String viceNumber;

    private String state;

    private String expressName;

    private String expressPhone;

    private String expressAddress;

    private Double payMoney;

    private String payWeOrderId;

    private String payWeixinOrderId;

    private String payWeixinStatus;


    //模糊查询字段
    private String likeName;
    private int pageStart;
    private int limit;
    private int currPage;

    private Date starDate;
    private Date endDate;

    private String orgName;
    private String nickName;



    public Date getStarDate() {
        return starDate;
    }

    public void setStarDate(Date starDate) {
        this.starDate = starDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLikeName() {
        return likeName;
    }

    public void setLikeName(String likeName) {
        this.likeName = likeName;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    public String getCreateOperator() {
        return createOperator;
    }

    public void setCreateOperator(String createOperator) {
        this.createOperator = createOperator == null ? null : createOperator.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastOperator() {
        return lastOperator;
    }

    public void setLastOperator(String lastOperator) {
        this.lastOperator = lastOperator == null ? null : lastOperator.trim();
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getViceNumber() {
        return viceNumber;
    }

    public void setViceNumber(String viceNumber) {
        this.viceNumber = viceNumber == null ? null : viceNumber.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName == null ? null : expressName.trim();
    }

    public String getExpressPhone() {
        return expressPhone;
    }

    public void setExpressPhone(String expressPhone) {
        this.expressPhone = expressPhone == null ? null : expressPhone.trim();
    }

    public String getExpressAddress() {
        return expressAddress;
    }

    public void setExpressAddress(String expressAddress) {
        this.expressAddress = expressAddress == null ? null : expressAddress.trim();
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public String getPayWeOrderId() {
        return payWeOrderId;
    }

    public void setPayWeOrderId(String payWeOrderId) {
        this.payWeOrderId = payWeOrderId == null ? null : payWeOrderId.trim();
    }

    public String getPayWeixinOrderId() {
        return payWeixinOrderId;
    }

    public void setPayWeixinOrderId(String payWeixinOrderId) {
        this.payWeixinOrderId = payWeixinOrderId == null ? null : payWeixinOrderId.trim();
    }

    public String getPayWeixinStatus() {
        return payWeixinStatus;
    }

    public void setPayWeixinStatus(String payWeixinStatus) {
        this.payWeixinStatus = payWeixinStatus == null ? null : payWeixinStatus.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}