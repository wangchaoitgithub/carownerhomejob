package com.zhiguan.carownerhomecrm.action.membe;

import com.zhiguan.carownerhomecrm.action.common.BaseAction;
import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.membe.MembePayOrder;
import com.zhiguan.carownerhomecrm.service.membe.MembePayOrderService;
import com.zhiguan.commonNew.util.DateFormatUtil;
import com.zhiguan.commonNew.util.StringUtil;
import com.zhiguan.commonNew.web.UrlUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

public class MembePayOrderAction extends BaseAction {

    private static final long serialVersionUID = 1L;
    private static Logger logger = (Logger) LogManager.getLogger("action");

    @Resource
    MembePayOrderService membePayOrderService;



    public void pageListAll(){
        try {
            String page = request.getParameter("page");
            String limit = request.getParameter("limit");
            String likeName = request.getParameter("likeName");
            String payWeOrderId = request.getParameter("payWeOrderId");
            String payWeixinOrderId = request.getParameter("payWeixinOrderId");
            String starDate = request.getParameter("starDate");
            String endDate = request.getParameter("endDate");
            String createOperator = request.getParameter("createOperator");
            String userId = request.getParameter("userId");
            String productName = request.getParameter("productName");
            String productId = request.getParameter("productId");
            String payWeixinStatus = request.getParameter("payWeixinStatus");
            String status = request.getParameter("status");
            String orgName = request.getParameter("orgName");
            String orgId = request.getParameter("orgId");

            if(StringUtil.isEmpty(page)){
                page = "1";
            }
            if(StringUtil.isEmpty(limit)){
                limit = "10";
            }

            MembePayOrder entity = new MembePayOrder();
            if(!StringUtil.isEmpty(likeName)){
                entity.setLikeName(likeName);
            }
            if(!StringUtil.isEmpty(payWeOrderId)){
                entity.setPayWeOrderId(payWeOrderId);
            }
            if(!StringUtil.isEmpty(payWeixinOrderId)){
                entity.setPayWeixinOrderId(payWeixinOrderId);
            }
            if(!StringUtil.isEmpty(starDate)){
                entity.setStarDate(DateFormatUtil.timeStringToDate(starDate));
            }
            if(!StringUtil.isEmpty(endDate)){
                entity.setEndDate(DateFormatUtil.timeStringToDate(endDate));
            }
            if(!StringUtil.isEmpty(createOperator)){
                try {
                    createOperator = UrlUtil.decoder(createOperator);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                entity.setCreateOperator(createOperator);
            }
            if(!StringUtil.isEmpty(userId)){
                entity.setUserId(Long.parseLong(userId));
            }
            if(!StringUtil.isEmpty(productId)){
                entity.setProductId(Long.parseLong(productId));
            }
            if(!StringUtil.isEmpty(productName)){
                try {
                    productName = UrlUtil.decoder(productName);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                entity.setProductName(productName);
            }
            if(!StringUtil.isEmpty(payWeixinStatus)){
                if (!payWeixinStatus.equals("null")){
                    entity.setPayWeixinStatus(payWeixinStatus);
                }
            }
            if(!StringUtil.isEmpty(status)){
                if (!status.equals("null")){
                    entity.setStatus(status);
                }
            }
            if(!StringUtil.isEmpty(orgId)){
                entity.setOrgId(orgId);
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

            PageUtils result = membePayOrderService.pageListAll(entity);
            /*test*/
            this.writeJson(result, true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            this.writeJson("服务异常", false);
        }
    }
}

