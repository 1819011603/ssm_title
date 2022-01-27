package com.example.web.filter;

import com.example.domain.system.Module;
import org.apache.commons.fileupload.util.LimitedInputStream;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.logging.LogRecord;

/**
 * @author 18190
 * @Date: 2021/7/20  21:03
 * @VERSION 1.0
 */

@WebFilter(value = {"/system/*","/store/*","/front/*"})
public class PowerFilter implements Filter {
    private FilterConfig Config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.Config = filterConfig;
        System.out.println("PowerFilter init");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        try {
            request = (HttpServletRequest) req;


            response = (HttpServletResponse) res;
            HttpSession session = request.getSession();

            String requestURI = request.getRequestURI().substring(1);
            if(requestURI.endsWith(".css")
                    || requestURI.endsWith(".jpg")
                    || requestURI.endsWith(".js")
                    || requestURI.endsWith(".png")
                    || requestURI.endsWith("index.jsp")
                    || requestURI.endsWith("login.jsp")
                    || requestURI.endsWith("unauthorized.jsp")
            ){
                chain.doFilter(request,response);
                return;
            }
            String queryString = request.getQueryString().split("&")[0];
            if(queryString.endsWith("operation=login")
                  || queryString.endsWith("operation=logout")
                  || queryString.endsWith("operation=main")

            ){
                chain.doFilter(request,response);
                return;
            }
            String url = requestURI + "?" + queryString;
            System.out.println("url:" +url);
            String authorList = (String) session.getAttribute("authorList");
            System.out.println("授权网页为: " + authorList);
            if (authorList == null){
                response.sendRedirect(request.getContextPath()+"/unauthorized.jsp");
            }
            else {
                int beginIndex = url.indexOf("/")+1;
                boolean contains = authorList.contains(url.substring(beginIndex)) || authorList.contains(url);
                System.out.println("该页是否被授权：" + contains);
                if(contains){
                    chain.doFilter(request,response);

                }else {
                    response.sendRedirect(request.getContextPath()+"/unauthorized.jsp");
                }

            }




        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect(request.getContextPath()+"/unauthorized.jsp");
        }


    }

    @Override
    public void destroy() {
        System.out.println("PowerFilter destroy");
    }
}
