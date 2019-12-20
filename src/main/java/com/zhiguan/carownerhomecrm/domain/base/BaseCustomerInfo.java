package com.zhiguan.carownerhomecrm.domain.base;

import java.util.Date;

public class BaseCustomerInfo {
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
     * 客户昵称
     */
    private String nickName;

    /**
     * 公司名称
     */
    private String corporateName;

    /**
     * 城市
     */
    private String city;

    /**
     * 帐号
     */
    private String accounts;

    /**
     * 密码
     */
    private String password;

    /**
     * 请求剩余数量
     */
    private Long requestNumber;

    /**
     * 使用剩余数量
     */
    private Long useNumber;

    /**
     * 总充值数
     */
    private Long rechargeCount;

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

    /**
     * 备用字段4
     */
    private String bak4;

    /**
     * 备用字段5
     */
    private String bak5;

    private int applyCount;
    
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
     * 客户昵称
     * @return nick_name 客户昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 客户昵称
     * @param nickName 客户昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * 公司名称
     * @return corporate_name 公司名称
     */
    public String getCorporateName() {
        return corporateName;
    }

    /**
     * 公司名称
     * @param corporateName 公司名称
     */
    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName == null ? null : corporateName.trim();
    }

    /**
     * 城市
     * @return city 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 城市
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 帐号
     * @return accounts 帐号
     */
    public String getAccounts() {
        return accounts;
    }

    /**
     * 帐号
     * @param accounts 帐号
     */
    public void setAccounts(String accounts) {
        this.accounts = accounts == null ? null : accounts.trim();
    }

    /**
     * 密码
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 请求剩余数量
     * @return request_number 请求剩余数量
     */
    public Long getRequestNumber() {
        return requestNumber;
    }

    /**
     * 请求剩余数量
     * @param requestNumber 请求剩余数量
     */
    public void setRequestNumber(Long requestNumber) {
        this.requestNumber = requestNumber;
    }

    /**
     * 使用剩余数量
     * @return surplus_number 使用剩余数量
     */
    public Long getUseNumber() {
        return useNumber;
    }

    /**
     * 使用剩余数量
     * @param surplusNumber 使用剩余数量
     */
    public void setUseNumber(Long useNumber) {
        this.useNumber = useNumber;
    }

    /**
     * 总充值数
     * @return recharge_count 总充值数
     */
    public Long getRechargeCount() {
        return rechargeCount;
    }

    /**
     * 总充值数
     * @param rechargeCount 总充值数
     */
    public void setRechargeCount(Long rechargeCount) {
        this.rechargeCount = rechargeCount;
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

    /**
     * 备用字段4
     * @return bak4 备用字段4
     */
    public String getBak4() {
        return bak4;
    }

    /**
     * 备用字段4
     * @param bak4 备用字段4
     */
    public void setBak4(String bak4) {
        this.bak4 = bak4 == null ? null : bak4.trim();
    }

    /**
     * 备用字段5
     * @return bak5 备用字段5
     */
    public String getBak5() {
        return bak5;
    }

    /**
     * 备用字段5
     * @param bak5 备用字段5
     */
    public void setBak5(String bak5) {
        this.bak5 = bak5 == null ? null : bak5.trim();
    }

	public int getApplyCount() {
		return applyCount;
	}

	public void setApplyCount(int applyCount) {
		this.applyCount = applyCount;
	}
    
}