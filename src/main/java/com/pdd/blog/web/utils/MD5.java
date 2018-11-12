package com.pdd.blog.web.utils;

import java.security.MessageDigest;

/**
 * @author:liyangpeng
 * @date:2018/11/5 10:16
 */
public class MD5 {
    /**
     * 用户密码盐值
     */
    public final static String PWD_SALTVALUE="liyangpengzxh#ljl";
    /**
     * 用户登录状态KEY
     */
    public final static String BLOG_LOGIN_INFO="blog_login_info";
    /**
     * md5加密
     * @param text
     * @return
     * @throws Exception
     */
    public static String encode(String text){
        try{
            //创建加密类型工具
            MessageDigest digest=MessageDigest.getInstance("md5");
            //将要加密字符串转换为byte数组
            byte [] result=digest.digest(text.getBytes());
            StringBuffer buffer=new StringBuffer();
            for (byte b : result){
                int number=b & 0xff;
                //将对应byte类型数据转换为16进制编码
                String str=Integer.toHexString(number);
                //如果16进制数据小于1位 则往前面补位为0(目的是保留32位md5编码)
                if(str.length()==1){
                    buffer.append("0");
                }
                //拼接16进制编码一共16位 生成32位md5编码
                buffer.append(str);
            }
            return buffer.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        //默认返回空字符串
        return "";
    }
}
