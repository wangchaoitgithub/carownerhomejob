package com.zhiguan.carownerhomecrm.action.base;

import com.zhiguan.commonNew.util.AESUtil;
import com.zhiguan.commonNew.util.StringUtil;
import com.zhiguan.carownerhomecrm.action.common.BaseAction;
import com.zhiguan.carownerhomecrm.common.constant.SessionAttributeName;
import com.zhiguan.carownerhomecrm.common.domain.SessionUserInfo;
import com.zhiguan.carownerhomecrm.common.main.CookieUtil;
import com.zhiguan.carownerhomecrm.common.main.WebMainUtil;
import com.zhiguan.carownerhomecrm.domain.base.BaseCustomerInfo;
import com.zhiguan.carownerhomecrm.service.base.BaseCustomerInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.annotation.Resource;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class BaseCustomerInfoAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = (Logger) LogManager.getLogger("action");

	@Resource
	BaseCustomerInfoService baseCustomerInfoService;
	
	// 平台登录接口
	public  void login() {
		try {
			String loginName = request.getParameter("loginName");
			String password = request.getParameter("password");
			if(StringUtil.isEmpty(loginName) || StringUtil.isEmpty(password)){
				this.writeJson("帐号或密码错误", false);
				return;
			}
			BaseCustomerInfo baseCustomerInfo = baseCustomerInfoService.getCustomerInfo(loginName);
			if(baseCustomerInfo == null){
				this.writeJson("帐号不存在！", false);
				return;
			}else{
				if(!password.equals(baseCustomerInfo.getPassword())){
					this.writeJson("密码错误", false);
					return;
				}
			}
			
			SessionUserInfo sessionUserInfo = new SessionUserInfo();
			sessionUserInfo.setUserId(baseCustomerInfo.getId());
			sessionUserInfo.setNickName(loginName);
			sessionUserInfo.setCorporateName(baseCustomerInfo.getCorporateName());
			sessionUserInfo.setCity(baseCustomerInfo.getCity());
			sessionUserInfo.setRequestNumber(baseCustomerInfo.getRequestNumber());
			sessionUserInfo.setUseNumber(baseCustomerInfo.getUseNumber());
			sessionUserInfo.setRechargeCount(baseCustomerInfo.getRechargeCount());
			sessionUserInfo.setApplyCount(baseCustomerInfo.getApplyCount());
			// 把登陆的用户信息放到session里面去
			WebMainUtil.setSessionUserInfo(sessionUserInfo);
			// userId传到cookie里面需要经过des加密，然后用户访问所有url的时候需要加密这个userId去核对
			WebMainUtil.setSessionUserId(loginName);				
			//CookieUtil.setCookie(request, response,SessionAttributeName.USER_ID, AESUtil.encrypt(loginName,CookieUtil.userIdPassw), 1);
			CookieUtil.setCookie(request, response,SessionAttributeName.USER_ID, loginName, 1);
			this.writeJson(sessionUserInfo, true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error(e.getMessage());
			this.writeJson("服务异常", false);
		}
	}
	
	public void getInfo() {
		try {
			SessionUserInfo sessionUserInfo = WebMainUtil.getSessionUserInfo();
			if(sessionUserInfo == null) {
				this.writeJson("请先登录...", false);
				return;
			}
			BaseCustomerInfo baseCustomerInfo = baseCustomerInfoService.getCustomerInfo(sessionUserInfo.getNickName());
			this.writeJson(baseCustomerInfo, true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			this.writeJson("服务异常", false);
		}
	}
	
	
	 /**
     * 取top页面的数据
     * @remark 
     * @author shengfukang
     * @createDate 2015-11-18
     */
    public void queryTopData(){
    	SessionUserInfo sessionUserInfo = WebMainUtil.getSessionUserInfo();
    	this.writeJson(sessionUserInfo, sessionUserInfo == null ? false : true);
    }
    
	/**
	 * 
	 * @Title: userloginOut
	 * @Description: 用户退出登陆
	 * @throws
	 * @shengfukang 2015-11-18
	 */
	public void loginOut() throws Exception {
		try{
			session.invalidate();
			this.writeJson(null, true);
		}catch(Exception e){
			logger.error(e.getMessage());
			this.writeJson(null, false);
		}
	}
}
