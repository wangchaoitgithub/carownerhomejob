package com.zhiguan.carownerhomecrm.domain.base;

import java.util.Date;

    public class BaseApplyInfo {
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
     * 应用名称
     */
    private String name;

    /**
     * 运营商ID
     */
    private Long operatorsId;

    /**
     * 客户id
     */
    private Long customerId;

    /**
     * appid(长度16位，数字和小写字母混合)
     */
    private String appid;

    /**
     * 密钥(长度32位，数字和大小写字母混合)
     */
    private String appsecret;

    /**
     * 请求手机号数量（累加）
     */
    private Long requestNumber;

    /**
     * 获取验证码的数量（累加）
     */
    private Long useNumber;

    /**
     * 短信通道号
     */
    private String smsChannelNumber;

    /**
     * 短信关键字
     */
    private String smsKeyword;

    /**
     * 描述
     */
    private String desc;

    /**
     * 手机号码获取索引值(最新)
     */
    private Long phoneIndex;

    /**
     * 短信的过期时间（秒）
     */
    private Integer overTime;

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
    private String likeName;
    
    private int pageStart;
    
    private int limit;
    
    private int currPage;
    
    private String nameSimple;// 运营商简称
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
     * 应用名称
     * @return name 应用名称
     */
    public String getName() {
        return name;
    }

    /**
     * 应用名称
     * @param name 应用名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 运营商ID
     * @return operators_id 运营商ID
     */
    public Long getOperatorsId() {
        return operatorsId;
    }

    /**
     * 运营商ID
     * @param operatorsId 运营商ID
     */
    public void setOperatorsId(Long operatorsId) {
        this.operatorsId = operatorsId;
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
     * appid(长度16位，数字和小写字母混合)
     * @return appid appid(长度16位，数字和小写字母混合)
     */
    public String getAppid() {
        return appid;
    }

    /**
     * appid(长度16位，数字和小写字母混合)
     * @param appid appid(长度16位，数字和小写字母混合)
     */
    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    /**
     * 密钥(长度32位，数字和大小写字母混合)
     * @return appsecret 密钥(长度32位，数字和大小写字母混合)
     */
    public String getAppsecret() {
        return appsecret;
    }

    /**
     * 密钥(长度32位，数字和大小写字母混合)
     * @param appsecret 密钥(长度32位，数字和大小写字母混合)
     */
    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret == null ? null : appsecret.trim();
    }

    /**
     * 请求手机号数量（累加）
     * @return request_number 请求手机号数量（累加）
     */
    public Long getRequestNumber() {
        return requestNumber;
    }

    /**
     * 请求手机号数量（累加）
     * @param requestNumber 请求手机号数量（累加）
     */
    public void setRequestNumber(Long requestNumber) {
        this.requestNumber = requestNumber;
    }

    /**
     * 获取验证码的数量（累加）
     * @return use_number 获取验证码的数量（累加）
     */
    public Long getUseNumber() {
        return useNumber;
    }

    /**
     * 获取验证码的数量（累加）
     * @param useNumber 获取验证码的数量（累加）
     */
    public void setUseNumber(Long useNumber) {
        this.useNumber = useNumber;
    }

    /**
     * 短信通道号
     * @return sms_channel_number 短信通道号
     */
    public String getSmsChannelNumber() {
        return smsChannelNumber;
    }

    /**
     * 短信通道号
     * @param smsChannelNumber 短信通道号
     */
    public void setSmsChannelNumber(String smsChannelNumber) {
        this.smsChannelNumber = smsChannelNumber == null ? null : smsChannelNumber.trim();
    }

    /**
     * 短信关键字
     * @return sms_keyword 短信关键字
     */
    public String getSmsKeyword() {
        return smsKeyword;
    }

    /**
     * 短信关键字
     * @param smsKeyword 短信关键字
     */
    public void setSmsKeyword(String smsKeyword) {
        this.smsKeyword = smsKeyword == null ? null : smsKeyword.trim();
    }

    /**
     * 描述
     * @return desc 描述
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 描述
     * @param desc 描述
     */
    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    /**
     * 手机号码获取索引值(最新)
     * @return phone_index 手机号码获取索引值(最新)
     */
    public Long getPhoneIndex() {
        return phoneIndex;
    }

    /**
     * 手机号码获取索引值(最新)
     * @param phoneIndex 手机号码获取索引值(最新)
     */
    public void setPhoneIndex(Long phoneIndex) {
        this.phoneIndex = phoneIndex;
    }

    /**
     * 短信的过期时间（秒）
     * @return over_time 短信的过期时间（秒）
     */
    public Integer getOverTime() {
        return overTime;
    }

    /**
     * 短信的过期时间（秒）
     * @param overTime 短信的过期时间（秒）
     */
    public void setOverTime(Integer overTime) {
        this.overTime = overTime;
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

	public String getNameSimple() {
		return nameSimple;
	}

	public void setNameSimple(String nameSimple) {
		this.nameSimple = nameSimple;
	}
    
}