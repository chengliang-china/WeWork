package com.wework.base.config;

import com.alibaba.fastjson.JSONObject;
import com.wework.base.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Configuration
public class MyInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;
    /**
     *  在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 进行逻辑判断，如果ok就返回true，不行就返回false，返回false就不会处理改请求
        PrintWriter out = null;
        JSONObject res = new JSONObject();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        if(request.getRequestURI().indexOf("login") != -1 ){
            System.out.println("登陆 放行。。。");
            return true;
        }

        String token = request.getParameter("token");

        if(token == null){
            res.put("code","1");
            res.put("message","token 为空");
            res.put("result",null);
            out = response.getWriter();
            out.append(res.toString());
            return false;
        }
        return true;
    }
        /*Object object = redisService.get(token);

        try{
            if(object != null)
                return true;

            res.put("code","110");
            res.put("message","token 失效");
            res.put("result",null);
            out = response.getWriter();
            out.append(res.toString());
            return false;
        }
        catch (Exception e){
            e.printStackTrace();
            response.sendError(500);
            return false;
        }*/



    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        //
    }
    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}
