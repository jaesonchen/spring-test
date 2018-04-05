package com.asiainfo.test.resttemplate;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.asiainfo.service.RuleController;
import com.fasterxml.jackson.databind.ObjectMapper;

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
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {

        // rest get
        System.out.println(get("http://localhost:18080/springmock-test/service/get/{name}", String.class, "chenzq"));
        System.out.println(get("http://localhost:18080/springmock-test/service/get/{name}", RuleController.User.class, "chenzq"));
        
        // form post
        Map<String, String> map = new HashMap<>();
        map.put("name", "czq");
        map.put("sex", "male");
        System.out.println(formPost("http://localhost:18080/springmock-test/service/form", String.class, map));
        
        // payload post
        map = new HashMap<>();
        map.put("name", "czq");
        map.put("sex", "male");
        System.out.println(jsonPost("http://localhost:18080/springmock-test/service/post", String.class, map));
        System.out.println(jsonPost("http://localhost:18080/springmock-test/service/post", Map.class, map));
    }
    
    // rest get
    public static <T> T get(String url, Class<T> clazz, Object... urlVariables) {
        return new RestTemplate().getForObject(url, clazz, urlVariables);
    }
    
    // form post
    public static <T> T formPost(String url, Class<T> clazz, Map<?, ?> map) {
        
        HttpHeaders headers = new HttpHeaders();
        // 表单提交方式
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        // 封装参数，不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        if (null != map) {
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                params.add(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
            }
        }
        // 请求对象
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        // post
        // ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.POST, requestEntity, clazz);
        ResponseEntity<T> response = new RestTemplate().postForEntity(url, requestEntity, clazz);
        return response.getBody();
    }
    
    // json post (payload)
    public static <T> T jsonPost(String url, Class<T> clazz, Map<?, ?> map) throws Exception {
        
        HttpHeaders headers = new HttpHeaders();
        // json提交方式
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> params = new HashMap<>();
        if (null != map) {
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                params.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
            }
        }
        // 将提交的数据转换为json
        String json = mapper.writeValueAsString(params);
        HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
        ResponseEntity<T> response = new RestTemplate().postForEntity(url, requestEntity, clazz);
        return response.getBody();
    }
}
