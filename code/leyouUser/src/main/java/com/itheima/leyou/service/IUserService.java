package com.itheima.leyou.service;

import java.util.Map;

public interface IUserService {

    public Map<String, Object> getUser(String username, String password);

    public Map<String, Object> insertUser(String username, String password);
}
