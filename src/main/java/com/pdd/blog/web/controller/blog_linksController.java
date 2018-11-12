package com.pdd.blog.web.controller;

import com.pdd.blog.service.blog_linksService;
import com.pdd.blog.web.response.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:liyangpeng
 * @date:2018/11/2 9:57
 */
@RestController
@RequestMapping("/API/links")
public class blog_linksController {

    @Autowired
    private blog_linksService bls;

    @GetMapping("/getLinks")
    public ResponseEntity getLinks(){
        return bls.getLinks();
    }
}
