package com.example.ProxyPattern.service;

import com.example.ProxyPattern.dto.Proxy;
import com.example.ProxyPattern.dto.User;

import java.util.List;

public interface UserService {
    List<User> getUser(String id);

    List<Proxy> getProxy(String id);

    List<User> getAllUser();

}
