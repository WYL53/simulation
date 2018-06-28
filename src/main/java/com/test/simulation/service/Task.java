package com.test.simulation.service;

import com.alibaba.fastjson.JSONObject;
import com.test.simulation.dao.NumberDao;
import com.test.simulation.dao.NumberFlowDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2018/6/28.
 */
@Component
public class Task {

    @Autowired
    private NumberDao numberDao;

    @Autowired
    private NumberFlowDao numberFlowDao;


    @Async
    public void myTask(List<JSONObject> objects) {
//        System.out.println("hahah");
//        numberDao
        for (JSONObject obj :objects) {
            obj.put("flow",1);
            numberFlowDao.update(obj);
        }
    }
}
