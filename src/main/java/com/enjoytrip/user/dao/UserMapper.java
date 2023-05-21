package com.enjoytrip.user.dao;

import com.enjoytrip.user.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int insert(User user);
    User selectById(Long id);
    List<User> selectAll();

    User selectByEmail(String email);

    int updateProfile(User user);
    int deleteById(Long id);
}
