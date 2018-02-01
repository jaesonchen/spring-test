package com.asiainfo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * spring 配置
 * 
 * @author       zq
 * @date         2018年1月30日  下午4:33:53
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(
        basePackages = { "com.asiainfo.service, com.asiainfo.config" }, 
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = { Controller.class, EnableWebMvc.class }))
public class SpringConfig {

}
