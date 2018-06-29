package com.test.simulation.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface NumberDao {

    List<JSONObject> getList();

    int update(JSONObject object);
//    int updateUser(JSONObject object);
}
