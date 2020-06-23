package com.itmacy.dev.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * 表现层控制
 * Created by itmacy on 2017/12/26.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

//    @Resource
//    private ApiRequestInterceptor apiRequestInterceptor;
//    @Resource
//    private UploadFileInterceptor uploadFileInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // registry.addInterceptor(new
        // RateLimitInterceptor(200)).addPathPatterns("/**");
//        registry.addInterceptor(apiRequestInterceptor).addPathPatterns("/**");
//        registry.addInterceptor(uploadFileInterceptor).addPathPatterns("/file/upload/**");
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.removeIf(httpMessageConverter -> httpMessageConverter instanceof MappingJackson2HttpMessageConverter);
    }


    /**
     * 允许CORS跨域请求
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "TRACE")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .exposedHeaders("Access-Control-Allow-Headers")
                .maxAge(3600);
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/html/**").addResourceLocations("classpath:/static/html/");
        registry.addResourceHandler("/static/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/static/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/static/jslib/**").addResourceLocations("classpath:/static/jslib/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");


    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
    }
}
