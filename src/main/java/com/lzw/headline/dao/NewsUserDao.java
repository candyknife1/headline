package com.lzw.headline.dao;

import com.lzw.headline.pojo.NewsUser;

public interface NewsUserDao {
    NewsUser findByUsername(String username);

    NewsUser findByUid(Integer userId);

    Integer insertUser(NewsUser newsUser);
}
