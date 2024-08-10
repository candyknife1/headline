package com.lzw.headline.dao.impl;

import com.lzw.headline.dao.BaseDao;
import com.lzw.headline.dao.NewsHeadlineDao;
import com.lzw.headline.dao.NewsUserDao;
import com.lzw.headline.pojo.NewsHeadline;
import com.lzw.headline.pojo.vo.HeadlineDetailVo;
import com.lzw.headline.pojo.vo.HeadlinePageVo;
import com.lzw.headline.pojo.vo.HeadlineQueryVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lzw
 * @Description: TODO
 * @Date: 2024/8/7 16:50
 * @Version: 1.0
 */
public class NewsHeadlineDaoImpl extends BaseDao implements NewsHeadlineDao {
    @Override
    public List<HeadlinePageVo> findPageList(HeadlineQueryVo headlineQueryVo) {
        List params = new ArrayList();
       String sql= """
               select
                   hid,
                   title,
                   type,
                   page_views as pageViews,                  
                   TIMESTAMPDIFF(HOUR,create_time,now()) as pastHours,
                   publisher
               from
                    news_headline
               where
                    is_deleted=0
               """;
       if(headlineQueryVo.getType()!=0){
           sql=sql.concat(" and type = ? ");
           params.add(headlineQueryVo.getType());
       }
        if(headlineQueryVo.getKeyWords() != null && !headlineQueryVo.getKeyWords().equals("")){
            sql=sql.concat(" and title like ? ");
            params.add("%"+headlineQueryVo.getKeyWords()+"%");
        }

        sql=sql.concat(" order by pastHours ASC , page_views DESC ");
        sql=sql.concat(" limit ? , ? ");
        params.add((headlineQueryVo.getPageNum()-1)*headlineQueryVo.getPageSize());
        params.add(headlineQueryVo.getPageSize());
        return baseQuery(HeadlinePageVo.class, sql, params.toArray());
    }

    @Override
    public int findPagecount(HeadlineQueryVo headlineQueryVo) {
        List params = new ArrayList();
        String sql= """
               select
                   count(1)
               from
                    news_headline
               where
                    is_deleted=0
               """;
        if(headlineQueryVo.getType()!=0){
            sql=sql.concat(" and type = ? ");
            params.add(headlineQueryVo.getType());
        }
        if(headlineQueryVo.getKeyWords() != null && !headlineQueryVo.getKeyWords().equals("")){
            sql=sql.concat(" and title like ? ");
            params.add("%"+headlineQueryVo.getKeyWords()+"%");
        }
        Long count = baseQueryObject(long.class, sql, params.toArray());
        return count.intValue();
    }

    @Override
    public int incrPageViews(int hid) {
        String sql="update news_headline set page_views=page_views+1 where hid=? ";
        return baseUpdate(sql,hid);
    }

    @Override
    public HeadlineDetailVo findHeadlineDetail(int hid) {
        String sql= """
                select
                    h.hid,
                    h.title,
                    h.article,
                    h.type,
                    t.tname as typeName,
                    h.page_views as pageViews,                  
                    TIMESTAMPDIFF(HOUR,h.create_time,now()) as pastHours,
                    h.publisher,
                    u.nick_name as author
                from
                    news_headline h   
                left join
                    news_type t on h.type = t.tid
                left join
                    news_user u on h.publisher = u.uid
                where     
                    h.hid = ?                              
                """;
        List<HeadlineDetailVo> list = baseQuery(HeadlineDetailVo.class, sql, hid);
        return null != list && list.size()>0? list.get(0) : null;
    }

    @Override
    public int addNewsHeadline(NewsHeadline newsHeadline) {
        String sql = """
                INSERT INTO news_headline
                 values (DEFAULT,?,?,?,?,0,now(),now(),0)
                """;
        return baseUpdate(sql, newsHeadline.getTitle(), newsHeadline.getArticle(), newsHeadline.getType(), newsHeadline.getPublisher());
    }

    @Override
    public NewsHeadline findByHId(Integer hid) {
        String sql = """
                select
                    hid,
                    title,
                    article,
                    type,
                    publisher,
                    page_views pageViews,
                    create_time createTime,
                    update_time updateTime,
                    is_deleted isDeleted
                from
                    news_headline
                 where
                    hid =?
                """;
        List<NewsHeadline> list=baseQuery(NewsHeadline.class,sql,hid);
        return list!=null && list.size()>0? list.get(0) : null;
    }

    @Override
    public int update(NewsHeadline headline) {
       String sql = """
               update news_headline
                set
                    title =?,
                    article =?,
                    type =?,
                    update_time=now()
                where
                    hid =?
               """;
       return baseUpdate(sql, headline.getTitle(), headline.getArticle(), headline.getType(), headline.getHid());
    }

    @Override
    public int removeByHid(int hid) {
        String sql = """
                update news_headline
                 set
                    is_deleted=1,
                    update_time=now()
                where
                    hid =?
                """;
        return baseUpdate(sql, hid);
    }
}
