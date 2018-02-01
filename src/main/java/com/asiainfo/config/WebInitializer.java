package com.asiainfo.config;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 取代web.xml
 * 
 * @author       zq
 * @date         2018年1月30日  下午4:33:23
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /* 
     * 设置spring上下文配置类
     * @return
     * @see org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer#getRootConfigClasses()
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { SpringConfig.class };
    }

    /* 
     * 设置springmvc上下文配置类
     * @return
     * @see org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer#getServletConfigClasses()
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { SpringmvcConfig.class };
    }

    /* 
     * springmvc Dispatcher映射的地址
     * @return
     * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#getServletMappings()
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
    
    /*
     * 注册filter
     * @return
     * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#getServletFilters()
     */
    @Override
    protected Filter[] getServletFilters() {
        
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setForceEncoding(true);
        encodingFilter.setEncoding("UTF-8");
        return new Filter[] {encodingFilter};
    }

    /*
     * registerDispatcherServlet完成时调用自定义registration
     * @param registration
     * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#customizeRegistration(javax.servlet.ServletRegistration.Dynamic)
     */
    @Override
    protected void customizeRegistration(Dynamic registration) {
        
        super.customizeRegistration(registration);
        registration.setInitParameter("spring.profiles.active", "bj");
    }
}
