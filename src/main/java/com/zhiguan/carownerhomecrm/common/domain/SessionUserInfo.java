package com.zhiguan.carownerhomecrm.common.domain;

/**
 * 登陆后把这些数据存到session中
 * @author 易鹏
 * @createDate：2018-11-1
 */
public class SessionUserInfo {
	public Long userId;// 客户ID
	public String nickName;// 客户昵称
	public String corporateName;// 公司名称
	public String city;// 城市
	public Long requestNumber;// 请求剩余数量
	public Long useNumber;// 使用剩余数量
	public Long rechargeCount;// 总充值数
	public int applyCount;// 应用数量
	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getCorporateName() {
		return corporateName;
	}
	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Long getRequestNumber() {
		return requestNumber;
	}
	public void setRequestNumber(Long requestNumber) {
		this.requestNumber = requestNumber;
	}
	public Long getUseNumber() {
		return useNumber;
	}
	public void setUseNumber(Long useNumber) {
		this.useNumber = useNumber;
	}
	public Long getRechargeCount() {
		return rechargeCount;
	}
	public void setRechargeCount(Long rechargeCount) {
		this.rechargeCount = rechargeCount;
	}
	public int getApplyCount() {
		return applyCount;
	}
	public void setApplyCount(int applyCount) {
		this.applyCount = applyCount;
	}
}
