package me.danght.npulibrary.component;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 管理员权限拦截器
 * @author DangHT
 * @date 15/02/2020
 */
public class AdminHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Boolean isAdmin = (Boolean) request.getSession().getAttribute("isAdmin");
        if (!isAdmin) {
            response.sendRedirect("/index");
            return false;
        }
        return true;
    }
}
