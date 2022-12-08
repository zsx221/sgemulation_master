package com.macrochina.net.config;

import com.google.common.net.HttpHeaders;
import org.springframework.boot.devtools.remote.server.HttpHeaderAccessManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//描述:ajax跨域请求配置
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //允许所有来源访问
        corsConfiguration.addAllowedOrigin("*");
        //允许所有请求头部
        corsConfiguration.addAllowedHeader("*");
        //允许访问的方式
        corsConfiguration.addAllowedMethod("*");
        // 是否支持安全证书
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }
    @Bean
    public WebMvcConfigurer croConfiger(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*")
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        .allowCredentials(true).exposedHeaders(HttpHeaders.SET_COOKIE).maxAge(3600L);
            }
        };
    }

    //跨域过滤器
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
}