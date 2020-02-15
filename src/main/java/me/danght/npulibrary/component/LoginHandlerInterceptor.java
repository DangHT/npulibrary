package me.danght.npulibrary.component;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 * @author DangHT
 * @date 14/02/2020
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        Object firstOpen = request.getSession().getAttribute("firstOpen");
        if (user == null) {
            if (firstOpen != null) request.setAttribute("msg", "请先登录");
            request.getRequestDispatcher("/login").forward(request, response);
            return false;
        }
        return true;
    }
}
