package com.zhiguan.carownerhomecrm.action.customer;

import com.zhiguan.commonNew.util.StringUtil;
import com.zhiguan.carownerhomecrm.action.common.BaseAction;
import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.customer.CustomerRechargeRecord;
import com.zhiguan.carownerhomecrm.service.customer.CustomerRechargeRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;

public class CustomerRechargeRecordAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private static Logger logger = (Logger) LogManager.getLogger("action");
	
	@Resource
	CustomerRechargeRecordService customerRechargeRecordService;
	
	public void pageListAll(){
		try {
			String page = request.getParameter("page");
			String limit = request.getParameter("limit");
			String likeName = request.getParameter("likeName");
			String customerId = request.getParameter("customerId");
			
			if(StringUtil.isEmpty(customerId)){
				this.writeJson("参数错误", false);
				return;
			}
			if(StringUtil.isEmpty(page)){
				page = "1";
			}
			if(StringUtil.isEmpty(limit)){
				limit = "10";
			}
			
			CustomerRechargeRecord entity = new CustomerRechargeRecord();
			if(!StringUtil.isEmpty(likeName)){
				entity.setLikeName(likeName);
			}
			entity.setCustomerId(Long.parseLong(customerId));
			entity.setCurrPage(Integer.parseInt(page));
			entity.setLimit(Integer.parseInt(limit));
			entity.setPageStart(PageUtils.getPageStart(Integer.parseInt(page),Integer.parseInt(limit)));
			
			PageUtils result = customerRechargeRecordService.pageListAll(entity);
			this.writeJson(result, true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			this.writeJson("服务异常", false);
		}
	}
}
