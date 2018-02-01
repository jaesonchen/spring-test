package com.asiainfo.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description: TODO
 * 
 * @author       zq
 * @date         2017年10月20日  下午3:44:51
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement(proxyTargetClass = true)
public class DataSourceConfig {

    @Autowired
    private Environment env;
    
	@Bean(name = "dataSource")
	@Qualifier("dataSource")
    public DataSource dataSource() {
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName(env.getProperty("env.db.driver", "oracle.jdbc.driver.OracleDriver"));
	    dataSource.setUrl(env.getProperty("env.db.url", "jdbc:oracle:thin:@10.1.253.75:1521:ora11g"));
	    dataSource.setUsername(env.getProperty("env.db.username", "mcd_core_bj"));
	    dataSource.setPassword(env.getProperty("env.db.password", "mcd_core_bj"));
        return dataSource;
    }

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("dataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
