package com.lzw.headline.service;

import com.lzw.headline.pojo.NewsUser;

public interface NewsUserService {
    NewsUser findByUsername(String username);

    NewsUser findByUid(Integer userId);

    Integer registUser(NewsUser newsUser);
}
