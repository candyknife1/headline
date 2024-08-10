package com.lzw.headline.service;

import com.lzw.headline.pojo.NewsHeadline;
import com.lzw.headline.pojo.vo.HeadlineDetailVo;
import com.lzw.headline.pojo.vo.HeadlineQueryVo;

import java.util.Map;

public interface NewsHeadlineService {
    Map<String,Object> findPage(HeadlineQueryVo headlineQueryVo);

    HeadlineDetailVo findHeadlineDetail(int hid);

    int addNewsHeadline(NewsHeadline newsHeadline);

    NewsHeadline findByHId(Integer hid);

    int update(NewsHeadline headline);

    int removeByHid(int hid);
}
