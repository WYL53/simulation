package com.test.simulation.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface UserDao {

    List<JSONObject> getList();

    int updateUser(JSONObject object);
}
