package com.pdd.blog.mapper;

import com.pdd.blog.bean.blog_links;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author:liyangpeng
 * @date:2018/11/2 9:48
 */
@Mapper
public interface blog_linksMapper {
    /**
     * 查询友情链接
     * @return
     */
    List<blog_links> getLinks();

}
