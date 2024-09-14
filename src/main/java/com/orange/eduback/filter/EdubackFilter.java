package com.orange.eduback.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


@Slf4j
@Component
public class EdubackFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("----init EdubackFilter------");
        Filter.super.init(filterConfig);
    }

    //监控访问的Ip地址,同时记录访问的时间
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("----doFilter EdubackFilter------");
        String ip = servletRequest.getRemoteAddr();
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "127.0.0.1";
        }
        long start = System.currentTimeMillis();
        //转换为yyyy-MM-dd HH:mm:ss
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(start));
        log.info("ip:{},访问时间:{}", ip, time);

        /*
        //拦截非本机Ip,拒绝访问,暂时不开启
        if (!ip.equals("127.0.0.1")) {
            log.error("非本机ip,拒绝访问");
            PrintWriter writer = servletResponse.getWriter();
            servletResponse.setCharacterEncoding("UTF-8");
            servletResponse.setContentType("application/json;charset=UTF-8");
            writer.write("非本机ip,拒绝访问");
            return;
        }*/

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("----destroy EdubackFilter------");
        Filter.super.destroy();
    }

}
