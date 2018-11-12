package com.pdd.blog.bean;

import java.util.Date;

/**
 * @author:liyangpeng
 * @date:2018/11/2 9:46
 */
public class blog_links {
    private Integer id;
    /**
     * 网站名称
     */
    private String siteName;
    /**
     * 网站主页
     */
    private String link;
    /**
     * 网站描述
     */
    private String desc;
    /**
     * 是否删除
     */
    private Integer isdel;
    /**
     * 添加时间
     */
    private Date addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}
