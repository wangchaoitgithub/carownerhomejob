package com.zhiguan.carownerhomecrm.action.phone;

import com.zhiguan.commonNew.util.DateFormatUtil;
import com.zhiguan.commonNew.util.StringUtil;
import com.zhiguan.carownerhomecrm.action.common.BaseAction;
import com.zhiguan.carownerhomecrm.action.common.excelUtil;
import com.zhiguan.carownerhomecrm.common.domain.SessionUserInfo;
import com.zhiguan.carownerhomecrm.common.main.WebMainUtil;
import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.base.BaseApplyInfo;
import com.zhiguan.carownerhomecrm.domain.phone.PhoneSmsRecord;
import com.zhiguan.carownerhomecrm.mapper.base.BaseApplyInfoMapper;
import com.zhiguan.carownerhomecrm.mapper.phone.PhoneSmsRecordMapper;
import com.zhiguan.carownerhomecrm.service.phone.PhoneSmsRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import java.util.Date;
import java.util.List;

public class PhoneSmsRecordAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private static Logger logger = (Logger) LogManager.getLogger("action");
	
	@Resource
	PhoneSmsRecordService phoneSmsRecordService;
	@Resource
	BaseApplyInfoMapper baseApplyInfoMapper;
	@Resource
	PhoneSmsRecordMapper phoneSmsRecordMapper;
	
	public void pageListAll(){
		try {
			String page = request.getParameter("page");
			String limit = request.getParameter("limit");
			String appId = request.getParameter("likeName");
			String starDate = request.getParameter("starDate");
			String endDate = request.getParameter("endDate");
			
			SessionUserInfo sessionUserInfo = WebMainUtil.getSessionUserInfo();
			if(sessionUserInfo == null) {
				this.writeJson("请先登录...", false);
				return;
			}
			
			if(StringUtil.isEmpty(page)){
				page = "1";
			}
			if(StringUtil.isEmpty(limit)){
				limit = "10";
			}
			
			PhoneSmsRecord entity = new PhoneSmsRecord();
			entity.setCustomerId(sessionUserInfo.getUserId());
			if(!StringUtil.isEmptyzhaopin(appId)){
				entity.setAppid(appId);
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
			
			PageUtils result = phoneSmsRecordService.pageListAll(entity);
			this.writeJson(result, true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			this.writeJson("服务异常", false);
		}
	}
	
	public void selectInfo(){
		try {
			String customerId = request.getParameter("customerId");
			if(StringUtil.isEmpty(customerId)){
				this.writeJson("参数错误", false);
				return;
			}
			
			SessionUserInfo sessionUserInfo = WebMainUtil.getSessionUserInfo();
			if(sessionUserInfo == null) {
				this.writeJson("请先登录...", false);
				return;
			}
			
			List<BaseApplyInfo> info = baseApplyInfoMapper.selectInfo(sessionUserInfo.getUserId());
			if(info != null && info.size() > 0){
				this.writeJson(info, true);
			}else{
				this.writeJson(null, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			this.writeJson("服务异常", false);
		}
	}
	//数据导出
	public void excelExport() {
		try {
			String appid = request.getParameter("applyName");
			String starDate = request.getParameter("starDate");
			String endDate = request.getParameter("endDate");
			
			SessionUserInfo sessionUserInfo = WebMainUtil.getSessionUserInfo();
			if(sessionUserInfo == null) {
				this.writeJson("请先登录...", false);
				return;
			}
			PhoneSmsRecord phoneSmsRecord = new PhoneSmsRecord();
			if(!StringUtil.isEmptyzhaopin(appid)){
				phoneSmsRecord.setAppid(appid);
			}
			phoneSmsRecord.setCustomerId(sessionUserInfo.getUserId());
			phoneSmsRecord.setStarDate(DateFormatUtil.timeStringToDate(starDate));
			phoneSmsRecord.setEndDate(DateFormatUtil.timeStringToDate(endDate));
			
			List<PhoneSmsRecord> list = phoneSmsRecordMapper.pageListAll(phoneSmsRecord);
			//设置文件格式 
			response.setContentType("application/x-execl");
			//Content-Disposition就是当用户想把请求所得的内容存为一个文件的时候提供一个默认的文件名
			//设置响应头   attachment表示为附件  并指定文件名称
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(("数据请求详情"+DateFormatUtil.formatToStringDateEn(new Date())+".xls").getBytes(), "ISO-8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();
			excelUtil.exportOrder(list,outputStream);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			this.writeJson(null, false);
		}
	}
}
