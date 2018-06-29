package com.test.simulation.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiService {

    public List<JSONObject> getData(List<JSONObject> queryList)throws InterruptedException{
        Thread.sleep(13000);
        return  queryList;
    }
}
