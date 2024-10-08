package com.lzw.headline.dao;

import com.lzw.headline.pojo.NewsHeadline;
import com.lzw.headline.pojo.vo.HeadlineDetailVo;
import com.lzw.headline.pojo.vo.HeadlinePageVo;
import com.lzw.headline.pojo.vo.HeadlineQueryVo;

import java.util.List;

public interface NewsHeadlineDao {
    List<HeadlinePageVo> findPageList(HeadlineQueryVo headlineQueryVo);

    int findPagecount(HeadlineQueryVo headlineQueryVo);

    int incrPageViews(int hid);

    HeadlineDetailVo findHeadlineDetail(int hid);

    int addNewsHeadline(NewsHeadline newsHeadline);

    NewsHeadline findByHId(Integer hid);

    int update(NewsHeadline headline);

    int removeByHid(int hid);
}
