package com.zhiguan.carownerhomecrm.action.org;

import com.zhiguan.carownerhomecrm.action.common.BaseAction;
import com.zhiguan.carownerhomecrm.common.main.WebMainUtil;
import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.org.OrgBaseInfo;
import com.zhiguan.carownerhomecrm.mapper.org.OrgBaseInfoMapper;
import com.zhiguan.carownerhomecrm.service.org.OrgBaseInfoService;
import com.zhiguan.commonNew.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

public class OrgBaseInfoAction extends BaseAction {

    private static final long serialVersionUID = 1L;
    private static Logger logger = (Logger) LogManager.getLogger("action");

    @Resource
    OrgBaseInfoService orgBaseInfoService;

    @Resource
    OrgBaseInfoMapper orgBaseInfoMapper;

    public void pageListAll(){
        try {
            String page = request.getParameter("page");
            String limit = request.getParameter("limit");
            String likeName = request.getParameter("likeName");

            if(StringUtil.isEmpty(page)){
                page = "1";
            }
            if(StringUtil.isEmpty(limit)){
                limit = "10";
            }

            OrgBaseInfo entity = new OrgBaseInfo();
            if(!StringUtil.isEmpty(likeName)){
                entity.setLikeName(likeName);
            }
            entity.setCurrPage(Integer.parseInt(page));
            entity.setLimit(Integer.parseInt(limit));
            entity.setPageStart(PageUtils.getPageStart(Integer.parseInt(page),Integer.parseInt(limit)));

            PageUtils result = orgBaseInfoService.pageListAll(entity);
            /*test*/
            this.writeJson(result, true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            this.writeJson("服务异常", false);
        }
    }


    /*修改记录信息*/
    public void update(){
        try {
            String id = request.getParameter("id");
            String orgName = request.getParameter("orgName");
            String orgDesc = request.getParameter("orgDesc");
            String orgType = request.getParameter("orgType");
            String groupUserPhonenum = request.getParameter("groupUserPhonenum");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String createOperator = request.getParameter("createOperator");
            String groupUserid = request.getParameter("groupUserid");
            String nickName = request.getParameter("nickName");
            String groupUserWeixinid = request.getParameter("groupUserWeixinid");
            String groupIfHighAuthority = request.getParameter("groupIfHighAuthority");
            String managerUserid = request.getParameter("managerUserid");
            String logoImgUrlMax = request.getParameter("logoImgUrlMax");
            String weixinPublicName = request.getParameter("weixinPublicName");
            String personCountAuth = request.getParameter("personCountAuth");
            String personCountRead = request.getParameter("personCountRead");
            String carTypeId = request.getParameter("carTypeId");
            String viceNumberMin = request.getParameter("viceNumberMin");
            String viceNumberMax = request.getParameter("viceNumberMax");
            String welcomeWord = request.getParameter("welcomeWord");
            String orgRule = request.getParameter("orgRule");
            String province = request.getParameter("province");
            String weixinPublicId = request.getParameter("weixinPublicId");
            String weixinPublicLogo = request.getParameter("weixinPublicLogo");
            String weixinPublicHttpUrl = request.getParameter("weixinPublicHttpUrl");
            String isDefault = request.getParameter("isDefault");

            if(StringUtil.isEmpty(id)){
                this.writeJson("参数错误", false);
                return;
            }

            OrgBaseInfo orgBaseInfo = new OrgBaseInfo();

            String lastOperator = WebMainUtil.getSessionUserInfo().getNickName();
            orgBaseInfo.setLastOperator(lastOperator);      //可做判断
            orgBaseInfo.setId(Long.parseLong(id));
            if (orgName != null){
                orgBaseInfo.setOrgName(orgName);
            }
            if(orgDesc != null){
                orgBaseInfo.setOrgDesc(orgDesc);
            }
            if(orgType != null){
                orgBaseInfo.setOrgType(orgType);
            }
            if(groupUserPhonenum != null){
                orgBaseInfo.setGroupUserPhonenum(Long.parseLong(groupUserPhonenum));
            }
            if(city != null){
                orgBaseInfo.setCity(city);
            }
            if (state != null){
                orgBaseInfo.setState(state);
            }
            if (createOperator != null){
                orgBaseInfo.setCreateOperator(createOperator);
            }
            if (groupUserid != null){
                orgBaseInfo.setGroupUserid(Long.parseLong(groupUserid));
            }
            if (nickName != null){
                orgBaseInfo.setNickName(nickName);
            }
            if (groupUserWeixinid != null){
                orgBaseInfo.setGroupUserWeixinid(groupUserWeixinid);
            }
            if (groupIfHighAuthority != null){
                if (groupIfHighAuthority.equals("是")){
                    orgBaseInfo.setGroupIfHighAuthority("y");
                }else {
                    orgBaseInfo.setGroupIfHighAuthority("n");
                }
            }
            if (managerUserid != null){
                orgBaseInfo.setManagerUserid(Long.parseLong(managerUserid));
            }
            if (logoImgUrlMax != null){
                orgBaseInfo.setLogoImgUrlMax(logoImgUrlMax);
            }
            if (weixinPublicName != null){
                orgBaseInfo.setWeixinPublicName(weixinPublicName);
            }
            if (personCountAuth != null){
                orgBaseInfo.setPersonCountAuth(Integer.parseInt(personCountAuth));
            }
            if (personCountRead != null){
                orgBaseInfo.setPersonCountRead(Integer.parseInt(personCountRead));
            }
            if (carTypeId != null){
                orgBaseInfo.setCarTypeId(carTypeId);
            }
            if (viceNumberMin != null){
                orgBaseInfo.setViceNumberMin(Integer.parseInt(viceNumberMin));
            }
            if (viceNumberMax != null){
                orgBaseInfo.setViceNumberMax(Integer.parseInt(viceNumberMax));
            }
            if (welcomeWord != null){
                orgBaseInfo.setWelcomeWord(welcomeWord);
            }
            if (orgRule != null){
                orgBaseInfo.setOrgRule(orgRule);
            }
            if (province != null){
                orgBaseInfo.setProvince(province);
            }
            if (weixinPublicId != null){
                orgBaseInfo.setWeixinPublicId(weixinPublicId);
            }
            if (weixinPublicLogo != null){
                orgBaseInfo.setWeixinPublicLogo(weixinPublicLogo);
            }
            if (weixinPublicHttpUrl != null){
                orgBaseInfo.setWeixinPublicHttpUrl(weixinPublicHttpUrl);
            }
            if (isDefault != null){
                if (isDefault.equals("是")){
                    orgBaseInfo.setIsDefault("y");
                }else {
                    orgBaseInfo.setIsDefault("n");
                }
            }
            int result = orgBaseInfoMapper.updateByPrimaryKeySelective(orgBaseInfo);
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

    public void selectInfo(){
        try {
            List<OrgBaseInfo> info = orgBaseInfoMapper.selectInfo();
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

