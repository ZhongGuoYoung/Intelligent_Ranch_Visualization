package com.mlm.mlmserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域
 */
@Configuration
public class CorsConfig {
    /**
     * 注意在servlet容器下，使用的CorsFilter、UrlBasedCorsConfigurationSource
     * <pre>{@code
     * org.springframework.web.filter.CorsFilter;
     * org.springframework.web.cors.UrlBasedCorsConfigurationSource;
     * }</pre>
     */
    @Bean
    public CorsFilter corsFilter() {
        // 跨域信息配置
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");// 允许任何访问源域名
        config.addAllowedHeader("*");// 允许任何访问源请求头
        config.addAllowedMethod("*");// 允许任何访问源请求方法
        // 跨域映射路径
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);// 对任何接口都配置跨域信息
        return new CorsFilter(source);
    }

    // /**
    //  * 注意在reactive容器下，使用的CorsWebFilter、UrlBasedCorsConfigurationSource
    //  * <pre>{@code
    //  * org.springframework.web.cors.reactive.CorsWebFilter;
    //  * org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
    //  * }</pre>
    //  */
    // @Bean
    // public CorsWebFilter corsWebFilter() {
    //     // 跨域信息配置
    //     CorsConfiguration config = new CorsConfiguration();
    //     config.addAllowedOrigin("*");// 允许任何访问源域名
    //     config.addAllowedHeader("*");// 允许任何访问源请求头
    //     config.addAllowedMethod("*");// 允许任何访问源请求方法
    //     // 跨域映射路径
    //     org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     source.registerCorsConfiguration("/**", config);// 对任何接口都配置跨域信息
    //     return new CorsWebFilter(source);
    // }
}
