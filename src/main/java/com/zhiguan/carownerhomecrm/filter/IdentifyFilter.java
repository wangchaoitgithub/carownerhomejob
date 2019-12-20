package com.zhiguan.carownerhomecrm.filter;

import javax.servlet.Filter;
import com.zhiguan.commonNew.util.AESUtil;
import com.zhiguan.commonNew.util.StringUtil;
import com.zhiguan.carownerhomecrm.common.constant.SessionAttributeName;
import com.zhiguan.carownerhomecrm.common.main.CookieUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 安全登录的校验过滤器
 * 
 * @author shengfukang
 * @createDate：2014-11-19
 */
public class IdentifyFilter implements Filter {
	private static Logger logger = LogManager.getLogger();
	private FilterConfig config = null;
	private String loginPage;

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String userName = null;
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String reqUrl = req.getRequestURL().toString();
		String path = req.getServletPath();
		String loginPage = reqUrl.replace(path, this.loginPage);
		// 从cookie中取出userId,如果有，而且是合法的，就让他登陆，如果是非法的，则不让他登陆并跳到login页面
		userName = isAuthenticated(req, res);
		logger.info("userId:" + userName + ",url:" + reqUrl);
		if (userName == null) {
			logger.warn("no logined to url: userId:" + userName + ",url:"
					+ reqUrl);
			res.sendRedirect(loginPage);
			return;
		} else {
			// logger.info("userId:" + userId + ",url:" + reqUrl);
			// String sessionId = CookieUtil.getCookie(req,"JSESSIONID") ;
			// 如果session为空则已经失效，则需要返回到登陆页面
			// 无效 登出 req.getSession().invalidate();
			if (null == req.getSession(false)
					|| null == req.getSession().getAttribute(
							SessionAttributeName.USER_INFO)) {
				// if (false == req.getSession(true).isNew()) {
				// System.out.println("session已经过期");
				res.sendRedirect(loginPage);
				return;
				// }
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig c) throws ServletException {
		// TODO Auto-generated method stub
		this.config = c;
		loginPage = config.getInitParameter("loginPage");
		if (loginPage == null) {
			throw new ServletException("loginPage init param missing");
		}
	}

	/**
	 * 根据cookie判断用户是否已登录
	 * 
	 * @param request
	 * @return
	 */
	private String isAuthenticated(HttpServletRequest request,
			HttpServletResponse response) {
		String isAuthenticated = null;
		try {/*
			 * //IAuthorityService authService = new AuthorityServiceImpl();
			 * if(authService.isOverTimeCookie(request, response)){ //true,超时
			 * return null; }
			 */
			String encryptUserId = CookieUtil.getCookie(request,
					SessionAttributeName.USER_ID);
			logger.info(encryptUserId);
			if (!StringUtil.isEmpty(encryptUserId)) {
				logger.info(CookieUtil.userIdPassw);
				String userId = AESUtil.decrypt(encryptUserId,
						CookieUtil.userIdPassw);
				logger.info(userId);
				return userId;

			} else {
				return null;
			}
		} catch (Exception e) {
			isAuthenticated = null;
			logger.error(e.getMessage());
		}
		return isAuthenticated;
	}

}
