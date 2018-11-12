package com.pdd.blog.mapper;

import com.pdd.blog.bean.blog_user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author:liyangpeng
 * @date:2018/11/1 14:32
 */
@Mapper
public interface blog_userMapper {
    /**
     * 查询最近活跃用户
     * @return
     */
    List<blog_user> getActiveUser();

    /**
     * 查询用户粗略详情
     * @return
     */
    blog_user getActiveUserDetail(Integer id);

    /**
     * 用户登录
     * @param account
     * @param password
     * @return
     */
    blog_user login(@Param("account")String account,@Param("password")String password);

    /**
     * 用户注册
     * @return
     */
    long regist(blog_user user);

    /**
     * 查询邮箱是否已经存在
     * @return
     */
    Long getEmailExists(String email);

    /**
     * 查看账号是否已经存在
     * @param account
     * @return
     */
    Long getAccountExists(String account);

    /**
     * 根据Email查询用户信息
     * @param email
     * @return
     */
    blog_user getUserByEmail(String email);

    /**
     * 根据Id修改用户信息
     * @param user
     * @return
     */
    long updateUserById(blog_user user);

}
