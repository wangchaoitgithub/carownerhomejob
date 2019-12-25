package com.zhiguan.carownerhomecrm.action.secret;

import com.zhiguan.carownerhomecrm.action.common.BaseAction;
import com.zhiguan.carownerhomecrm.common.domain.SessionUserInfo;
import com.zhiguan.carownerhomecrm.common.main.WebMainUtil;
import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.secret.SecretWordRecord;
import com.zhiguan.carownerhomecrm.mapper.secret.SecretWordRecordMapper;
import com.zhiguan.carownerhomecrm.service.secret.SecretWordRecordService;
import com.zhiguan.commonNew.util.DateFormatUtil;
import com.zhiguan.commonNew.util.StringUtil;
import com.zhiguan.commonNew.web.UrlUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class SecretWordRecordAction extends BaseAction {

    private static final long serialVersionUID = 1L;
    private static Logger logger = (Logger) LogManager.getLogger("action");

    @Resource
    SecretWordRecordService secretWordRecordService;

    @Resource
    SecretWordRecordMapper secretWordRecordMapper;

    public void pageListAll(){
        try {
            String page = request.getParameter("page");
            String limit = request.getParameter("limit");
            String secretWord = request.getParameter("secretWord");
            String starDate = request.getParameter("starDate");
            String endDate = request.getParameter("endDate");
            String receiveUserId = request.getParameter("receiveUserId");
            String userId = request.getParameter("userId");

            if(StringUtil.isEmpty(page)){
                page = "1";
            }
            if(StringUtil.isEmpty(limit)){
                limit = "10";
            }

            SecretWordRecord entity = new SecretWordRecord();

            if(!StringUtil.isEmpty(starDate)){
                entity.setStarDate(DateFormatUtil.timeStringToDate(starDate));
            }
            if(!StringUtil.isEmpty(userId)){
                entity.setUserId(Long.parseLong(userId));
            }
            if(!StringUtil.isEmpty(receiveUserId)){
                entity.setReceiveUserId(Long.parseLong(receiveUserId));
            }
            if(!StringUtil.isEmpty(endDate)){
                entity.setEndDate(DateFormatUtil.timeStringToDate(endDate));
            }

            if(!StringUtil.isEmpty(secretWord)){        //模糊查询
                try {
                    secretWord = UrlUtil.decoder(secretWord);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                entity.setLikeName(secretWord);
            }
            entity.setCurrPage(Integer.parseInt(page));
            entity.setLimit(Integer.parseInt(limit));
            entity.setPageStart(PageUtils.getPageStart(Integer.parseInt(page),Integer.parseInt(limit)));

            PageUtils result = secretWordRecordService.pageListAll(entity);
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
            String status = request.getParameter("status");
            String secretWord = request.getParameter("secretWord");
            System.out.println(status);
            if(StringUtil.isEmpty(id)){
                this.writeJson("参数错误", false);
                return;
            }

            SecretWordRecord secretWordRecord = new SecretWordRecord();

            String lastOperator = WebMainUtil.getSessionUserInfo().getNickName();
            secretWordRecord.setLastOperator(lastOperator);
            secretWordRecord.setId(Long.parseLong(id));

            if(secretWord != null){
                secretWordRecord.setSecretWord(secretWord);
            }
            if(status != null){
                secretWordRecord.setStatus(status);
            }

            int result = secretWordRecordMapper.updateByPrimaryKeySelective(secretWordRecord);
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
