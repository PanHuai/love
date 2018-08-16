package com.love.lylph.filiter;

import com.love.lylph.common.JsonUtils;
import com.love.lylph.common.StringUtils;
import com.love.lylph.response.ResponseInfo;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 潘淮  on 2018/8/15.<br>
 */
public class loginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ResponseInfo responseInfo =  new ResponseInfo();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        PrintWriter writer = response.getWriter();
        String token = request.getParameter("TOKEN");
        String userId = request.getParameter("userId");
        if (StringUtils.isBlank(token) || StringUtils.isBlank(userId)) {
            responseInfo.setCode(404);
            responseInfo.setMsg("访问无效，请登录");
            writer.print(JsonUtils.getJsonUtils().toJson(responseInfo));
            writer.close();
            return;
        }
        String attribute = (String) request.getSession().getAttribute("TOKEN" + userId);
        if (!attribute.equals(token)) {
            responseInfo.setCode(400);
            responseInfo.setMsg("访问过期，请重新登录");
            writer.print(JsonUtils.getJsonUtils().toJson(responseInfo));
            writer.close();
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
    }
}
