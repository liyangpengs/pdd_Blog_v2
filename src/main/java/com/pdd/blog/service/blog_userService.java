package com.pdd.blog.service;

import com.pdd.blog.bean.blog_user;
import com.pdd.blog.web.dto.LoginFromDto;
import com.pdd.blog.web.dto.RegistFromDto;
import com.pdd.blog.web.dto.UpdateInfoFromDto;
import com.pdd.blog.web.response.ResponseEntity;

import javax.servlet.http.HttpSession;

/**
 * @author:liyangpeng
 * @date:2018/11/1 15:10
 */
public interface blog_userService {
    /**
     * 查询最近活跃用户
     * @return
     */
    ResponseEntity getActiveUser();

    /**
     * 查询用户粗略详情
     * @param id
     * @return
     */
    ResponseEntity getActiveUserDetail(Integer id);

    /**
     * 登录
     * @return
     */
    ResponseEntity login(LoginFromDto dto,HttpSession session);

    /**
     * 获取登录信息
     * @param session
     * @return
     */
    ResponseEntity getLoginInfo(HttpSession session);

    /**
     * 用户注册
     * @return
     */
    ResponseEntity regist(RegistFromDto dto);

    /**
     * 重置密码
     * @param password
     * @param repassword
     * @param token
     * @return
     */
    ResponseEntity ResetPwd(String password,String repassword,String token);

    /**
     * 用户修改资料接口
     * @param dto
     * @return
     */
    ResponseEntity UpdateUserInfo(UpdateInfoFromDto dto,HttpSession session);
}
