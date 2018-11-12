package com.pdd.blog.web.dto;

import com.pdd.blog.web.utils.MD5;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @author:liyangpeng
 * @date:2018/11/6 10:33
 */
public class RegistFromDto {
    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空")
    @NotEmpty(message = "用户名不能为空")
    private String  account;
    /**
     * 邮箱
     */
    @NotNull(message = "密码不能为空")
    @NotEmpty(message = "密码不能为空")
    private String password;

    @NotNull(message = "邮箱不能为空")
    @NotEmpty(message = "邮箱不能为空")
    private String email;
    /**
     * 密码
     */
    @NotNull(message = "确认密码不能为空")
    @NotEmpty(message = "确认密码不能为空")
    private String repassword;

    /**
     * 验证码
     */
    @NotNull(message = "验证码不能为空")
    @NotEmpty(message = "验证码不能为空")
    private String reimgcode;
    /**
     * 邮箱验证码
     */
    @NotNull(message = "邮箱验证码不能为空")
    @NotEmpty(message = "邮箱验证码不能为空")
    private String code;

    public String getReimgcode() {
        return reimgcode;
    }

    public void setReimgcode(String reimgcode) {
        this.reimgcode = reimgcode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @NotNull
    public String getAccount() {
        return account;
    }

    public void setAccount(@NotNull String account) {
        this.account = account;
    }

    @NotNull
    public String getPassword() {
        return MD5.encode(this.password+this.account+MD5.PWD_SALTVALUE);
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    @NotNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    @NotNull
    public String getRepassword() {
        return MD5.encode(this.repassword+this.account+MD5.PWD_SALTVALUE);
    }

    public void setRepassword(@NotNull String repassword) {
        this.repassword = repassword;
    }

    @Override
    public String toString() {
        return "RegistFromDto{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", repassword='" + repassword + '\'' +
                ", reimgcode='" + reimgcode + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
