package com.love.lylph.service.Impl;

import com.love.lylph.mapper.UserMapper;
import com.love.lylph.pojo.User;
import com.love.lylph.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author PanHuai
 * @data 2018/7/31 14:44
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUserName(String username) {
        return userMapper.findUserByUserName(username) ;
    }

    @Override
    public int insert(User user) {
        return userMapper.insertSelective(user);
    }
}
