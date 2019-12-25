package com.zhiguan.carownerhomecrm.action.communication;

import com.zhiguan.carownerhomecrm.action.common.BaseAction;
import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.communication.CommunicationCommentRecord;
import com.zhiguan.carownerhomecrm.service.communication.CommunicationCommentRecordService;
import com.zhiguan.commonNew.util.DateFormatUtil;
import com.zhiguan.commonNew.util.StringUtil;
import com.zhiguan.commonNew.web.UrlUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

public class CommunicationCommentRecordAction extends BaseAction {

    private static final long serialVersionUID = 1L;
    private static Logger logger = (Logger) LogManager.getLogger("action");

    @Resource
    CommunicationCommentRecordService communicationCommentRecordService;

    public void pageListAll(){
        try {
            String page = request.getParameter("page");
            String limit = request.getParameter("limit");
//            String likeName = request.getParameter("likeName");
//            String userId = request.getParameter("userId");
            String starDate = request.getParameter("starDate");
            String endDate = request.getParameter("endDate");
            String nickName = request.getParameter("nickName");
            if(StringUtil.isEmpty(page)){
                page = "1";
            }
            if(StringUtil.isEmpty(limit)){
                limit = "10";
            }

            CommunicationCommentRecord entity = new CommunicationCommentRecord();
            if(!StringUtil.isEmpty(starDate)){
                entity.setStarDate(DateFormatUtil.timeStringToDate(starDate));
            }
            if(!StringUtil.isEmpty(endDate)){
                entity.setEndDate(DateFormatUtil.timeStringToDate(endDate));
            }

            if(!StringUtil.isEmpty(nickName)){
                try {
                    nickName = UrlUtil.decoder(nickName);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                entity.setNickName(nickName);
            }
//            if(!StringUtil.isEmpty(likeName)){
//                entity.setLikeName(likeName);
//            }
            entity.setCurrPage(Integer.parseInt(page));
            entity.setLimit(Integer.parseInt(limit));
            entity.setPageStart(PageUtils.getPageStart(Integer.parseInt(page),Integer.parseInt(limit)));

            PageUtils result = communicationCommentRecordService.pageListAll(entity);
            /*test*/
            this.writeJson(result, true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            this.writeJson("服务异常", false);
        }
    }
}