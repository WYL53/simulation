package com.test.simulation;

import com.alibaba.fastjson.JSONObject;
import com.test.simulation.dao.UserDao;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

public class MyTask implements Runnable /*,ApplicationContextAware */{

    private UserDao userDao;

    private JSONObject user;

    public MyTask(JSONObject _user,UserDao _userDao) {
        this.user = _user;
        this.userDao = _userDao;
    }

    @Override
    public void run() {
//        UserDao userDao = ThreadLocal<>;
        try {
//            System.out.println(Thread.currentThread().getName()+":"+this.userID);
            Thread.sleep(120);
            this.user.put("age",2);
            userDao.updateUser(this.user);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        System.out.println("setApplicationContext");
//        this.userDao = (UserDao) applicationContext.getBean("userDao");
//    }
}
