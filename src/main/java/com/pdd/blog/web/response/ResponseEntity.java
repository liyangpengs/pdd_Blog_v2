package com.pdd.blog.web.response;

/**
 * @author:liyangpeng
 * @desc:响应实体类
 * @date:2018/11/1 15:11
 */
public class ResponseEntity {
    private Integer code;
    private String msg;
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
