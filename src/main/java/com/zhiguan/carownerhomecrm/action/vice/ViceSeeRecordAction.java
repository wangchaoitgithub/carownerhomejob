package com.zhiguan.carownerhomecrm.action.vice;

import com.zhiguan.carownerhomecrm.action.common.BaseAction;
import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.vice.VicePayNumber;
import com.zhiguan.carownerhomecrm.domain.vice.ViceSeeRecord;
import com.zhiguan.carownerhomecrm.mapper.vice.ViceSeeRecordMapper;
import com.zhiguan.carownerhomecrm.service.vice.VicePayNumberService;
import com.zhiguan.carownerhomecrm.service.vice.ViceSeeRecordService;
import com.zhiguan.commonNew.util.DateFormatUtil;
import com.zhiguan.commonNew.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import java.util.List;

public class ViceSeeRecordAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    private static Logger logger = (Logger) LogManager.getLogger("action");

    @Resource
    ViceSeeRecordService viceSeeRecordService;

    @Resource
    ViceSeeRecordMapper viceSeeRecordMapper;


    public void pageListAll(){
        try {
            String page = request.getParameter("page");
            String limit = request.getParameter("limit");
//            String likeName = request.getParameter("likeName");
            String userId = request.getParameter("userId");
            String starDate = request.getParameter("starDate");
            String endDate = request.getParameter("endDate");
            String orgIds = request.getParameter("orgIds");

            if(StringUtil.isEmpty(page)){
                page = "1";
            }
            if(StringUtil.isEmpty(limit)){
                limit = "10";
            }

            ViceSeeRecord entity = new ViceSeeRecord();
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
            if(!StringUtil.isEmpty(orgIds)){
                entity.setOrgId(Long.parseLong(orgIds));
            }
            entity.setCurrPage(Integer.parseInt(page));
            entity.setLimit(Integer.parseInt(limit));
            entity.setPageStart(PageUtils.getPageStart(Integer.parseInt(page),Integer.parseInt(limit)));

            PageUtils result = viceSeeRecordService.pageListAll(entity);
            /*test*/
            this.writeJson(result, true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            this.writeJson("服务异常", false);
        }
    }

    public void selectInfo(){
        try {
            List<ViceSeeRecord> info = viceSeeRecordMapper.selectInfo();
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
}


