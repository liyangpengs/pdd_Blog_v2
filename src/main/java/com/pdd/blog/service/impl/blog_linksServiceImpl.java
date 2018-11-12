package com.pdd.blog.service.impl;

import com.pdd.blog.mapper.blog_linksMapper;
import com.pdd.blog.service.blog_linksService;
import com.pdd.blog.web.response.ResponseEntity;
import com.pdd.blog.web.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:liyangpeng
 * @date:2018/11/2 9:53
 */
@Service
public class blog_linksServiceImpl implements blog_linksService {

    @Autowired
    private blog_linksMapper blm;

    @Override
    public ResponseEntity getLinks() {
        return ResponseResult.success(0,"SUCCESS",blm.getLinks());
    }

}
