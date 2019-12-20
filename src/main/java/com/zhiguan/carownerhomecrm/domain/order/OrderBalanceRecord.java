package com.zhiguan.carownerhomecrm.domain.order;

import java.util.Date;

public class OrderBalanceRecord {
    /**
     * ID
     */
    private Long id;

    /**
     * 是否被删除
     */
    private String isDeleted;

    /**
     * 创建人
     */
    private String createOperator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后更改人
     */
    private String lastOperator;

    /**
     * 最后修改时间
     */
    private Date lastModifyTime;

    /**
     * 客户id
     */
    private Long customerId;

    /**
     * 应用id
     */
    private Long applyId;

    /**
     * 交易类型：'+/-'
     */
    private String type;

    /**
     * 交易数量
     */
    private Integer count;

    /**
     * 交易前的剩余量
     */
    private Integer currentBalance;

    /**
     * 交易后的剩余量
     */
    private Integer afterBalance;

    /**
     * 原因
     */
    private String reason;

    /**
     * 备用字段1
     */
    private String bak1;

    /**
     * 备用字段2
     */
    private String bak2;

    /**
     * 备用字段3
     */
    private String bak3;

    //模糊查询字段
    private String likeName;
    
    private int pageStart;
    
    private int limit;
    
    private int currPage;
    
    private String applyName;// 应用名称
    
    private Date starDate;
    
    private Date endDate;
    /**
     * ID
     * @return id ID
     */
    public Long getId() {
        return id;
    }

    /**
     * ID
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 是否被删除
     * @return is_deleted 是否被删除
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * 是否被删除
     * @param isDeleted 是否被删除
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * 创建人
     * @return create_Operator 创建人
     */
    public String getCreateOperator() {
        return createOperator;
    }

    /**
     * 创建人
     * @param createOperator 创建人
     */
    public void setCreateOperator(String createOperator) {
        this.createOperator = createOperator == null ? null : createOperator.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 最后更改人
     * @return last_operator 最后更改人
     */
    public String getLastOperator() {
        return lastOperator;
    }

    /**
     * 最后更改人
     * @param lastOperator 最后更改人
     */
    public void setLastOperator(String lastOperator) {
        this.lastOperator = lastOperator == null ? null : lastOperator.trim();
    }

    /**
     * 最后修改时间
     * @return last_Modify_Time 最后修改时间
     */
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * 最后修改时间
     * @param lastModifyTime 最后修改时间
     */
    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    /**
     * 客户id
     * @return customer_id 客户id
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * 客户id
     * @param customerId 客户id
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * 应用id
     * @return apply_id 应用id
     */
    public Long getApplyId() {
        return applyId;
    }

    /**
     * 应用id
     * @param applyId 应用id
     */
    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    /**
     * 交易类型：'+/-'
     * @return type 交易类型：'+/-'
     */
    public String getType() {
        return type;
    }

    /**
     * 交易类型：'+/-'
     * @param type 交易类型：'+/-'
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 交易数量
     * @return count 交易数量
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 交易数量
     * @param count 交易数量
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 交易前的剩余量
     * @return current_balance 交易前的剩余量
     */
    public Integer getCurrentBalance() {
        return currentBalance;
    }

    /**
     * 交易前的剩余量
     * @param currentBalance 交易前的剩余量
     */
    public void setCurrentBalance(Integer currentBalance) {
        this.currentBalance = currentBalance;
    }

    /**
     * 交易后的剩余量
     * @return after_balance 交易后的剩余量
     */
    public Integer getAfterBalance() {
        return afterBalance;
    }

    /**
     * 交易后的剩余量
     * @param afterBalance 交易后的剩余量
     */
    public void setAfterBalance(Integer afterBalance) {
        this.afterBalance = afterBalance;
    }

    /**
     * 原因
     * @return reason 原因
     */
    public String getReason() {
        return reason;
    }

    /**
     * 原因
     * @param reason 原因
     */
    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    /**
     * 备用字段1
     * @return bak1 备用字段1
     */
    public String getBak1() {
        return bak1;
    }

    /**
     * 备用字段1
     * @param bak1 备用字段1
     */
    public void setBak1(String bak1) {
        this.bak1 = bak1 == null ? null : bak1.trim();
    }

    /**
     * 备用字段2
     * @return bak2 备用字段2
     */
    public String getBak2() {
        return bak2;
    }

    /**
     * 备用字段2
     * @param bak2 备用字段2
     */
    public void setBak2(String bak2) {
        this.bak2 = bak2 == null ? null : bak2.trim();
    }

    /**
     * 备用字段3
     * @return bak3 备用字段3
     */
    public String getBak3() {
        return bak3;
    }

    /**
     * 备用字段3
     * @param bak3 备用字段3
     */
    public void setBak3(String bak3) {
        this.bak3 = bak3 == null ? null : bak3.trim();
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

	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

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
    
}