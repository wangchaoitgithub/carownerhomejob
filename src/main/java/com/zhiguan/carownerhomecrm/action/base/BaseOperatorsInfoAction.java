package com.zhiguan.carownerhomecrm.action.base;

import com.zhiguan.carownerhomecrm.action.common.BaseAction;
import com.zhiguan.carownerhomecrm.domain.base.BaseOperatorsInfo;
import com.zhiguan.carownerhomecrm.mapper.base.BaseOperatorsInfoMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import java.util.List;

public class BaseOperatorsInfoAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private static Logger logger = (Logger) LogManager.getLogger("action");
	
	@Resource
	BaseOperatorsInfoMapper baseOperatorsInfoMapper;
	
	// 查询有效的运营商ID,简称
	public void selectInfo(){
		try {
			List<BaseOperatorsInfo> result = baseOperatorsInfoMapper.selectInfo();
			this.writeJson(result, true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			this.writeJson("服务异常", false);
		}
	}
}
