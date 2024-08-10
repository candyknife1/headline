package com.lzw.headline.dao.impl;

import com.lzw.headline.dao.BaseDao;
import com.lzw.headline.dao.NewsUserDao;
import com.lzw.headline.pojo.NewsUser;

import java.util.List;

/**
 * @Author: lzw
 * @Description: TODO
 * @Date: 2024/8/7 16:50
 * @Version: 1.0
 */
public class NewsUserDaoImpl extends BaseDao implements NewsUserDao {
    @Override
    public NewsUser findByUsername(String username) {
       String sql= """
               select
                 uid,username,user_pwd as userPwd,nick_name as nickName 
               from 
                 news_user
               where
                username=?
               """;
        List<NewsUser> newsUserList = baseQuery(NewsUser.class, sql, username);
        return newsUserList!=null && newsUserList.size()>0 ? newsUserList.get(0) : null;
    }

    @Override
    public NewsUser findByUid(Integer userId) {
        String sql= """
               select
                 uid,username,user_pwd as userPwd,nick_name as nickName 
               from 
                 news_user
               where
                uid=?
               """;
        List<NewsUser> newsUserList = baseQuery(NewsUser.class, sql, userId);
        return newsUserList!=null && newsUserList.size()>0 ? newsUserList.get(0) : null;
    }

    @Override
    public Integer insertUser(NewsUser newsUser) {
       String sql= """
               insert into news_user
               values (DEFAULT,?,?,?)
               """;
               return baseUpdate(sql, newsUser.getUsername(), newsUser.getUserPwd(), newsUser.getNickName());
    }

}
