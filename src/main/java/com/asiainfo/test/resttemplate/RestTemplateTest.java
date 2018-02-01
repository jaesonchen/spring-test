package com.asiainfo.test.resttemplate;

import org.springframework.web.client.RestTemplate;

import com.asiainfo.service.RuleController;

/**
 * TODO
 * 
 * @author       zq
 * @date         2018年2月1日  下午3:17:28
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class RestTemplateTest {

    /** 
     * TODO
     * 
     * @param args
     */
    public static void main(String[] args) {

        String url = "http://localhost:8080/springmock-test/service/get/{name}";
        RuleController.User user = new RestTemplate().getForObject(url, RuleController.User.class, "chenzq");
        System.out.println("user=" + user);
    }
}
