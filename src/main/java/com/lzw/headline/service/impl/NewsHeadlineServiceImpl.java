package com.lzw.headline.service.impl;

import com.lzw.headline.dao.NewsHeadlineDao;
import com.lzw.headline.dao.impl.NewsHeadlineDaoImpl;
import com.lzw.headline.pojo.NewsHeadline;
import com.lzw.headline.pojo.vo.HeadlineDetailVo;
import com.lzw.headline.pojo.vo.HeadlinePageVo;
import com.lzw.headline.pojo.vo.HeadlineQueryVo;
import com.lzw.headline.service.NewsHeadlineService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: lzw
 * @Description: TODO
 * @Date: 2024/8/7 17:01
 * @Version: 1.0
 */
public class NewsHeadlineServiceImpl implements NewsHeadlineService {
    private NewsHeadlineDao headlineDao =new NewsHeadlineDaoImpl();
    @Override
    public Map<String,Object> findPage(HeadlineQueryVo headlineQueryVo) {
        int pageNum=headlineQueryVo.getPageNum();
        int pageSize=headlineQueryVo.getPageSize();
        List<HeadlinePageVo> pagaData=headlineDao.findPageList(headlineQueryVo);
        int totalSize=headlineDao.findPagecount(headlineQueryVo);

        int totalPage = totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1;
        Map pageInfo=new HashMap();
        pageInfo.put("pageNum",pageNum);
        pageInfo.put("pageSize",pageSize);
        pageInfo.put("totalSize",totalSize);
        pageInfo.put("pageData",pagaData);
        pageInfo.put("totalPage",totalPage);

        return pageInfo;
    }

    @Override
    public HeadlineDetailVo findHeadlineDetail(int hid) {
        int i = headlineDao.incrPageViews(hid);
        return headlineDao.findHeadlineDetail(hid);
    }
}
