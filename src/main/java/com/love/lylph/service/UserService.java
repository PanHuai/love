package com.love.lylph.service;

import com.love.lylph.pojo.User;

/**
 * @author PanHuai
 * @data 2018/7/31 14:42
 */
public interface UserService {

    public User getUser(String username,String password);

    public int add(User user);
}
