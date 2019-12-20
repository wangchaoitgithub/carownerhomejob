package com.zhiguan.carownerhomecrm.service.user;

import com.zhiguan.carownerhomecrm.common.util.PageUtils;
import com.zhiguan.carownerhomecrm.domain.user.UserBaseInfo;

public interface UserBaseInfoService {
    /*查询的所有信息*/
    PageUtils pageListAll(UserBaseInfo entity);


}
