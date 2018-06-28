package com.test.simulation;

import com.alibaba.fastjson.JSONObject;
import com.test.simulation.dao.UserDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@MapperScan("com.test.simulation.dao")
public class SimulationApplication {

    @Autowired
    private UserDao userDao;

	public static void main(String[] args) {
//		System.out.println("hello");
		ApplicationContext context = SpringApplication.run(SimulationApplication.class, args);
//        SimulationApplication simulationApplication =  new SimulationApplication();
		SimulationApplication simulationApplication = (SimulationApplication) context.getBean("simulationApplication");
        List<JSONObject> users = simulationApplication.getUserList();
		System.out.println(users);
		simulationApplication.startTasks(users);
//		String[] ss = new String[100];

//		startTasks(ss);
//		ApplicationContext applicationContext =
	}

	// 关键3
	@PostConstruct
	public void init() {
//		this.userDao
		System.out.println(this.userDao);
	}

	public void startTasks(List<JSONObject> users){
		ExecutorService executorService = Executors.newFixedThreadPool(20);
		for (JSONObject user : users) {
			executorService.execute(new MyTask(user,userDao));
		}
		executorService.shutdown();
	}

	private List<JSONObject> getUserList(){
//	    return
		return userDao.getList();
    }
}
