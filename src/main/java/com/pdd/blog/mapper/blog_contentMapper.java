package com.pdd.blog.mapper;

import com.pdd.blog.bean.blog_content;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author:liyangpeng
 * @date:2018/11/1 14:32
 */
@Mapper
public interface blog_contentMapper {
    /**
     * 获取主页最新发表内容
     * @return
     */
    List<blog_content> new_content();
}
