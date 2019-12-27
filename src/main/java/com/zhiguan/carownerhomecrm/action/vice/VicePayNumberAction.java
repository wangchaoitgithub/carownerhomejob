package com.zhiguan.carownerhomecrm.action.vice;

import com.zhiguan.carownerhomecrm.action.common.BaseAction;
import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.vice.VicePayNumber;
import com.zhiguan.carownerhomecrm.mapper.vice.VicePayNumberMapper;
import com.zhiguan.carownerhomecrm.service.vice.VicePayNumberService;
import com.zhiguan.commonNew.util.DateFormatUtil;
import com.zhiguan.commonNew.util.StringUtil;
import com.zhiguan.commonNew.web.UrlUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class VicePayNumberAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    private static Logger logger = (Logger) LogManager.getLogger("action");

    @Resource
    VicePayNumberService vicePayNumberService;

    @Resource
    VicePayNumberMapper vicePayNumberMapper;

    public void pageListAll(){
        try {
            String page = request.getParameter("page");
            String limit = request.getParameter("limit");
//            String likeName = request.getParameter("likeName");
            String userId = request.getParameter("userId");
            String userName = request.getParameter("userName");
            String starDate = request.getParameter("starDate");
            String endDate = request.getParameter("endDate");
            String orgIds = request.getParameter("orgIds");
            String payWeOrderId = request.getParameter("payWeOrderId");
            String payWeixinOrderId = request.getParameter("payWeixinOrderId");
            String viceNumber = request.getParameter("viceNumber");
            String expressPhone = request.getParameter("expressPhone");
            String expressName = request.getParameter("expressName");
            String state = request.getParameter("state");
            String orgName = request.getParameter("orgName");

            if(StringUtil.isEmpty(page)){
                page = "1";
            }
            if(StringUtil.isEmpty(limit)){
                limit = "10";
            }

            VicePayNumber entity = new VicePayNumber();
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
            if(!StringUtil.isEmpty(userName)){
                entity.setNickName(userName);
            }
            if(!StringUtil.isEmpty(viceNumber)){
                entity.setViceNumber(viceNumber);
            }
            if(!StringUtil.isEmpty(expressPhone)){
                entity.setExpressPhone(expressPhone);
            }
            if(!StringUtil.isEmpty(expressName)){
                try {
                    expressName = UrlUtil.decoder(expressName);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                entity.setExpressName(expressName);
            }

            if(!StringUtil.isEmpty(orgIds)){
                entity.setOrgId(Long.parseLong(orgIds));
            }
            if(!StringUtil.isEmpty(payWeOrderId)){
                entity.setPayWeOrderId(payWeOrderId);
            }
            if(!StringUtil.isEmpty(payWeixinOrderId)){
                entity.setPayWeixinOrderId(payWeixinOrderId);
            }
            if(!StringUtil.isEmpty(state)){
                if (!state.equals("null")){
                    entity.setState(state);
                }
            }

            if(!StringUtil.isEmpty(orgName)){
                try {
                    orgName = UrlUtil.decoder(orgName);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                entity.setOrgName(orgName);
            }

            entity.setCurrPage(Integer.parseInt(page));
            entity.setLimit(Integer.parseInt(limit));
            entity.setPageStart(PageUtils.getPageStart(Integer.parseInt(page),Integer.parseInt(limit)));

            PageUtils result = vicePayNumberService.pageListAll(entity);
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
            List<VicePayNumber> info = vicePayNumberMapper.selectInfo();
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
