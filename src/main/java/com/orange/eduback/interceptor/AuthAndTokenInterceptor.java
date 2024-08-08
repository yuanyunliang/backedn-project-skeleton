package com.orange.eduback.interceptor;

import com.orange.eduback.util.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;

@Slf4j
@Configuration
public class AuthAndTokenInterceptor implements HandlerInterceptor {

    @Resource
    private JwtUtils jwtUtils;
    // 拦截器
    // 1. 拦截请求，判断请求是否携带token
    // 2. 如果携带token，解析token，获取用户信息
    // 3. 如果没有携带token，返回未登录
    // 4. 如果token解析失败，返回token错误
    // 5. 如果token解析成功，将用户信息存入request，放行

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("---interceptor拦截请求：{}", request.getRequestURI());
        // 1. 拦截请求，判断请求是否携带token
        String token = request.getHeader("token");
        if (token == null || !jwtUtils.validateToken(token)) {
            response.setStatus(401);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("未登录或身份验证失败。");
            log.info("token：{} 验证失败。", token);
            return false;
        }
        // 2. 如果携带token，解析token，获取用户信息
        try {
            Claims claims = jwtUtils.getClaimsFromToken(token);
            //String username = jwtUtils.getUsernameFromToken(token);
            String username = claims.get("username", String.class);
            String role = claims.get("role", String.class);
            String id = claims.get("id", String.class);
            request.setAttribute("username", username);
            request.setAttribute("role", role);
            request.setAttribute("id", id);
            return true;
        } catch (Exception e) {
            log.error("token解析失败", e);
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("----interceptor请求postHandle处理：{}", request.getRequestURI());
        String username = (String) request.getAttribute("username");
        String role = (String) request.getAttribute("role");
        String id = (String) request.getAttribute("id");
        String ip = request.getRemoteAddr();
        String time = SimpleDateFormat.getInstance().format(System.currentTimeMillis());
        String browser = request.getHeader("User-Agent");
        String httpMethod = request.getMethod();
        log.info("用户：{}，ID：{}, 角色：{},IP：{}，时间：{}，浏览器：{}，请求方式：{}", username, id, role, ip, time, browser, httpMethod);

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("-----interceptor请求完成：{}", request.getRequestURI());
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
