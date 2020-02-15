package me.danght.npulibrary.config;

import me.danght.npulibrary.component.LoginHandlerInterceptor;
import me.danght.npulibrary.component.MyLocaleResolver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * MVC属性设置
 * @author DangHT
 * @date 05/02/2020
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean hiddenHttpMethodFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new HiddenHttpMethodFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        return filterRegistrationBean;
    }

    /**
     * 注册视图映射
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/")
                .setViewName("forward:/index");
        registry.addViewController("/index.html")
                .setViewName("forward:/index");
        registry.addViewController("/login")
                .setViewName("login");
        registry.addViewController("/login.html")
                .setViewName("login");
        registry.addViewController("/signup")
                .setViewName("signup");
        registry.addViewController("/signup.html")
                .setViewName("signup");
    }

    /**
     * 注册区域解析器（用于国际化）
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .excludePathPatterns("/webjars/**", "/assets/**", "/login", "/login.html", "/signup", "/signup.html", "/user/login");
    }
}
