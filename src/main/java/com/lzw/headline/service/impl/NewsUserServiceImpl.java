package com.lzw.headline.service.impl;

import com.lzw.headline.dao.NewsUserDao;
import com.lzw.headline.dao.impl.NewsUserDaoImpl;
import com.lzw.headline.pojo.NewsUser;
import com.lzw.headline.service.NewsUserService;
import com.lzw.headline.util.MD5Util;

/**
 * @Author: lzw
 * @Description: TODO
 * @Date: 2024/8/7 16:59
 * @Version: 1.0
 */
public class NewsUserServiceImpl implements NewsUserService {
    private NewsUserDao userDao=new NewsUserDaoImpl();
    @Override
    public NewsUser findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public NewsUser findByUid(Integer userId) {
        return userDao.findByUid(userId);
    }

    @Override
    public Integer registUser(NewsUser newsUser) {
        newsUser.setUserPwd(MD5Util.encrypt(newsUser.getUserPwd()));
        return userDao.insertUser(newsUser);
    }
}
