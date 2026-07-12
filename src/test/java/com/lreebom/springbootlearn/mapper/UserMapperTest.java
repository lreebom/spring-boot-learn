package com.lreebom.springbootlearn.mapper;

import com.lreebom.springbootlearn.model.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void insertAndSelectById() {
        User user = new User();
        String suffix = String.valueOf(System.currentTimeMillis());
        user.setUsername("test_user_mapper_" + suffix);
        user.setEmail("test_user_mapper_" + suffix + "@example.com");
        user.setDeptId(1L);
        int rows = userMapper.insert(user);

        Assertions.assertThat(rows).isEqualTo(1);
        Assertions.assertThat(user.getId()).isNotNull();

        User selectedUser = userMapper.selectById(user.getId());

        Assertions.assertThat(selectedUser).isNotNull();
        Assertions.assertThat(selectedUser.getUsername()).isEqualTo(user.getUsername());
        Assertions.assertThat(selectedUser.getEmail()).isEqualTo(user.getEmail());

    }
}
