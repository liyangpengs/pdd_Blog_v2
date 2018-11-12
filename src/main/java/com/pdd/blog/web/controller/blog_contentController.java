package com.pdd.blog.web.controller;

import com.pdd.blog.service.blog_contentService;
import com.pdd.blog.web.response.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:liyangpeng
 * @date:2018/11/1 15:26
 */
@RestController
@RequestMapping("/API/content")
public class blog_contentController {

    @Autowired
    private blog_contentService bcs;

    @GetMapping("/new_content")
    public ResponseEntity new_content(){
        return bcs.new_content();
    };
}
