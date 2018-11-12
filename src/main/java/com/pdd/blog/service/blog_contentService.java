package com.pdd.blog.service;

import com.pdd.blog.web.response.ResponseEntity;

/**
 * @author:liyangpeng
 * @date:2018/11/1 15:19
 */
public interface blog_contentService  {
    /**
     * 获取主页最新发表内容
     * @return
     */
    ResponseEntity new_content();
}
