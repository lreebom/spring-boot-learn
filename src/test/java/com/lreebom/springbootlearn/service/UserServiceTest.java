package com.lreebom.springbootlearn.service;

import com.lreebom.springbootlearn.common.BusinessException;
import com.lreebom.springbootlearn.model.dto.UserCreateDTO;
import com.lreebom.springbootlearn.model.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void createSuccess() {
        String suffix = String.valueOf(System.currentTimeMillis());

        UserCreateDTO createDTO = new UserCreateDTO();
        createDTO.setUsername("test_user_service_" + suffix);
        createDTO.setEmail("test_user_service_" + suffix + "@example.com");

        Long id = userService.create(createDTO);

        Assertions.assertThat(id).isNotNull();

        User user = userService.getById(id);
        Assertions.assertThat(user.getUsername()).isEqualTo(createDTO.getUsername());
        Assertions.assertThat(user.getEmail()).isEqualTo(createDTO.getEmail());
    }

    @Test
    void createFailWhenUsernameExists() {
        String suffix = String.valueOf(System.currentTimeMillis());

        UserCreateDTO firstDTO = new UserCreateDTO();
        firstDTO.setUsername("test_user_service_" + suffix);
        firstDTO.setEmail("test_user_service_" + suffix + "@example.com");
        userService.create(firstDTO);

        UserCreateDTO secondDTO = new UserCreateDTO();
        secondDTO.setUsername(firstDTO.getUsername());
        secondDTO.setEmail("test_user_service_" + suffix + "2@example.com");

        Assertions.assertThatThrownBy(() -> userService.create(secondDTO)).isInstanceOf(BusinessException.class).hasMessage("用户名已存在");

    }
}
