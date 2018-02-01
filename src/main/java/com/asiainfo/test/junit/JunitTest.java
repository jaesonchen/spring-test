package com.asiainfo.test.junit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.asiainfo.config.DataSourceConfig;
import com.asiainfo.service.RuleDao;
import com.asiainfo.service.RuleService;

/**
 * service、dao、component层测试
 * 
 * @author       zq
 * @date         2018年1月29日  上午10:29:11
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
@Component
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class, RuleService.class, RuleDao.class})
public class JunitTest {

    @Autowired
    private RuleService service;
    
    @Before
    @Transactional(readOnly = true)
    public void setUp() {
        System.out.println("do something init!");
    }
    
    @Test
    @Transactional(readOnly = true)
    public void query() {
        this.service.listRule().forEach(System.out::println);
    }
}
