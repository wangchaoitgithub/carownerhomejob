package com.zhiguan.carownerhomecrm.action.user;

import com.zhiguan.carownerhomecrm.action.common.BaseAction;
import com.zhiguan.carownerhomecrm.common.main.WebMainUtil;
import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.user.UserBaseInfo;
import com.zhiguan.carownerhomecrm.mapper.user.UserBaseInfoMapper;
import com.zhiguan.carownerhomecrm.service.user.UserBaseInfoService;
import com.zhiguan.commonNew.util.StringUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

public class UserBaseInfoAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private static Logger logger = (Logger) LogManager.getLogger("action");
	
	@Resource
	UserBaseInfoService userBaseInfoService;

	@Resource
	UserBaseInfoMapper userBaseInfoMapper;
	
	public void pageListAll(){
		try {
			String page = request.getParameter("page");
			String limit = request.getParameter("limit");
			String likeName = request.getParameter("nickName");	//模糊查询字段  根据nickname
			String id = request.getParameter("uid");			//根据id查
			if(StringUtil.isEmpty(page)){
				page = "1";
			}
			if(StringUtil.isEmpty(limit)){
				limit = "10";
			}

			UserBaseInfo entity = new UserBaseInfo();
			if (!StringUtil.isEmpty(likeName)){
				entity.setLikeName(likeName);
			}
			if(!StringUtil.isEmpty(id)){
				if(!StringUtil.isNumber(id)){
					this.writeJson("userid必须为数字", false);
					return;
				}
				entity.setId(Long.parseLong(id));
			}

			entity.setCurrPage(Integer.parseInt(page));
			entity.setLimit(Integer.parseInt(limit));
			entity.setPageStart(PageUtils.getPageStart(Integer.parseInt(page),Integer.parseInt(limit)));
			
			PageUtils result = userBaseInfoService.pageListAll(entity);
			/*test*/
			this.writeJson(result, true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			this.writeJson("服务异常", false);
		}
	}


	/*修改用户信息*/
	public void update(){
		try {
			String id = request.getParameter("id");
			String state = request.getParameter("state");
			String name = request.getParameter("nickName");
			String viceNumber = request.getParameter("viceNumber");
			if(StringUtil.isEmpty(id)){
				this.writeJson("参数错误", false);
				return;
			}

			UserBaseInfo userBaseInfo = new UserBaseInfo();

			String lastOperator = WebMainUtil.getSessionUserInfo().getNickName();
			userBaseInfo.setLastOperator(lastOperator);
			userBaseInfo.setId(Long.parseLong(id));
			if (name != null){
				userBaseInfo.setNickName(name);
			}
			if (state != null){
				userBaseInfo.setState(state);
			}
			if(viceNumber != null){
				userBaseInfo.setViceNumber(viceNumber);
			}

			int result = userBaseInfoMapper.updateByPrimaryKeySelective(userBaseInfo);
			if(result == 1){
				this.writeJson("修改成功", true);
			}else{
				this.writeJson("修改失败", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			this.writeJson("服务异常", false);
		}
	}

	/*封掉用户信息*/
	public void delete(){
		try {
			String id = request.getParameter("id");
			 String isDeleted = request.getParameter("isDeleted");

			if(StringUtil.isEmpty(id)){
				this.writeJson("参数错误", false);
				return;
			}

			UserBaseInfo userBaseInfo = new UserBaseInfo();
			String lastOperator = WebMainUtil.getSessionUserInfo().getNickName();
			userBaseInfo.setLastOperator(lastOperator);
			userBaseInfo.setId(Long.parseLong(id));
			if (isDeleted.equals("已封号")){
				userBaseInfo.setIsDeleted("n");
			}else {
				userBaseInfo.setIsDeleted("y");
			}

			int result = userBaseInfoMapper.updateFordelete(userBaseInfo);
			if(result == 1){
				this.writeJson("修改成功", true);
			}else{
				this.writeJson("修改失败", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			this.writeJson("服务异常", false);
		}
	}

}
