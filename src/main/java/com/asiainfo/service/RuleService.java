package com.asiainfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO
 * 
 * @author       zq
 * @date         2018年1月25日  下午5:38:15
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
@Service
@Transactional
public class RuleService {

    @Autowired
    private RuleDao dao;
    
    public List<Rule> listRule() {
        return this.dao.listRule();
    }
}
