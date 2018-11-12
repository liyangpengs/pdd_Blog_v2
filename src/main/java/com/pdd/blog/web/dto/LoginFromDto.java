package com.pdd.blog.web.dto;

import com.pdd.blog.web.utils.MD5;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @author:liyangpeng
 * @date:2018/11/2 14:15
 */
public class LoginFromDto {
    //账号
    @NotNull(message = "账号不能为空")
    @NotEmpty(message = "账号不能为空")
    private String account;
    //密码
    @NotNull(message = "密码不能为空")
    @NotEmpty(message = "密码不能为空")
    private String password;
    //验证码
    @NotNull(message = "验证码不能为空")
    @NotEmpty(message = "验证码不能为空")
    private String code;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        //密码直接在get方法封装加密
        return MD5.encode(this.password+this.account+MD5.PWD_SALTVALUE);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
