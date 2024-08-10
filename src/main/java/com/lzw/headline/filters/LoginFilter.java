package com.lzw.headline.filters;

import com.lzw.headline.common.Result;
import com.lzw.headline.common.ResultCodeEnum;
import com.lzw.headline.util.JwtHelper;
import com.lzw.headline.util.WebUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Author: lzw
 * @Description: TODO
 * @Date: 2024/8/10 9:55
 * @Version: 1.0
 */
@WebFilter("/headline/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token= request.getHeader("token");
        boolean flag=false;
        if(null!=token && (!JwtHelper.isExpiration(token))){
            flag=true;
        }
        if (flag){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            WebUtil.writeJson((HttpServletResponse) servletResponse, Result.build(null, ResultCodeEnum.NOTLOGIN));
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
