package com.pdd.blog.web.controller;

import com.mysql.jdbc.StringUtils;
import com.pdd.blog.service.blog_userService;
import com.pdd.blog.web.dto.LoginFromDto;
import com.pdd.blog.web.dto.RegistFromDto;
import com.pdd.blog.web.dto.UpdateInfoFromDto;
import com.pdd.blog.web.response.ResponseEntity;
import com.pdd.blog.web.response.ResponseResult;
import com.pdd.blog.web.utils.MD5;
import com.pdd.blog.web.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author:liyangpeng
 * @date:2018/11/1 20:03
 */
@RestController
@RequestMapping("/API/User")
public class blog_userController {

    @Autowired
    private blog_userService bus;

    /**
     * 活跃用户
     * @return
     */
    @GetMapping("/ActiveUser")
    public ResponseEntity getActiveUser(){
        return bus.getActiveUser();
    }

    /**
     * 活跃用户详情
     * @param id
     * @return
     */
    @GetMapping("/getActiveUserDetail")
    public ResponseEntity getActiveUserDetail(Integer id){
        return bus.getActiveUserDetail(id);
    }

    /**
     * 登录
     * @param login
     * @param validResult
     * @param request
     * @return
     */
    @PostMapping("/Login")
    public ResponseEntity login(@Valid LoginFromDto login, BindingResult validResult, HttpServletRequest request){
        //验证表单合法性
        if(validResult.hasErrors()){
            return ResponseResult.error(1,"后台参数验证未通过",validResult.getAllErrors());
        }
        //验证验证码
        if(!VerifyUtil.validVerCode(request,login.getCode(),VerifyUtil.VERIFICATIONCODE)){
            return ResponseResult.error(1,"验证码错误");
        }
        //返回登录结果
        return bus.login(login,request.getSession());
    }

    /**
     * 获取登录信息接口
     * @param session
     * @return
     */
    @GetMapping("/getLoginInfo")
    public ResponseEntity getLoginInfo(HttpSession session){
        return bus.getLoginInfo(session);
    }

    /**
     * 注销登录
     * @param session
     * @return
     */
    @GetMapping("/LoginOut")
    public ResponseEntity loginOut(HttpSession session){
        //移除登录状态
        session.removeAttribute(MD5.BLOG_LOGIN_INFO);
        return ResponseResult.success(0,"您已注销..");
    }
    /**
     * 注册
     * @param regist
     * @param result
     * @param request
     * @return
     */
    @PostMapping("/Regist")
    public ResponseEntity regist(@Valid RegistFromDto regist, BindingResult result, HttpServletRequest request){
        if(result.hasErrors()){
            return ResponseResult.error(0,"后台参数验证未通过",result.getAllErrors());
        }
        //验证图片验证码
        if(!VerifyUtil.validVerCode(request,regist.getReimgcode(),VerifyUtil.VERIFICATIONCODE)){
            return ResponseResult.error(1,"图片验证码错误");
        }
        //验证邮箱验证码
        if(!VerifyUtil.validVerCode(request,regist.getCode(),VerifyUtil.EMAILVERIFICATIONCODE)){
            return ResponseResult.error(1,"邮箱验证码错误");
        }
        return bus.regist(regist);
    }

    /**
     * 密码重置
     * @param password
     * @param repassword
     * @param token
     * @return
     */
    @PostMapping("/ResetPwd")
    public ResponseEntity ResetPwd(String password,String repassword,String token){
        if(StringUtils.isNullOrEmpty(password)||StringUtils.isNullOrEmpty(repassword)||StringUtils.isNullOrEmpty(token)){
            return ResponseResult.error(1,"请求参数错误");
        }
        if(!password.equals(repassword)){
            return ResponseResult.error(1,"两次密码不一致");
        }
        return bus.ResetPwd(password,repassword,token);
    }


    @PostMapping("/UpdateUserInfo")
    public ResponseEntity UpdateUserInfo(@Valid UpdateInfoFromDto dto,BindingResult result,HttpSession session){
        if(result.hasErrors()){
            return ResponseResult.error(1,"后台参数验证未通过",result.getAllErrors());
        }
        return bus.UpdateUserInfo(dto,session);
    }
}
