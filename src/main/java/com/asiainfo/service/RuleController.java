package com.asiainfo.service;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TODO
 * 
 * @author       zq
 * @date         2018年1月25日  下午5:39:16
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
@Controller
@RequestMapping("/service")
public class RuleController {

    @Autowired
    private RuleService service;
    
    @RequestMapping("/list")
    @ResponseBody
    public List<Rule> list(HttpServletRequest request, HttpServletResponse response) {
        
        System.out.println("RuleController.list() executed!");
        return this.service.listRule();
    }
    
    @RequestMapping("/get/{name}")
    @ResponseBody 
    public User get(@PathVariable("name") String name) {
        
        System.out.println("RuleController.get() executed, name=" + name);
        User user = new User();
        user.setName(name);
        user.setSex("male");
        return user;
    }

    @RequestMapping("/post")
    @ResponseBody
    public Map<String, Object> post(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        
        System.out.println("RuleController.post() executed, user=" + user);
        Map<String, Object> result = new HashMap<>();
        result.put("name", user.getName());
        result.put("sex", user.getSex());
        result.put("status", "200");
        return result;
    }
    
    @RequestMapping("/form")
    public void formPost(@ModelAttribute User user, Writer writer) throws IOException {
        
        System.out.println("RuleController.formPost() executed, user=" + user);
        writer.write("<html><body>success</body></html>");
    }
    
    public static class User implements java.io.Serializable {

        /** serialVersionUID */
        private static final long serialVersionUID = 1L;
        private String name;
        private String sex;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getSex() {
            return sex;
        }
        public void setSex(String sex) {
            this.sex = sex;
        }
        @Override
        public String toString() {
            return "User [name=" + name + ", sex=" + sex + "]";
        }
    }
}