package com.test.simulation.service;

import com.alibaba.fastjson.JSONObject;
import com.test.simulation.dao.NumberDao;
import com.test.simulation.dao.NumberFlowDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2018/6/28.
 */
@Component
public class TaskService {

    @Autowired
    private NumberDao numberDao;

    @Autowired
    private NumberFlowDao numberFlowDao;

    @Autowired
    private ApiService apiService;

    @Autowired
    private LimitService limitService;

    @Async
    public void myTask(List<JSONObject> objects,CountDownLatch latch) {
        try {
            limitService.require();
            System.out.println(new SimpleDateFormat("hh:mm:ss").format(new Date())+" "+
                    Thread.currentThread().getName()+" 请求接口");
            apiService.getData(objects);
            for (JSONObject obj :objects) {
                JSONObject j = new JSONObject(3);
                j.put("number",obj.get("number"));
                j.put("flow",100);
                j.put("time",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                numberFlowDao.add(j);

                obj.put("status",1777);
                numberDao.update(obj);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (latch != null){
                latch.countDown();
            }
            System.out.println( Thread.currentThread().getName()+" 完成任务。");
        }

    }
}
