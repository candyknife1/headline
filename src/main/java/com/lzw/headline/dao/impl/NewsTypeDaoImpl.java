package com.lzw.headline.dao.impl;

import com.lzw.headline.dao.BaseDao;
import com.lzw.headline.dao.NewsTypeDao;
import com.lzw.headline.dao.NewsUserDao;
import com.lzw.headline.pojo.NewsType;

import java.util.List;

/**
 * @Author: lzw
 * @Description: TODO
 * @Date: 2024/8/7 16:50
 * @Version: 1.0
 */
public class NewsTypeDaoImpl extends BaseDao implements NewsTypeDao {

    @Override
    public List<NewsType> findAll() {
        String sql="select tid,tname from news_type";
        return baseQuery(NewsType.class,sql);
    }
}
