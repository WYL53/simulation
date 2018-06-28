package com.test.simulation;

import com.alibaba.fastjson.JSONObject;
import com.test.simulation.dao.NumberDao;
import com.test.simulation.dao.NumberFlowDao;
import com.test.simulation.service.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimulationApplicationTests {

	@Autowired
	private Task task;

	@Autowired
	private NumberDao numberDao;

	@Test
	public void contextLoads() throws Exception {
		int index = 0;
		List<JSONObject> list = numberDao.getList();
		while (index < list.size()){
			List<JSONObject> sl = list.subList(index,index + 100);
			task.myTask(sl);
			index += 100;
		}
		Thread.currentThread().join();
	}

}
