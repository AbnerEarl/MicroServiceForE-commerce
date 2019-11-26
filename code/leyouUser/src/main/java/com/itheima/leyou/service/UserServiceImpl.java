package com.itheima.leyou.service;

import com.itheima.leyou.dao.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserDao iUserDao;

    public Map<String, Object> getUser(String username, String password){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        //1、判断传入的参数
        if (username==null||username.equals("")){
            resultMap.put("result", false);
            resultMap.put("msg", "用户名不能为空！");
            return resultMap;
        }

        //2、调userdao的方法
        ArrayList<Map<String, Object>> list = iUserDao.getUser(username, password);

        //3、如果没有查询到返回错误信息
        if (list==null||list.size()==0){
            resultMap.put("result", false);
            resultMap.put("msg", "没有查询到数据！");
            return resultMap;
        }

        //4、返回正常信息
        resultMap = list.get(0);
        resultMap.put("result", true);
        resultMap.put("msg", "");
        return resultMap;
    }

    public Map<String, Object> insertUser(String username, String password){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        //1、判断传入的参数
        if (username==null||username.equals("")){
            resultMap.put("result", false);
            resultMap.put("msg", "用户名不能为空！");
            return resultMap;
        }

        int new_id = iUserDao.insertUser(username, password);

        resultMap.put("user_id", new_id);
        resultMap.put("username", username);
        resultMap.put("phone", username);
        resultMap.put("password", password);
        resultMap.put("result", true);
        resultMap.put("msg", "");
        return resultMap;
    }
}
