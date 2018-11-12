package com.pdd.blog.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.auth.COSSigner;
import com.qcloud.cos.http.HttpMethodName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author:liyangpeng
 * @date:2018/11/8 15:28
 */
@RestController
@RequestMapping("/API/COS")
public class blog_CosController {

    @Value("${blog.cos.bucket}")
    private String Bucket;

    @Value("${blog.cos.Region}")
    private String Region;

    @Value("${blog.cos.SecretId}")
    private String SecretId;

    @Value("${blog.cos.SecretKey}")
    private String SecretKey;

    @Value("${blog.cos.path}")
    private String path;

    @GetMapping("/getSignature")
    public String getSignature(){
        COSCredentials cred = new BasicCOSCredentials(SecretId,SecretKey);
        COSSigner signer = new COSSigner();
        Date expiredTime = new Date(System.currentTimeMillis() + 3600L * 1000L);
        String key = "1d7211eea640ad05e54010c2cdf752f9.jpeg";
        String sign = signer.buildAuthorizationStr(HttpMethodName.PUT, key, cred, expiredTime);
        System.out.println(sign);
        return sign;
    }
}
