package com.test.simulation;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSONObject;
import com.test.simulation.dao.UserDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@MapperScan("com.test.simulation.dao")
@EnableAsync
public class SimulationApplication {

    @Autowired
    private UserDao userDao;

	@Bean
	public TaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(20);
		executor.setMaxPoolSize(20);
		executor.setQueueCapacity(1000);
		return executor;
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.druid.datasource")
	public DataSource dataSource(){
//		DruidDataSource dataSource = new DruidDataSource();
//		dataSource.setUrl("jdbc:mysql://localhost:3306/gdj?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&allowMultiQueries=true");
//		dataSource.setUsername("root");
//		dataSource.setPassword("mysql");
//		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//
//		dataSource.setMaxActive(20);
//		dataSource.setInitialSize(5);
		return new DruidDataSource();
	}

	public static void main(String[] args) {
		SpringApplication.run(SimulationApplication.class, args);
	}

}
