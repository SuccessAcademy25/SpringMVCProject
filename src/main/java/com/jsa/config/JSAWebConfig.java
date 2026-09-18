package com.jsa.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan({ 
	"com.jsa",
	"com.jsa.controller",
    "com.jsa.service",
    "com.jsa.dao" 
    })
public class JSAWebConfig {
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/jsps/");//  /WEB-INF/jsps/index.jsp ,register 
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Bean
	public DriverManagerDataSource getMysqlDS() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/myecomdb");
		ds.setUsername("root");
		ds.setPassword("jitu");

		return ds;
	}

	@Bean
	public JdbcTemplate getJdbcTemp(DataSource myds) {
		JdbcTemplate jdbcTemp = new JdbcTemplate(myds);
		return jdbcTemp;
	}
}



