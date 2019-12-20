package com.zhiguan.carownerhomecrm.domain.phone;

import java.util.Date;

public class PhoneSmsRecord {
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
     * 获取手机号的时间
     */
    private Date getTime;

    /**
     * 发送短信时间
     */
    private Date sendTime;

    /**
     * 运营商推送到我们平台的时间
     */
    private Date receiveTime;

    /**
     * 成功获取我方验证码的时间
     */
    private Date doneTime;

    /**
     * 验证码
     */
    private Integer verificationCode;

    /**
     * 手机号
     */
    private Long phoneNumber;

    /**
     * 短信内容
     */
    private String smsContent;

    /**
     * 客户每次获取手机号时颁发给客户，用来获取验证码时的凭证
     */
    private String uuid;

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 应用的appid
     */
    private String appid;

    /**
     * 状态（init/获取手机号，send/应用反馈使用，receive/运营商短信已接收, done/应用已消费） 
     */
    private String state;

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
    //模糊查询字段
    
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
     * 获取手机号的时间
     * @return get_time 获取手机号的时间
     */
    public Date getGetTime() {
        return getTime;
    }

    /**
     * 获取手机号的时间
     * @param getTime 获取手机号的时间
     */
    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }

    /**
     * 发送短信时间
     * @return send_time 发送短信时间
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * 发送短信时间
     * @param sendTime 发送短信时间
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * 运营商推送到我们平台的时间
     * @return receive_time 运营商推送到我们平台的时间
     */
    public Date getReceiveTime() {
        return receiveTime;
    }

    /**
     * 运营商推送到我们平台的时间
     * @param receiveTime 运营商推送到我们平台的时间
     */
    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    /**
     * 成功获取我方验证码的时间
     * @return done_time 成功获取我方验证码的时间
     */
    public Date getDoneTime() {
        return doneTime;
    }

    /**
     * 成功获取我方验证码的时间
     * @param doneTime 成功获取我方验证码的时间
     */
    public void setDoneTime(Date doneTime) {
        this.doneTime = doneTime;
    }

    /**
     * 验证码
     * @return verification_code 验证码
     */
    public Integer getVerificationCode() {
        return verificationCode;
    }

    /**
     * 验证码
     * @param verificationCode 验证码
     */
    public void setVerificationCode(Integer verificationCode) {
        this.verificationCode = verificationCode;
    }

    /**
     * 手机号
     * @return phone_number 手机号
     */
    public Long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 手机号
     * @param phoneNumber 手机号
     */
    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 短信内容
     * @return sms_content 短信内容
     */
    public String getSmsContent() {
        return smsContent;
    }

    /**
     * 短信内容
     * @param smsContent 短信内容
     */
    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent == null ? null : smsContent.trim();
    }

    /**
     * 客户每次获取手机号时颁发给客户，用来获取验证码时的凭证
     * @return uuid 客户每次获取手机号时颁发给客户，用来获取验证码时的凭证
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 客户每次获取手机号时颁发给客户，用来获取验证码时的凭证
     * @param uuid 客户每次获取手机号时颁发给客户，用来获取验证码时的凭证
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * 客户ID
     * @return customer_id 客户ID
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * 客户ID
     * @param customerId 客户ID
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * 应用的appid
     * @return appid 应用的appid
     */
    public String getAppid() {
        return appid;
    }

    /**
     * 应用的appid
     * @param appid 应用的appid
     */
    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    /**
     * 状态（init/获取手机号，send/应用反馈使用，receive/运营商短信已接收, done/应用已消费） 
     * @return state 状态（init/获取手机号，send/应用反馈使用，receive/运营商短信已接收, done/应用已消费） 
     */
    public String getState() {
        return state;
    }

    /**
     * 状态（init/获取手机号，send/应用反馈使用，receive/运营商短信已接收, done/应用已消费） 
     * @param state 状态（init/获取手机号，send/应用反馈使用，receive/运营商短信已接收, done/应用已消费） 
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
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