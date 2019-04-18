package com.asiainfo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * springmvc 配置
 * 
 * @author       zq
 * @date         2018年1月30日  下午4:36:01
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
@Configuration
@EnableWebMvc
@ComponentScan(
        basePackages = {"com.asiainfo.service"}, 
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class), 
        useDefaultFilters = false)
public class SpringmvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 视图解析器
     *
     * @return
     */
    @Bean
    public ViewResolver internalResourceViewResolver() {
        
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setViewClass(JstlView.class);
        internalResourceViewResolver.setPrefix("/WEB-INF/view/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }
    
    /**
     * 初始化RequestMappingHandlerAdapter、加载Json转换器
     * 
     * @return
     */
    @Bean
    public HandlerAdapter requestMappingHandlerAdapter() {
        
        RequestMappingHandlerAdapter rmhd = new RequestMappingHandlerAdapter();
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        jsonConverter.setSupportedMediaTypes(mediaTypes);
        rmhd.getMessageConverters().add(jsonConverter);
        return rmhd;
    }
    
    /**
     * 配置静态资源处理
     *
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}