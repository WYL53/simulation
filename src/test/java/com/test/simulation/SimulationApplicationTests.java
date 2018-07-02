package com.test.simulation;

import com.alibaba.fastjson.JSONObject;
import com.test.simulation.dao.NumberDao;
import com.test.simulation.service.LimitService;
import com.test.simulation.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimulationApplicationTests {

	@Autowired
	private TaskService task;

	@Autowired
	private LimitService limitService;

	@Autowired
	private NumberDao numberDao;

	@Autowired
	private ThreadPoolTaskExecutor executor;

	private CountDownLatch latch;

	@Test
	public void contextLoads() throws Exception {
		Integer latchSize = 0;
		limitService.start();
		int index = 0;
		int size = 100;
		List<JSONObject> list = numberDao.getList();
		latchSize = list.size() / size;
		if(list.size() % size > 0){
			latchSize++;
		}
		latch = new CountDownLatch(latchSize);
		while (index < list.size()){
			if(index + size > list.size()){
				size = list.size() - index;
			}
			List<JSONObject> sl = list.subList(index,index + size);
			task.myTask(sl,latch);
			index += size;
		}
//		while (executor.getThreadPoolExecutor().getQueue().size()>1){
//			System.out.println(System.currentTimeMillis()+" queueSize:"+executor.getThreadPoolExecutor().getQueue().size());
//			Thread.sleep(1000);
//		}
		latch.await();
		limitService.shutDown();
		Thread.sleep(4000);
		executor.shutdown();
		System.out.println("完成。");
	}

}
