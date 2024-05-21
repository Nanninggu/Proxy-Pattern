package com.example.ProxyPattern.proxy;

import com.example.ProxyPattern.dto.Proxy;
import com.example.ProxyPattern.dto.User;
import com.example.ProxyPattern.service.UserService;
import com.example.ProxyPattern.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UserServiceImpl의 내용 변경 없이
 * 추가적인 기능 구현이 가능하다.
 */

@Service
public class UserServiceProxy implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceProxy.class);

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserService userService;

    @Override
    public List<User> getUser(String id) {
        logger.info("Before calling getUser method");
        List<User> user = userServiceImpl.getUser(id);
        logger.info("After calling getUser method");
        return user;
    }

    @Transactional
    @Override
    public List<Proxy> getProxy(String id) {
        logger.info("Before calling getProxy method");
        // proxy 값을 json으로 변환한다.
        ObjectMapper objectMapper = new ObjectMapper();
        List<Proxy> proxy = null;
        try {
            logger.info("Before calling try block");
            proxy = userServiceImpl.getProxy(id); // get proxy
            String payload = objectMapper.writeValueAsString(proxy); // convert to json
            String uuid = java.util.UUID.randomUUID().toString(); // create uuid
            String timestamp = String.valueOf(System.currentTimeMillis()); // create timestamp
            // insert uuid, timestamp, json into event table
            userServiceImpl.insertEvent(uuid, timestamp, payload);
            logger.info("After calling try block");
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("After calling getProxy method");
        return proxy;
    }

    @Override
    public List<User> getAllUser() {
        logger.info("Before calling getAllUser method");
        List<User> user = userServiceImpl.getAllUser();
        logger.info("After calling getAllUser method");
        return user;
    }

}
