package com.lzw.headline.service.impl;

import com.lzw.headline.dao.NewsTypeDao;
import com.lzw.headline.dao.impl.NewsTypeDaoImpl;
import com.lzw.headline.pojo.NewsType;
import com.lzw.headline.service.NewsTypeService;

import java.util.List;

/**
 * @Author: lzw
 * @Description: TODO
 * @Date: 2024/8/7 17:00
 * @Version: 1.0
 */
public class NewsTypeServiceImpl implements NewsTypeService {
  private NewsTypeDao typeDao=new NewsTypeDaoImpl();

    @Override
    public List<NewsType> findAll() {
        return typeDao.findAll();
    }
}
