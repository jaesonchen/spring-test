package com.asiainfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * TODO
 * 
 * @author       zq
 * @date         2018年1月25日  下午5:32:49
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
@Repository
public class RuleDao {

    @Autowired
    private JdbcTemplate template;
    
    public List<Rule> listRule() {
        
        StringBuilder sql = new StringBuilder();
        sql.append(" select ")
            .append("    rule_id, channel_id, cycle_id, frequency ")
            .append("from mcd_fqc_rule ")
            .append("order by rule_id ");
        return this.template.query(sql.toString(), new BeanPropertyRowMapper<Rule>(Rule.class));
    }
}
