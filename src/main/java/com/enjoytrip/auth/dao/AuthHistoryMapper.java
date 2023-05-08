package com.enjoytrip.auth.dao;

import com.enjoytrip.auth.entity.AuthHistory;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AuthHistoryMapper {

    int insert(AuthHistory authHistory);
    AuthHistory selectByEmailLastAuth(String email);
    int updateAuthStatus(AuthHistory authHistory);
}
