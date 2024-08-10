package com.lzw.headline.service;

import com.lzw.headline.pojo.NewsType;

import java.util.List;

public interface NewsTypeService {
    /**
     * 查询所有头条类型方法
     * @return 多个头条类型以List集合返回
     */
    List<NewsType> findAll();
}
