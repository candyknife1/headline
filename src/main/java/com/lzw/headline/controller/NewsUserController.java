package com.lzw.headline.controller;

import com.lzw.headline.common.Result;
import com.lzw.headline.common.ResultCodeEnum;
import com.lzw.headline.pojo.NewsUser;
import com.lzw.headline.service.NewsUserService;
import com.lzw.headline.service.impl.NewsUserServiceImpl;
import com.lzw.headline.util.JwtHelper;
import com.lzw.headline.util.MD5Util;
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
@WebServlet("/user/*")
public class NewsUserController extends BaseController{
    private NewsUserService userService=new NewsUserServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收用户名密码
        NewsUser paramUser = WebUtil.readJson(req, NewsUser.class);
        NewsUser loginuser=userService.findByUsername(paramUser.getUsername());

        Result result=null;
        if(null != loginuser) {
            if (MD5Util.encrypt(paramUser.getUserPwd()).equals(loginuser.getUserPwd())) {
                Integer uid=loginuser.getUid();
                String token = JwtHelper.createToken(uid.longValue());
                Map data=new HashMap();
                data.put("token", token);
                result = Result.ok(data);
            } else {
                result = Result.build(null, ResultCodeEnum.PASSWORD_ERROR );
            }
        }else {
            result = Result.build(null, ResultCodeEnum.USERNAME_ERROR);
         }
        WebUtil.writeJson(resp, result);
    }

    protected void getUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("token");

        Result result=Result.build(null, ResultCodeEnum.NOTLOGIN);
       if(null!=token &&(!"".equals(token))){
           if(!JwtHelper.isExpiration(token)){
               Integer userId = JwtHelper.getUserId(token).intValue();
               NewsUser newsUser=userService.findByUid(userId);
               if(null!=newsUser){
                   Map data=new HashMap();
                   newsUser.setUserPwd("");
                   data.put("loginUser",newsUser);
                   result=Result.ok(data);
               }
           }
       }
        WebUtil.writeJson(resp, result);
    }

    protected void checkUserName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        NewsUser newsUser=userService.findByUsername(username);
        Result result=Result.ok(null);
        if(null!=newsUser){
            result=Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        WebUtil.writeJson(resp, result);
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsUser newsUser = WebUtil.readJson(req, NewsUser.class);
        System.out.println("nihao");
        System.out.println(newsUser.getNickName());
        Integer uid=userService.registUser(newsUser);
        Result result=Result.ok(null);
        if(null==uid){
            result=Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        WebUtil.writeJson(resp, result);
    }
}

