package com.zhiguan.carownerhomecrm.action.process;

import com.zhiguan.carownerhomecrm.action.common.BaseAction;
import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.process.ProcessBaseRecord;
import com.zhiguan.carownerhomecrm.service.process.ProcessBaseRecordService;
import com.zhiguan.commonNew.util.DateFormatUtil;
import com.zhiguan.commonNew.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;

public class ProcessBaseRecordAction extends BaseAction {

    private static final long serialVersionUID = 1L;
    private static Logger logger = (Logger) LogManager.getLogger("action");

    @Resource
    ProcessBaseRecordService processBaseRecordService;

    public void pageListAll(){
        try {
            String page = request.getParameter("page");
            String limit = request.getParameter("limit");
//            String likeName = request.getParameter("likeName");
            String userId = request.getParameter("userId");
            String starDate = request.getParameter("starDate");
            String endDate = request.getParameter("endDate");
            String nextCheckUserId = request.getParameter("nextCheckUserId");
            String businessType = request.getParameter("businessType");
            String resul = request.getParameter("result");
            String status = request.getParameter("status");
            String lastProcessId = request.getParameter("lastProcessId");





            if(StringUtil.isEmpty(page)){
                page = "1";
            }
            if(StringUtil.isEmpty(limit)){
                limit = "10";
            }

            ProcessBaseRecord entity = new ProcessBaseRecord();
//            if(!StringUtil.isEmpty(likeName)){
//                entity.setLikeName(likeName);
//            }
            if(!StringUtil.isEmpty(starDate)){
                entity.setStarDate(DateFormatUtil.timeStringToDate(starDate));
            }
            if(!StringUtil.isEmpty(endDate)){
                entity.setEndDate(DateFormatUtil.timeStringToDate(endDate));
            }

            if(!StringUtil.isEmpty(userId)){
                entity.setUserId(Long.parseLong(userId));
            }
            if(!StringUtil.isEmpty(nextCheckUserId)){
                entity.setNextCheckUserId(Long.parseLong(nextCheckUserId));
            }
            if(!StringUtil.isEmpty(businessType)){
                if (!businessType.equals("null") ){
                    if (businessType.equals("认证")){
                        entity.setBusinessType("auth");
                    }else if (businessType.equals("用户信息修改")){
                        entity.setBusinessType("userInfoUpdate");
                    }else if (businessType.equals("副牌")){
                        entity.setBusinessType("buyViceNumber");
                    }else if (businessType.equals("求助")){
                        entity.setBusinessType("help");
                    }else if (businessType.equals("发帖")){
                        entity.setBusinessType("forum");
                    }else if (businessType.equals("评论")){
                        entity.setBusinessType("comment");
                    }else if (businessType.equals("导入群活动")){
                        entity.setBusinessType("addGroupActivity");
                    }
                }
            }
            if(!StringUtil.isEmpty(resul)){
                if (!resul.equals("null") ){
                    entity.setResult(resul);
                }
            }
            if(!StringUtil.isEmpty(status)){
                if (!status.equals("null") && !status.equals("nulls") ){
                    entity.setStatus(status);
                }else if (status.equals("nulls")){
                    entity.setStatusNull(status);
                }
            }
            if(!StringUtil.isEmpty(lastProcessId)){
                entity.setLastProcessId(Long.parseLong(lastProcessId));
            }





            entity.setCurrPage(Integer.parseInt(page));
            entity.setLimit(Integer.parseInt(limit));
            entity.setPageStart(PageUtils.getPageStart(Integer.parseInt(page),Integer.parseInt(limit)));

            PageUtils result = processBaseRecordService.pageListAll(entity);
            /*test*/
            this.writeJson(result, true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            this.writeJson("服务异常", false);
        }
    }
}
