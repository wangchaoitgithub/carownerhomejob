package com.zhiguan.carownerhomecrm.domain.org;

import java.util.Date;

public class OrgBaseInfo {
    private Long id;

    private String isDeleted;

    private String createOperator;

    private Date createTime;

    private String lastOperator;

    private Date lastModifyTime;

    private String orgName;

    private String orgDesc;

    private String welcomeWord;

    private String orgType;

    private String orgRule;

    private Long groupUserid;

    private Long groupUserPhonenum;

    private String groupUserWeixinid;

    private String groupIfHighAuthority;

    private Long managerUserid;

    private String province;

    private String city;

    private String logoImgUrlMax;

    private String logoImgUrlMin;

    private String weixinPublicId;

    private String weixinPublicName;

    private String weixinPublicLogo;

    private String weixinPublicHttpUrl;

    private Integer personCountAuth;

    private Integer personCountRead;

    private String isDefault;

    private String carTypeId;

    private String state;

    private Integer viceNumberMin;

    private Integer viceNumberMax;

    private String bak1;

    private String bak2;

    private String bak3;

    /*模糊查询字段*/
    private String likeName;
    private int pageStart;
    private int limit;
    private int currPage;

    private String nickName;

    private String typeName;





    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOrgDesc() {
        return orgDesc;
    }

    public void setOrgDesc(String orgDesc) {
        this.orgDesc = orgDesc == null ? null : orgDesc.trim();
    }

    public String getWelcomeWord() {
        return welcomeWord;
    }

    public void setWelcomeWord(String welcomeWord) {
        this.welcomeWord = welcomeWord == null ? null : welcomeWord.trim();
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType == null ? null : orgType.trim();
    }

    public String getOrgRule() {
        return orgRule;
    }

    public void setOrgRule(String orgRule) {
        this.orgRule = orgRule == null ? null : orgRule.trim();
    }

    public Long getGroupUserid() {
        return groupUserid;
    }

    public void setGroupUserid(Long groupUserid) {
        this.groupUserid = groupUserid;
    }

    public Long getGroupUserPhonenum() {
        return groupUserPhonenum;
    }

    public void setGroupUserPhonenum(Long groupUserPhonenum) {
        this.groupUserPhonenum = groupUserPhonenum;
    }

    public String getGroupUserWeixinid() {
        return groupUserWeixinid;
    }

    public void setGroupUserWeixinid(String groupUserWeixinid) {
        this.groupUserWeixinid = groupUserWeixinid == null ? null : groupUserWeixinid.trim();
    }

    public String getGroupIfHighAuthority() {
        return groupIfHighAuthority;
    }

    public void setGroupIfHighAuthority(String groupIfHighAuthority) {
        this.groupIfHighAuthority = groupIfHighAuthority == null ? null : groupIfHighAuthority.trim();
    }

    public Long getManagerUserid() {
        return managerUserid;
    }

    public void setManagerUserid(Long managerUserid) {
        this.managerUserid = managerUserid;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getLogoImgUrlMax() {
        return logoImgUrlMax;
    }

    public void setLogoImgUrlMax(String logoImgUrlMax) {
        this.logoImgUrlMax = logoImgUrlMax == null ? null : logoImgUrlMax.trim();
    }

    public String getLogoImgUrlMin() {
        return logoImgUrlMin;
    }

    public void setLogoImgUrlMin(String logoImgUrlMin) {
        this.logoImgUrlMin = logoImgUrlMin == null ? null : logoImgUrlMin.trim();
    }

    public String getWeixinPublicId() {
        return weixinPublicId;
    }

    public void setWeixinPublicId(String weixinPublicId) {
        this.weixinPublicId = weixinPublicId == null ? null : weixinPublicId.trim();
    }

    public String getWeixinPublicName() {
        return weixinPublicName;
    }

    public void setWeixinPublicName(String weixinPublicName) {
        this.weixinPublicName = weixinPublicName == null ? null : weixinPublicName.trim();
    }

    public String getWeixinPublicLogo() {
        return weixinPublicLogo;
    }

    public void setWeixinPublicLogo(String weixinPublicLogo) {
        this.weixinPublicLogo = weixinPublicLogo == null ? null : weixinPublicLogo.trim();
    }

    public String getWeixinPublicHttpUrl() {
        return weixinPublicHttpUrl;
    }

    public void setWeixinPublicHttpUrl(String weixinPublicHttpUrl) {
        this.weixinPublicHttpUrl = weixinPublicHttpUrl == null ? null : weixinPublicHttpUrl.trim();
    }

    public Integer getPersonCountAuth() {
        return personCountAuth;
    }

    public void setPersonCountAuth(Integer personCountAuth) {
        this.personCountAuth = personCountAuth;
    }

    public Integer getPersonCountRead() {
        return personCountRead;
    }

    public void setPersonCountRead(Integer personCountRead) {
        this.personCountRead = personCountRead;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault == null ? null : isDefault.trim();
    }

    public String getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(String carTypeId) {
        this.carTypeId = carTypeId == null ? null : carTypeId.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Integer getViceNumberMin() {
        return viceNumberMin;
    }

    public void setViceNumberMin(Integer viceNumberMin) {
        this.viceNumberMin = viceNumberMin;
    }

    public Integer getViceNumberMax() {
        return viceNumberMax;
    }

    public void setViceNumberMax(Integer viceNumberMax) {
        this.viceNumberMax = viceNumberMax;
    }

    public String getBak1() {
        return bak1;
    }

    public void setBak1(String bak1) {
        this.bak1 = bak1 == null ? null : bak1.trim();
    }

    public String getBak2() {
        return bak2;
    }

    public void setBak2(String bak2) {
        this.bak2 = bak2 == null ? null : bak2.trim();
    }

    public String getBak3() {
        return bak3;
    }

    public void setBak3(String bak3) {
        this.bak3 = bak3 == null ? null : bak3.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}