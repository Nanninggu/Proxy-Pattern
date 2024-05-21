package com.example.ProxyPattern.service;

import com.example.ProxyPattern.dto.Proxy;
import com.example.ProxyPattern.dto.User;
import com.example.ProxyPattern.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUser(String id) {
        return userMapper.getUser(id);
    }

    // add this method
    @Override
    public List<Proxy> getProxy(String id) {
        return userMapper.getProxy(id);
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    public void insertEvent(String uuid, String timestamp, String payload) {
        userMapper.insertEvent(uuid, timestamp, payload);
    }

}
