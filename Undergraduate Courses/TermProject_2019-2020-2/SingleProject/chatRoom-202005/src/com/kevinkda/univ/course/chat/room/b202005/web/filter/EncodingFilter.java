/*
 * Copyright (c) 2020 Kevin KDA. Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan. 
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna. 
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus. 
 * Vestibulum commodo. Ut rhoncus gravida arcu. 
 */

package com.kevinkda.univ.course.chat.room.b202005.web.filter;

import com.kevinkda.web.support.EncodingRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Kevin KDA on 2020/4/28 14:56
 * @version 1.0.0
 * @project TotalPro
 * @package com.kevinkda.univ.course.chat.room.b202005.web.filter
 * @classname EncodingFilter
 * @apiNote
 * @since 1.0.0
 */
@WebFilter(filterName = "SetEncodingFilter", urlPatterns = "/*")
public class EncodingFilter implements Filter {
    private static final String GET = "GET";
    private static final String POST = "POST";

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//        获得子接口的实例化对象
        HttpServletRequest request = (HttpServletRequest) req;

//        设置响应字符编码集为UTF-8
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
//        resp.setContentType("application/json; charset=utf-8");
        
//        进行请求方法判断
        if (GET.equals(request.getMethod())) {
//            GET请求进行 request 对象的替换
            EncodingRequest ecReq = new EncodingRequest(request);
//            传递过滤链，本次放行
            chain.doFilter(ecReq, resp);
        } else if (POST.equals(request.getMethod())) {
//            Post请求，执行编码类型转换
            req.setCharacterEncoding("UTF-8");
//            传递过滤链，本次放行
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
