package com.asiainfo.test.mock;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.asiainfo.config.SpringConfig;
import com.asiainfo.config.SpringmvcConfig;
import com.asiainfo.service.RuleController;

/**
 * mockmvc controller 测试，指定controller进行测试
 * 
 * @author       zq
 * @date         2018年1月29日  下午2:09:41
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringConfig.class, SpringmvcConfig.class })
@WebAppConfiguration
public class StandaloneTest {

    @Autowired
    private RuleController controller;

    private MockMvc mockMvc;
    
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();
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
}
