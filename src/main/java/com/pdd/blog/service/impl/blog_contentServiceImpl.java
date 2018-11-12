package com.pdd.blog.service.impl;

import com.pdd.blog.mapper.blog_contentMapper;
import com.pdd.blog.service.blog_contentService;
import com.pdd.blog.web.response.ResponseEntity;
import com.pdd.blog.web.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:liyangpeng
 * @date:2018/11/1 15:19
 */
@Service
public class blog_contentServiceImpl implements blog_contentService {

    @Autowired
    private blog_contentMapper blm;

    @Override
    public ResponseEntity new_content() {
        return ResponseResult.success(0,"Success",blm.new_content());
    }

}
