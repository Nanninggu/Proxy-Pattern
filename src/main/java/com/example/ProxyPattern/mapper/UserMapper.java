package com.example.ProxyPattern.mapper;

import com.example.ProxyPattern.dto.Proxy;
import com.example.ProxyPattern.dto.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users WHERE id = #{id}")
    List<User> getUser(String id);

    @Select("SELECT * FROM proxy_table WHERE id = #{id}")
    List<Proxy> getProxy(String id);

    @Select("SELECT * FROM users")
    List<User> getAllUser();

    @Insert("INSERT INTO events (uuid, timestamp, payload) " +
            "VALUES (#{uuid}, #{timestamp}, #{payload})")
    void insertEvent(String uuid, String timestamp, String payload);

}
