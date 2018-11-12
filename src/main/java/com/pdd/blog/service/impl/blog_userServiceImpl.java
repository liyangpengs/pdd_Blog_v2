package com.pdd.blog.service.impl;

import com.mysql.jdbc.StringUtils;
import com.pdd.blog.bean.blog_user;
import com.pdd.blog.mapper.blog_userMapper;
import com.pdd.blog.service.blog_userService;
import com.pdd.blog.web.dto.LoginFromDto;
import com.pdd.blog.web.dto.RegistFromDto;
import com.pdd.blog.web.dto.UpdateInfoFromDto;
import com.pdd.blog.web.response.ResponseEntity;
import com.pdd.blog.web.response.ResponseResult;
import com.pdd.blog.web.utils.MD5;
import com.pdd.blog.web.utils.RedisUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @author:liyangpeng
 * @date:2018/11/1 15:10
 */
@Service
public class blog_userServiceImpl implements blog_userService {

    @Autowired
    private blog_userMapper bum;

    @Autowired
    private RedisUtil redis;

    @Override
    public ResponseEntity getActiveUser() {
        return ResponseResult.success(0,"SUCCESS",bum.getActiveUser());
    }

    @Override
    public ResponseEntity getActiveUserDetail(Integer id) {
        return ResponseResult.success(0,"SUCCESS",bum.getActiveUserDetail(id));
    }


    @Override
    public ResponseEntity login(LoginFromDto dto,HttpSession session) {
        blog_user loginUser=bum.login(dto.getAccount(),dto.getPassword());
        //判断是否登录成功
        if(loginUser==null){
            return ResponseResult.error(1,"账号或密码错误");
        }
        //保留登录信息
        session.setAttribute(MD5.BLOG_LOGIN_INFO,loginUser);
        return ResponseResult.success(0,"登录成功",loginUser);
    }


    @Override
    public ResponseEntity getLoginInfo(HttpSession session) {
        //判断是否登录
        if(session.getAttribute(MD5.BLOG_LOGIN_INFO)==null){
            return ResponseResult.error(1,"未登录.");
        }
        //返回登录信息
        return ResponseResult.success(0,"已登录",session.getAttribute(MD5.BLOG_LOGIN_INFO));
    }


    @Override
    public ResponseEntity regist(RegistFromDto dto) {
        //匹配重复密码
        if(!dto.getPassword().equals(dto.getRepassword())){
            return ResponseResult.error(0,"两次密码不一致");
        }
        //查询邮箱是否已经被注册
        long emailExists=bum.getEmailExists(dto.getEmail());
        if(emailExists>0){
            return ResponseResult.error(0,"此邮箱已被注册.");
        }
        //查询用户名是否已经存在
        long AccountExists=bum.getAccountExists(dto.getAccount());
        if(AccountExists>0){
            return ResponseResult.error(0,"此用户名已被注册.");
        }
        blog_user user=new blog_user();
        BeanUtils.copyProperties(dto,user);
        long result=bum.regist(user);
        if(result>0){
            return ResponseResult.success(0,"注册成功");
        }
        return ResponseResult.error(1,"注册失败");
    }


    @Override
    public ResponseEntity ResetPwd(String password, String repassword, String token) {
        String email=redis.get(token);
        //token使用完毕立马清除
        redis.delKey(token);
        //判断token是否真实存在
        if(StringUtils.isNullOrEmpty(email)||email.equals("null")){
            return ResponseResult.error(1,"token已失效.");
        }
        blog_user user=bum.getUserByEmail(email);
        if(user==null){
            return ResponseResult.error(1,"邮箱错误.");
        }
        //修改密码
        user.setPassword(MD5.encode(password+user.getAccount()+MD5.PWD_SALTVALUE));
        long Result=bum.updateUserById(user);
        if(Result>0){
            return ResponseResult.success(0,"重置密码成功");
        }
        return ResponseResult.success(0,"密码重置失败");
    }

    @Override
    public ResponseEntity UpdateUserInfo(UpdateInfoFromDto dto,HttpSession session) {
        //匹配两次密码是否一致
        if(!dto.getPassword().equals(dto.getRepassword())){
            return ResponseResult.error(1,"两次密码不一致");
        }
        //查询邮箱是否已经被注册
        long emailExists=bum.getEmailExists(dto.getEmail());
        if(emailExists>0){
            return ResponseResult.error(1,"此邮箱已被注册.");
        }
        //判断是否登录
        blog_user user=(blog_user)session.getAttribute(MD5.BLOG_LOGIN_INFO);
        if(session.getAttribute(MD5.BLOG_LOGIN_INFO)==null){
            return ResponseResult.error(1,"未登录.");
        }
        BeanUtils.copyProperties(dto,user);
        System.out.println(dto);
        System.out.println(user);
        return ResponseResult.success(0,"修改成功.");
    }
}