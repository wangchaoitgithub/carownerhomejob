package com.zhiguan.carownerhomecrm.common.main;


import com.zhiguan.carownerhomecrm.common.constant.SessionAttributeName;
import com.zhiguan.carownerhomecrm.common.domain.SessionUserInfo;
import org.apache.struts2.ServletActionContext;
import com.zhiguan.commonNew.biz.pojo.BasePojoInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * web中最常用的方法
 * @author shengfukang
 * @createDate：2014-11-5
 */
public class WebMainUtil {
	
	/**
	 * 取session中的userId
	 * @remark 
	 * @author shengfukang
	 * @createDate 2014-11-5
	 * @return
	 */
	public static long getUserId(){
		SessionUserInfo sessionUserInfo = (SessionUserInfo)getSession().getAttribute(SessionAttributeName.USER_INFO);
		return Integer.parseInt(sessionUserInfo.getUserId().toString());
	}
	
	/**
	 * 取session中的组织ID
	 * @remark 
	 * @author shengfukang
	 * @createDate 2014-11-5
	 * @return
	 * @throws Exception 
	 */
	public static int getNamespace() throws Exception{
		Object namespce = getSession().getAttribute(SessionAttributeName.NAMESPACE);
		if(namespce == null){
			throw new Exception("no login!");
		}else{
			return new Integer(namespce.toString());
		}
	}
	
	/**
	 * 取session中的组织类型
	 * @remark 
	 * @author shengfukang
	 * @createDate 2014-11-5
	 * @return
	 *//*
	public static String getOrganizationType(){
		SessionUserInfo sessionUserInfo = (SessionUserInfo)getSession().getAttribute(SessionAttributeName.USER_INFO);
		return sessionUserInfo.getOrganizationType().toString();
	}*/
	
	/**
	 * 取session中的userId
	 * @remark 
	 * @author shengfukang
	 * @createDate 2014-11-5
	 * @return
	 */
	public static HttpSession getSession(){
		return ServletActionContext.getRequest().getSession();
	}
	
	/**
	 * 取session中的userInfo对象
	 * @remark 
	 * @author shengfukang
	 * @createDate 2014-11-5
	 * @return
	 */
	public static SessionUserInfo getSessionUserInfo(){
		Object session = getSession().getAttribute(SessionAttributeName.USER_INFO);
		if(session == null){
			return null;
		}else{
			return (SessionUserInfo)session;
		}
	}
	
	/**
	 * 设置当前登陆的是哪个namespace
	 * @remark 
	 * @author shengfukang
	 * @createDate 2014-11-19
	 * @return
	 */
	public static void setSessionUserId(String userId){
		getSession().setAttribute(SessionAttributeName.USER_ID,userId);
	}
	
	/**
	 * 取session中的userInfo对象
	 * @remark 
	 * @author shengfukang
	 * @createDate 2014-11-5
	 * @return
	 */
	public static void setSessionNamespace(int namespace){
		getSession().setAttribute(SessionAttributeName.NAMESPACE,namespace);
	}
	
	/**
	 * 取session中的userInfo对象
	 * @remark 
	 * @author shengfukang
	 * @createDate 2014-11-5
	 * @return
	 */
	public static void setSessionUserInfo(SessionUserInfo sessionUserInfo){
		getSession().setAttribute(SessionAttributeName.USER_INFO,sessionUserInfo);
	}
	
	public static List<?> filterNamespace(List<?> list) throws Exception{
		List<BasePojoInterface> deleteList = new ArrayList<BasePojoInterface>();
		for(int i = 0; i <list.size(); i++){
			if(((BasePojoInterface)list.get(i)).getNamespace() != getNamespace()){
				deleteList.add((BasePojoInterface)(list.get(i)));
			}
		}
		list.removeAll(deleteList);
		return list;
	}
	
	/**
	 * 获取
	 * @remark 
	 * @author shengfukang
	 * @createDate 2014-11-24
	 * @return
	 */
	public static String getSystemUrl(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String url = request.getRequestURL().toString();
		String path = request.getServletPath();
		String result = url.substring(0, url.indexOf(path));
		return result;
	}
}
