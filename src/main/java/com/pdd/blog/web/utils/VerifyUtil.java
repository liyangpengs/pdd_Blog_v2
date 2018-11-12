package com.pdd.blog.web.utils;

import com.mysql.jdbc.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
/**
 * @author:liyangpeng
 * @date:2018/11/2 11:33
 */
public class VerifyUtil {
    /**
     * 验证码Key 放到session中的key
     */
    public static final String VERIFICATIONCODE= "VerificationCode";
    /**
     * 邮箱验证码KEY
     */
    public static final String EMAILVERIFICATIONCODE= "EmailVerificationCode";
    /**
     * 验证码模板
     */
    public final static String CODE_CONTENT = "<!DOCTYPE html><html><head><title></title><meta name=\"content-type\" content=\"text/html; charset=UTF-8\"></head><body><p>您好,您的验证码为:<span style=\"font-size: 16px;color:#222;min-height: 89px;word-wrap: break-word;\">%s</span></p></body></html>";

    /**
     * 验证码模板
     */
    public final static String  RESETPWD_CONTENT = "<!DOCTYPE html><html><head><title></title><meta name=\"content-type\" content=\"text/html; charset=UTF-8\"></head><body><p>您正在白发先生博客提交修改密码请求,请点击以下链接继续修改密码:</p><br/><a href=\"%s\">%s</a></body></html>";
    /**
     * 随机产生数字与字母组合的字符串
     */
    private static String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * 图片宽
     */
    private int width = 95;
    /**
     * 图片高
     */
    private int height = 25;
    /**
     * 干扰线数量
     */
    private int lineSize = 40;
    /**
     * 随机产生字符数量
     */
    private int stringNum = 4;

    private Random random = new Random();

    /**
     * 获得字体
     */
    private Font getFont() {
        return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
    }

    /**
     * 获得颜色
     */
    private Color getRandColor(int fc, int bc) {
        if (fc > 255){
            fc = 255;
        }
        if (bc > 255){
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /**
     * 生成随机图片
     */
    public void getRandcode(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        // 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        Graphics g = image.getGraphics();
        //图片大小
        g.fillRect(0, 0, width, height);
        //字体大小
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
        //字体颜色
        g.setColor(getRandColor(110, 133));
        // 绘制干扰线
        for (int i = 0; i <= lineSize; i++) {
            drowLine(g);
        }
        // 绘制随机字符
        String randomString = "";
        for (int i = 1; i <= stringNum; i++) {
            randomString = drowString(g, randomString, i);
        }
        //将生成的随机字符串保存到session中
        session.removeAttribute(VERIFICATIONCODE);
        session.setAttribute(VERIFICATIONCODE, randomString);
        //设置失效时间1分钟
        session.setMaxInactiveInterval(60);
        g.dispose();
        try {
            // 将内存中的图片通过流动形式输出到客户端
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 绘制字符串
     */
    private String drowString(Graphics g, String randomString, int i) {
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
                .nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(randString
                .length())));
        randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 13 * i, 16);
        return randomString;
    }

    /**
     * 绘制干扰线
     */
    private void drowLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    /**
     * 获取随机的字符
     */
    public static String getRandomString(int num) {
        return String.valueOf(randString.charAt(num));
    }

    /**
     * 验证验证码
     * @param request
     * @param code
     * @return
     */
    public static boolean validVerCode(HttpServletRequest request,String code,String key){
        HttpSession session = request.getSession();
        String sessionResult=String.valueOf(session.getAttribute(key));
        //非空判断 匹配验证
        if(!StringUtils.isNullOrEmpty(sessionResult)&&sessionResult.toLowerCase().equals(code.toLowerCase())){
            return true;
        }
        return false;
    }
    /**
     * 生成4位随机验证码
     * @return
     */
    public static String getRandomCode(){
        StringBuffer buffer=new StringBuffer();
        for (int i = 0; i < 4 ; i++) {
            Random random=new Random();
            String rand = String.valueOf(getRandomString(random.nextInt(randString.length())));
            buffer.append(rand);
        }
        return buffer.toString();
    }
}
