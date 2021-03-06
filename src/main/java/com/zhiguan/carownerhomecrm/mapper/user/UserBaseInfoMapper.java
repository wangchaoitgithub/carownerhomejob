package com.zhiguan.carownerhomecrm.mapper.user;

import com.zhiguan.carownerhomecrm.domain.base.BaseApplyInfo;
import com.zhiguan.carownerhomecrm.domain.customer.CustomerRechargeRecord;
import com.zhiguan.carownerhomecrm.domain.user.UserBaseInfo;

import java.util.List;

public interface UserBaseInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_base_info
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_base_info
     *
     * @mbggenerated
     */
    int insert(UserBaseInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_base_info
     *
     * @mbggenerated
     */
    int insertSelective(UserBaseInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_base_info
     *
     * @mbggenerated
     */
    UserBaseInfo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_base_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(UserBaseInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_base_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(UserBaseInfo record);



    /*查询用户信息*/
    List<UserBaseInfo> pageListAll(UserBaseInfo enitty);

    /*查询的信息条数*/
    int pageListAllSize(UserBaseInfo enitty);

    /*修改前点击的用户信息*/
//    List<UserBaseInfo> selectInfo(Long Id);

    /*确认修改用户信息*/
//    int update(UserBaseInfo record);

    /*封禁用户*/
    int updateFordelete(UserBaseInfo enitty);
}