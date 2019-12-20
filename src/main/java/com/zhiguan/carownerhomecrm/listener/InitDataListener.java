package com.zhiguan.carownerhomecrm.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class InitDataListener implements HttpSessionListener {
	private static Logger logger = LogManager.getLogger();
	static{
        //tomcat启动执行的任务代码
	}


	public void sessionCreated(HttpSessionEvent httpSessionEvent) {

	}

	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

	}
}
