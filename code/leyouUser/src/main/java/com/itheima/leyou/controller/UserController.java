package com.itheima.leyou.controller;

import com.alibaba.fastjson.JSON;
import com.itheima.leyou.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> login(String username, String password, HttpServletRequest httpServletRequest){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        //1、取会员
        resultMap = iUserService.getUser(username, password);

        //2、如果没有取到会员，写入会员
        if (!(Boolean) resultMap.get("result")){
            resultMap = iUserService.insertUser(username, password);
        }

        //3、写入session
        HttpSession httpSession = httpServletRequest.getSession();
        String user = JSON.toJSONString(resultMap);
        httpSession.setAttribute("user", user);

        //4、返回信息
        return resultMap;
    }
}
