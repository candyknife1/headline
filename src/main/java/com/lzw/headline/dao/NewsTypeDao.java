package com.lzw.headline.dao;

import com.lzw.headline.pojo.NewsType;

import java.util.List;

public interface NewsTypeDao {
    List<NewsType> findAll();
}
