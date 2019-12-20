package com.zhiguan.carownerhomecrm.action.common;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Vector;

/**
 * 
 * @author shengfukang
 * @createDate：2014-10-20
 */
public class BaseAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 7834736236105975470L;

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	private Vector<String> errMessage;
	protected int start;
	protected int limit;

	public void setServletRequest(HttpServletRequest req) {
		this.request = req;
		this.session = request.getSession();
	}

	public void setServletResponse(HttpServletResponse res) {
		this.response = res;
	}
	
	/**
	 * 通过request.getRemoteAddr()可以获取IP，但是如果通过代理服务器后，这个就是代理服务器的地址了
	 * 所以从header里面去最靠谱
	 * @remark 
	 * @author shengfukang
	 * @createDate 2014-12-8
	 * @return
	 */
	public String getIp(){
	  String ip = request.getHeader("x-forwarded-for");
	  if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {       
		  ip = request.getHeader("Proxy-Client-IP");
	  }
	  if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {       
		  ip = request.getHeader("WL-Proxy-Client-IP");
	  }
	  if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {       
	      ip = request.getRemoteAddr();
	  }
	 return ip;
	}
	
	public void writeError() {
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.write("{success:false,msg:'");
			for (String msg : errMessage) {
				writer.write(msg);
				writer.write("<br/>");
			}
			errMessage.clear();
			writer.write("'}");
			writer.flush();
			writer.close();
		} catch (java.io.IOException exc) {
			exc.printStackTrace();
		}
	}

	public void writeJson(Object obj) {
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.write(JSONUtil.serialize(obj));
			writer.flush();
			writer.close();
		} catch (java.io.IOException exc) {
			exc.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void writeJson(Object obj, boolean isSuccess) {
		try {
			response.setContentType("application/json;charset=utf-8");
			PrintWriter writer = response.getWriter();
			if (isSuccess) {
				writer.write("{\"success\":true,\"data\":");
			} else {
				writer.write("{\"success\":false,\"data\":");
			}
			//writer.write(JSONUtil.serialize(obj));
			writer.write(JSONObject.toJSONString(obj,SerializerFeature.WriteMapNullValue));
			writer.write("}");
			writer.flush();
			writer.close();
		} catch (java.io.IOException exc) {
			exc.printStackTrace();
		}
	}

	public void write(String outStr) {
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.write(outStr);
			writer.flush();
			writer.close();
		} catch (java.io.IOException exc) {
			exc.printStackTrace();
		}
	}

	public void writeXml(String outStr) {
		try {
			response.setContentType("text/xml;charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.write(outStr);
			writer.flush();
			writer.close();
		} catch (java.io.IOException exc) {
			exc.printStackTrace();
		}
	}

	protected void addError(String msgId, Object... params) {
		if (errMessage == null) {
			errMessage = new Vector<String>();
		}
		errMessage.add(String.format(getText(msgId), params));
	}
	
	protected void addErrorWithMsg(String msg, Object... params) {
		if (errMessage == null) {
			errMessage = new Vector<String>();
		}
		errMessage.add(String.format(msg, params));
	}

	public boolean isInvalid() {
		if (errMessage != null && errMessage.size() > 0) {
			writeError();
			return true;
		}
		return false;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	
}
