package com.example.ProxyPattern.controller;

import com.example.ProxyPattern.dto.Proxy;
import com.example.ProxyPattern.dto.User;
import com.example.ProxyPattern.proxy.UserServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserServiceProxy userServiceProxy;

    @GetMapping("/user/{id}")
    public List<User> getUser(@PathVariable String id) {
        return userServiceProxy.getUser(id);
    }

    // add this method
    @GetMapping("/proxy/{id}")
    public List<Proxy> getProxy(@PathVariable String id) {
        return userServiceProxy.getProxy(id);
    }

    @GetMapping("/users")
    public List<User> getAllUser() {
        return userServiceProxy.getAllUser();
    }

}
