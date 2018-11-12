package com.pdd.blog.web.controller;

import com.mysql.jdbc.StringUtils;
import com.pdd.blog.mapper.blog_userMapper;
import com.pdd.blog.web.response.ResponseEntity;
import com.pdd.blog.web.response.ResponseResult;
import com.pdd.blog.web.utils.MD5;
import com.pdd.blog.web.utils.RedisUtil;
import com.pdd.blog.web.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:liyangpeng
 * @date:2018/11/2 11:22
 */
@Controller
@RequestMapping("/API/VerificationCode")
public class blog_VerificationCode {

    @Value("${spring.mail.username}")
    private String fromMain;

    @Value("${spring.domain}")
    private String domain;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private blog_userMapper bum;

    @Autowired
    private RedisUtil redisUtil;
    /**
     * 获取验证码接口
     * @param request
     * @param response
     */
    @GetMapping("/getCode")
    public void getVerificationCode(HttpServletRequest request, HttpServletResponse response){
        List<String> list=new ArrayList<>();
        //设置相应类型,告诉浏览器输出的内容为图片
        response.setContentType("image/jpeg");
        //设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        VerifyUtil randomValidateCode = new VerifyUtil();
        //输出验证码图片
        randomValidateCode.getRandcode(request, response);
    }

    @PostMapping("/SendMainCode")
    @ResponseBody
    public ResponseEntity sendMainVerificationCode(String mainAddress, HttpSession session) throws Exception{
        if(StringUtils.isNullOrEmpty(mainAddress)){
            return ResponseResult.error(1,"邮箱不能为空.");
        }
        MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
        //设置发信人，发信人需要和spring.mail.username配置的一样否则报错
        message.setFrom(new InternetAddress(MimeUtility.encodeText("白发先生")+"<"+fromMain+">"));
        //设置收信人
        message.setTo(mainAddress);
        //设置主题
        message.setSubject("白发先生博客-注册验证码");
        String EmailCode=VerifyUtil.getRandomCode();
        //保存验证码到session
        session.setAttribute(VerifyUtil.EMAILVERIFICATIONCODE,EmailCode);
        //第二个参数true表示使用HTML语言来编写邮件
        message.setText(String.format(VerifyUtil.CODE_CONTENT,EmailCode),true);
        this.mailSender.send(mimeMessage);
        return ResponseResult.success(0,"验证码发送成功.");
    }


    @PostMapping("/ResetPwdMail")
    @ResponseBody
    public ResponseEntity ResetPwdMail(String email, HttpSession session) throws Exception{
        if(StringUtils.isNullOrEmpty(email)){
            return ResponseResult.error(1,"邮箱不能为空.");
        }
        //查询邮箱是否已经被注册
        long emailExists=bum.getEmailExists(email);
        if(emailExists==0){
            return ResponseResult.error(0,"此邮箱尚未注册.");
        }
        MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
        //设置发信人，发信人需要和spring.mail.username配置的一样否则报错
        message.setFrom(new InternetAddress(MimeUtility.encodeText("白发先生")+"<"+fromMain+">"));
        //设置收信人
        message.setTo(email);
        //设置主题
        message.setSubject("白发先生博客-修改密码");
        String token= MD5.encode(System.currentTimeMillis()+email+"ABCD1234..");
        //存入redis有效时间30分钟
        redisUtil.set(token,email,30*60*60000);
        System.out.println(token);
        String href=domain+"ResetPwd.html?token="+token;
        //第二个参数true表示使用HTML语言来编写邮件
        message.setText(String.format(VerifyUtil.RESETPWD_CONTENT,href,href),true);
        this.mailSender.send(mimeMessage);
        return ResponseResult.success(0,"重置密码方式已通过邮件发送至您的邮箱,请注意查收.");
    }
}
