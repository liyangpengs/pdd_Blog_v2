package com.pdd.blog.bean;

/**
 * @author:liyangpeng
 * @date:2018/11/1 15:04
 */
public class blog_user {
    /**
     * id
     */
    private Integer id;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 性别
     */
    private String sex;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 头像
     */
    private String headSrc;
    /**
     * 居住地址
     */
    private String address;
    /**
     * 大V等级
     */
    private Integer largeV;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 背景图片
     */
    private String backgroundImg;
    /**
     * 个性签名
     */
    private String userDesc;
    /**
     * 用户等级
     */
    private Integer level;
    /**
     * 邮箱
     */
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHeadSrc() {
        return headSrc;
    }

    public void setHeadSrc(String headSrc) {
        this.headSrc = headSrc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getLargeV() {
        return largeV;
    }

    public void setLargeV(Integer largeV) {
        this.largeV = largeV;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBackgroundImg() {
        return backgroundImg;
    }

    public void setBackgroundImg(String backgroundImg) {
        this.backgroundImg = backgroundImg;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "blog_user{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", headSrc='" + headSrc + '\'' +
                ", address='" + address + '\'' +
                ", largeV=" + largeV +
                ", mobile='" + mobile + '\'' +
                ", backgroundImg='" + backgroundImg + '\'' +
                ", userDesc='" + userDesc + '\'' +
                ", level=" + level +
                ", email='" + email + '\'' +
                '}';
    }
}
