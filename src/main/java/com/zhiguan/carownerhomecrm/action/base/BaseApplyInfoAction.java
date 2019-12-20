package com.zhiguan.carownerhomecrm.action.base;

import com.zhiguan.commonNew.util.StringUtil;
import com.zhiguan.carownerhomecrm.action.common.BaseAction;
import com.zhiguan.carownerhomecrm.common.main.WebMainUtil;
import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.base.BaseApplyInfo;
import com.zhiguan.carownerhomecrm.mapper.base.BaseApplyInfoMapper;
import com.zhiguan.carownerhomecrm.service.base.BaseApplyInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import java.util.List;

public class BaseApplyInfoAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private static Logger logger = (Logger) LogManager.getLogger("action");
	
	@Resource
	BaseApplyInfoService baseApplyInfoService;
	@Resource
	BaseApplyInfoMapper baseApplyInfoMapper;
	
	public void pageListAll(){
		try {
			String page = request.getParameter("page");
			String limit = request.getParameter("limit");
			String operatorsId = request.getParameter("operatorsId");
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
			
			BaseApplyInfo entity = new BaseApplyInfo();
			if(!StringUtil.isEmpty(operatorsId)){
				entity.setOperatorsId(Long.parseLong(operatorsId));
			}
			if(!StringUtil.isEmpty(likeName)){
				entity.setLikeName(likeName);
			}
			entity.setCustomerId(Long.parseLong(customerId));
			entity.setCurrPage(Integer.parseInt(page));
			entity.setLimit(Integer.parseInt(limit));
			entity.setPageStart(PageUtils.getPageStart(Integer.parseInt(page),Integer.parseInt(limit)));
			
			PageUtils result = baseApplyInfoService.pageListAll(entity);
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
			
			List<BaseApplyInfo> info = baseApplyInfoMapper.selectInfo(Long.parseLong(customerId));
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
	
	public void update(){
		try {
			String id = request.getParameter("id");
			String customerId = request.getParameter("customerId");
			String desc = request.getParameter("desc");
			if(StringUtil.isEmpty(id) || StringUtil.isEmpty(customerId)){
				this.writeJson("参数错误", false);
				return;
			}
			String lastOperator = WebMainUtil.getSessionUserInfo().getNickName();
			int result = baseApplyInfoMapper.update(Long.parseLong(id), Long.parseLong(customerId), desc, lastOperator);
			if(result == 1){
				this.writeJson("修改成功", true);
			}else{
				this.writeJson(null, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			this.writeJson("服务异常", false);
		}
	}
}
