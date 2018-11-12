package com.pdd.blog.web.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @author:liyangpeng
 * @date:2018/11/8 14:14
 */
public class UpdateInfoFromDto {
    @NotNull(message = "昵称不能为空")
    @NotEmpty(message = "昵称不能为空")
    private String nickName;
    @NotNull(message = "邮箱不能为空")
    @NotEmpty(message = "邮箱不能为空")
    private String email;
    @NotNull(message = "密码不能为空")
    @NotEmpty(message = "密码不能为空")
    private String password;
    @NotNull(message = "重复密码不能为空")
    @NotEmpty(message = "重复密码不能为空")
    private String repassword;
    @NotNull(message = "性别不能为空")
    @NotEmpty(message = "性别不能为空")
    private String sex;
    @NotNull(message = "个人简介不能为空")
    @NotEmpty(message = "个人简介不能为空")
    private String userDesc;
    @NotNull(message = "头像不能为空")
    @NotEmpty(message = "头像不能为空")
    private String headSrc;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    @NotNull
    public String getHeadSrc() {
        return headSrc;
    }

    public void setHeadSrc(@NotNull String headSrc) {
        this.headSrc = headSrc;
    }

    @Override
    public String toString() {
        return "UpdateInfoFromDto{" +
                "nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", repassword='" + repassword + '\'' +
                ", sex='" + sex + '\'' +
                ", userDesc='" + userDesc + '\'' +
                ", headSrc='" + headSrc + '\'' +
                '}';
    }
}
