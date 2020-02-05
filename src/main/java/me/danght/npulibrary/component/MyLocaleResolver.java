package me.danght.npulibrary.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 区域解析器（用于国际化）
 * @author DangHT
 * @date 05/02/2020
 */
public class MyLocaleResolver implements LocaleResolver {

    /**
     * 浏览器发送请求参数”l“，指定区域
     * 标准的区域信息格式：language_country
     * 解析参数"l"，返回指定的区域对象
     *
     * @param request
     * @return
     */
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String l = request.getParameter("l");
        Locale locale = Locale.getDefault();

        if (!StringUtils.isEmpty(l)) {
            String[] s = l.split("_");
            locale = new Locale(s[0], s[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
