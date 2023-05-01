package com.enjoytrip.user.dao;

import com.enjoytrip.user.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int insert(User user);
    List<User> selectAll();
}
