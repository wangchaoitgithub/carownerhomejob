package com.zhiguan.carownerhomecrm.domain.base;

import java.util.Date;

public class BaseOperatorsInfo {
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
     * 运营商简称
     */
    private String nameSimple;

    /**
     * 运营商全称
     */
    private String nameAll;

    /**
     * 国家
     */
    private String country;

    /**
     * 电话地区号(区号)
     */
    private String areaCode;

    /**
     * 代号id
     */
    private String custId;

    /**
     * 密钥(长度32位，数字和大小写字母混合)
     */
    private String appsecret;

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
     * 运营商简称
     * @return name_simple 运营商简称
     */
    public String getNameSimple() {
        return nameSimple;
    }

    /**
     * 运营商简称
     * @param nameSimple 运营商简称
     */
    public void setNameSimple(String nameSimple) {
        this.nameSimple = nameSimple == null ? null : nameSimple.trim();
    }

    /**
     * 运营商全称
     * @return name_all 运营商全称
     */
    public String getNameAll() {
        return nameAll;
    }

    /**
     * 运营商全称
     * @param nameAll 运营商全称
     */
    public void setNameAll(String nameAll) {
        this.nameAll = nameAll == null ? null : nameAll.trim();
    }

    /**
     * 国家
     * @return country 国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 国家
     * @param country 国家
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /**
     * 电话地区号(区号)
     * @return area_code 电话地区号(区号)
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 电话地区号(区号)
     * @param areaCode 电话地区号(区号)
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     * 代号id
     * @return cust_id 代号id
     */
    public String getCustId() {
        return custId;
    }

    /**
     * 代号id
     * @param custId 代号id
     */
    public void setCustId(String custId) {
        this.custId = custId == null ? null : custId.trim();
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
}