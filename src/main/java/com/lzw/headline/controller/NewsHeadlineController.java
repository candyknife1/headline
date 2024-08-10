package com.lzw.headline.controller;

import com.lzw.headline.common.Result;
import com.lzw.headline.pojo.NewsHeadline;
import com.lzw.headline.service.NewsHeadlineService;
import com.lzw.headline.service.impl.NewsHeadlineServiceImpl;
import com.lzw.headline.util.JwtHelper;
import com.lzw.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lzw
 * @Description: TODO
 * @Date: 2024/8/7 17:10
 * @Version: 1.0
 */
@WebServlet("/headline/*")
public class NewsHeadlineController extends BaseController{
    private NewsHeadlineService headlineService=new NewsHeadlineServiceImpl();

    protected void publish(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token=req.getHeader("token");
        Long userId= JwtHelper.getUserId(token);

        NewsHeadline newsHeadline= WebUtil.readJson(req,NewsHeadline.class);
        newsHeadline.setPublisher(userId.intValue());

        headlineService.addNewsHeadline(newsHeadline);
        WebUtil.writeJson(resp, Result.ok(null));
    }


    protected void findHeadlineByHid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer hid = Integer.parseInt(req.getParameter("hid"));
        NewsHeadline headline=headlineService.findByHId(hid);
        Map data=new HashMap();
        data.put("headline",headline);
        WebUtil.writeJson(resp, Result.ok(data));
    }


    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsHeadline headline = WebUtil.readJson(req, NewsHeadline.class);
        headlineService.update(headline);
        WebUtil.writeJson(resp, Result.ok(null));
    }


    protected void removeByHid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int hid =Integer.parseInt(req.getParameter("hid"));
        headlineService.removeByHid(hid);
        WebUtil.writeJson(resp,Result.ok(null));
    }
}
