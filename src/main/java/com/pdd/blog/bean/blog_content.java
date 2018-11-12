package com.pdd.blog.bean;

import java.util.Date;

/**
 * @author:liyangpeng
 * @date:2018/11/1 14:30
 */
public class blog_content {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 发布用户
     */
    private blog_user author;
    /**
     * 标题
     */
    private String title;
    /**
     * 文件链接
     */
    private String url;
    /**
     * 封面
     */
    private String cover;
    /**
     * 发布时间
     */
    private Date publishtime;
    /**
     * 修改时间
     */
    private String modiftime;
    /**
     * 缓存访问Key
     */
    private String cachekey;
    /**
     * 领域Id
     */
    private String field;
    /**
     * 标签
     */
    private String tag;
    /**
     * 内容描述
     */
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public blog_user getAuthor() {
        return author;
    }

    public void setAuthor(blog_user author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public String getModiftime() {
        return modiftime;
    }

    public void setModiftime(String modiftime) {
        this.modiftime = modiftime;
    }

    public String getCachekey() {
        return cachekey;
    }

    public void setCachekey(String cachekey) {
        this.cachekey = cachekey;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
