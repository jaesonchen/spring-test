package com.asiainfo.test.mock;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.servlet.http.Cookie;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.asiainfo.config.SpringConfig;
import com.asiainfo.config.SpringmvcConfig;

/**
 * mockmvc controller 测试，对整个web环境进行测试，包括interceptor
 * 
 * @author       zq
 * @date         2018年1月25日  下午5:43:29
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringConfig.class, SpringmvcConfig.class })
@WebAppConfiguration
@ActiveProfiles("bj")
public class WebContextTest {
    
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testGet() throws Exception {
        
        mockMvc.perform(
                get("/service/get/{name}", "chenzq")
                    .characterEncoding("utf-8")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("name").value("chenzq"))
            .andExpect(jsonPath("sex").value("male"))
            .andDo(print());
            //.andReturn();
    }
    
    @Test
    public void testPost() throws Exception {
        
        mockMvc.perform(
                post("/service/post")
                    .characterEncoding("utf-8")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content("{\"name\":\"chenzq\", \"sex\":\"male\"}")
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("name").value("chenzq"))
            .andExpect(jsonPath("sex").value("male"))
            .andExpect(jsonPath("status").value("200"))
            .andDo(print());
    }
    
    @Test
    public void testSessionAttr() throws Exception {
        
        mockMvc.perform(
                get("/service/sessionAttr")
                    .characterEncoding("utf-8")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .sessionAttr("name", "jaeson")
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("name").value("jaeson"))
            .andExpect(jsonPath("sex").value("male"))
            .andDo(print());
    }
    
    @Test
    public void testCookie() throws Exception {
        
        mockMvc.perform(
                get("/service/cookie")
                    .characterEncoding("utf-8")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .cookie(new Cookie("sessionId", "1234567890abcde"))
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("sessionId").value("1234567890abcde"))
            .andDo(print());
    }
    
    @Test
    public void testUpload() throws Exception {
        
        mockMvc.perform(
                fileUpload("/service/upload")
                    .file(new MockMultipartFile("file", "filename.txt", "text/plain", "file content".getBytes()))
                    .param("userId", "jaeson")
            )
            .andExpect(status().isOk())
            .andExpect(view().name("success"))
            .andDo(print());
    }
}
