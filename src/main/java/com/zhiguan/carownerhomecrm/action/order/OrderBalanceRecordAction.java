package com.zhiguan.carownerhomecrm.action.order;

import com.zhiguan.commonNew.util.DateFormatUtil;
import com.zhiguan.commonNew.util.StringUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zhiguan.carownerhomecrm.action.common.BaseAction;
import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.order.OrderBalanceRecord;
import com.zhiguan.carownerhomecrm.service.order.OrderBalanceRecordService;

import javax.annotation.Resource;

public class OrderBalanceRecordAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private static Logger logger = (Logger) LogManager.getLogger("action");
	
	@Resource
	OrderBalanceRecordService orderBalanceRecordService;
	
	public void pageListAll(){
		try {
			String page = request.getParameter("page");
			String limit = request.getParameter("limit");
			String customerId = request.getParameter("customerId");
			String applyId = request.getParameter("likeName");
			String starDate = request.getParameter("starDate");
			String endDate = request.getParameter("endDate");

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
			
			OrderBalanceRecord entity = new OrderBalanceRecord();
			
			entity.setCustomerId(Long.parseLong(customerId));
			if(!StringUtil.isEmpty(applyId)){
				entity.setApplyId(Long.parseLong(applyId));
			}
			if(!StringUtil.isEmpty(starDate)){
				entity.setStarDate(DateFormatUtil.timeStringToDate(starDate));
			}
			if(!StringUtil.isEmpty(endDate)){
				entity.setEndDate(DateFormatUtil.timeStringToDate(endDate));
			}
			entity.setCurrPage(Integer.parseInt(page));
			entity.setLimit(Integer.parseInt(limit));
			entity.setPageStart(PageUtils.getPageStart(Integer.parseInt(page),Integer.parseInt(limit)));
			
			PageUtils result = orderBalanceRecordService.pageListAll(entity);
			this.writeJson(result, true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			this.writeJson("服务异常", false);
		}
	}
}
