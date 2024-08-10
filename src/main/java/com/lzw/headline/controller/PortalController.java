package com.lzw.headline.controller;

import com.lzw.headline.common.Result;
import com.lzw.headline.pojo.NewsHeadline;
import com.lzw.headline.pojo.NewsType;
import com.lzw.headline.pojo.vo.HeadlineDetailVo;
import com.lzw.headline.pojo.vo.HeadlineQueryVo;
import com.lzw.headline.service.NewsHeadlineService;
import com.lzw.headline.service.NewsTypeService;
import com.lzw.headline.service.impl.NewsHeadlineServiceImpl;
import com.lzw.headline.service.impl.NewsTypeServiceImpl;
import com.lzw.headline.service.impl.NewsUserServiceImpl;
import com.lzw.headline.util.WebUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: lzw
 * @Description:门户 控制器
 * 游客的请求由此处理
 * @Date: 2024/8/7 17:13
 * @Version: 1.0
 */
@WebServlet("/portal/*")
public class PortalController extends BaseController{
    private NewsTypeService typeService=new NewsTypeServiceImpl();
    private NewsHeadlineService headingService=new NewsHeadlineServiceImpl();

    /**
     * 查询所有头条类型的业务接口实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findAllTypes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<NewsType> newsTypeList=typeService.findAll();
        WebUtil.writeJson(resp,Result.ok(newsTypeList));
    }

    protected void findNewsPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HeadlineQueryVo headlineQueryVo = WebUtil.readJson(req, HeadlineQueryVo.class);
        Map pageInfo=headingService.findPage(headlineQueryVo);
        Map data=new HashMap();
        data.put("pageInfo",pageInfo);
        WebUtil.writeJson(resp,Result.ok(data));
    }

    protected void showHeadlineDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int hid=Integer.parseInt(req.getParameter("hid"));
        HeadlineDetailVo headlineDetailVo=headingService.findHeadlineDetail(hid);
        Map data=new HashMap();
        data.put("headline",headlineDetailVo);
        WebUtil.writeJson(resp,Result.ok(data));
    }
}


