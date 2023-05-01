package com.enjoytrip.user;


import static org.assertj.core.api.Assertions.assertThat;

import com.enjoytrip.user.dao.UserMapper;
import com.enjoytrip.user.entity.Provider;
import com.enjoytrip.user.entity.Role;
import com.enjoytrip.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.annotation.Rollback;

@MybatisTest

// 실 데이터베이스에 연결 시 필요한 어노테이션
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class MyBatisUserTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    @DisplayName("user 생성 테스트")
//    @Rollback(value = false)// 이걸 넣으면 rollback이 자동 실패됨.
    void insert_userTest(){
        User user = User.builder()
            .email("email@email")
            .nickname("nickUser")
            .password("qweqwe")
            .provider(Provider.LOCAL)
            .role(Role.USER)
            .build();

        int insert = userMapper.insert(user);
        assertThat(insert).isEqualTo(1);
    }

    @Test
    @DisplayName("user email로 조회 테스트")
    void selectEmailTest(){
        User user = User.builder()
            .email("email@email")
            .nickname("nickUser")
            .password("qweqwe")
            .provider(Provider.LOCAL)
            .role(Role.USER)
            .build();
        userMapper.insert(user);

        User selectUser = userMapper.selectByEmail(user.getEmail());
        assertThat(selectUser.getNickname()).isEqualTo(user.getNickname());
    }
}
