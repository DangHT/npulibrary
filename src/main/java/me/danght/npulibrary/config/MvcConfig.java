package me.danght.npulibrary.config;

import me.danght.npulibrary.component.MyLocaleResolver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.LocaleResolver;
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
                .setViewName("login");
        registry.addViewController("/index")
                .setViewName("login");
        registry.addViewController("/index.html")
                .setViewName("login");
        registry.addViewController("/login.html")
                .setViewName("login");
        registry.addViewController("/signup.html")
                .setViewName("signup");
        registry.addViewController("/admin")
                .setViewName("dashboard");
    }

    /**
     * 注册区域解析器（用于国际化）
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }


}
