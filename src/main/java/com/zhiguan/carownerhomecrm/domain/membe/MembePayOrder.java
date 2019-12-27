package com.zhiguan.carownerhomecrm.domain.membe;

import java.util.Date;

public class MembePayOrder {
    private Long id;

    private String isDeleted;

    private String createOperator;

    private Date createTime;

    private String lastOperator;

    private Date lastModifyTime;

    private String orgId;

    private Long userId;

    private Double payMoney;

    private Long productId;

    private Integer addMonthCount;

    private Date preMemberDate;

    private Date afterMemberDate;

    private String payWeOrderId;

    private String payWeixinOrderId;

    private String payWeixinStatus;

    private String status;


    /*模糊查询字段*/
    private String likeName;
    private int pageStart;
    private int limit;
    private int currPage;

    private Date starDate;
    private Date endDate;

    private String orgName;
    private String productName;



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

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getAddMonthCount() {
        return addMonthCount;
    }

    public void setAddMonthCount(Integer addMonthCount) {
        this.addMonthCount = addMonthCount;
    }

    public Date getPreMemberDate() {
        return preMemberDate;
    }

    public void setPreMemberDate(Date preMemberDate) {
        this.preMemberDate = preMemberDate;
    }

    public Date getAfterMemberDate() {
        return afterMemberDate;
    }

    public void setAfterMemberDate(Date afterMemberDate) {
        this.afterMemberDate = afterMemberDate;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}