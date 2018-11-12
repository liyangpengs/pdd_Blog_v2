package com.pdd.blog.web.response;

/**
 * @author:liyangpeng
 * @desc:响应工具类
 * @date:2018/11/1 15:13
 */
public class ResponseResult {

    public static ResponseEntity success(Integer code){
        return success(code,null);
    }

    public static ResponseEntity success(Integer code,String msg){
        return success(code,msg,null);
    }

    public static ResponseEntity success(Integer code,String msg,Object data){
        ResponseEntity entity=new ResponseEntity();
        entity.setCode(code);
        entity.setMsg(msg);
        entity.setData(data);
        return entity;
    }

    public static ResponseEntity error(Integer code){
        return error(code,null);
    }

    public static ResponseEntity error(Integer code,String msg){
        return error(code,msg,null);
    }

    public static ResponseEntity error(Integer code,String msg,Object data){
        ResponseEntity entity=new ResponseEntity();
        entity.setCode(code);
        entity.setMsg(msg);
        entity.setData(data);
        return entity;
    }
}
