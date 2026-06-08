package com.lreebom.springbootlearn.service.impl;

import com.lreebom.springbootlearn.common.BusinessException;
import com.lreebom.springbootlearn.common.PageResult;
import com.lreebom.springbootlearn.mapper.UserMapper;
import com.lreebom.springbootlearn.model.dto.UserCreateDTO;
import com.lreebom.springbootlearn.model.dto.UserDeleteDTO;
import com.lreebom.springbootlearn.model.dto.UserPageQueryDTO;
import com.lreebom.springbootlearn.model.dto.UserUpdateDTO;
import com.lreebom.springbootlearn.model.entity.User;
import com.lreebom.springbootlearn.model.enums.UserStatusEnum;
import com.lreebom.springbootlearn.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getById(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        return user;
    }

    @Override
    public Long create(UserCreateDTO createDTO) {

        User existUser = userMapper.selectByUsername(createDTO.getUsername());
        if (existUser != null) {
            throw new BusinessException("用户名已存在");
        }

        User existEmail = userMapper.selectByEmail(createDTO.getEmail());
        if (existEmail != null) {
            throw new BusinessException("邮箱已存在");
        }

        User user = new User();
        user.setUsername(createDTO.getUsername());
        user.setEmail(createDTO.getEmail());

        int rows = userMapper.insert(user);
        if (rows != 1) {
            throw new BusinessException("创建用户失败");
        }
        log.info("创建用户成功，userId={} username={}", user.getId(), user.getUsername());
        return user.getId();
    }

    @Override
    public PageResult<User> page(UserPageQueryDTO queryDTO) {
        if (queryDTO.getStatus() != null && !UserStatusEnum.contains(queryDTO.getStatus())) {
            throw new BusinessException("用户状态不合法");
        }

        int offset = (queryDTO.getPageNum() - 1) * queryDTO.getPageSize();

        List<User> records = userMapper.selectPage(queryDTO, offset, queryDTO.getPageSize());
        long total = userMapper.count(queryDTO);

        return new PageResult<>(total, records);
    }

    @Override
    public void update(UserUpdateDTO updateDTO) {
        if (updateDTO.getId() == null) {
            throw new BusinessException("用户ID不能为空");
        }

        if (updateDTO.getUsername() == null && updateDTO.getEmail() == null && updateDTO.getStatus() == null) {
            throw new BusinessException("至少更新一个字段");
        }

        User user = userMapper.selectById(updateDTO.getId());
        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        if (updateDTO.getUsername() != null) {
            User existUsername = userMapper.selectByUsername(updateDTO.getUsername());
            if (existUsername != null && !existUsername.getId().equals(updateDTO.getId())) {
                throw new BusinessException("用户名已存在");
            }
        }

        if (updateDTO.getEmail() != null) {
            User existEmail = userMapper.selectByEmail(updateDTO.getEmail());
            if (existEmail != null && !existEmail.getId().equals(updateDTO.getId())) {
                throw new BusinessException("邮箱已存在");
            }
        }

        if (updateDTO.getStatus() != null && !UserStatusEnum.contains(updateDTO.getStatus())) {
            throw new BusinessException("用户状态不合法");
        }

        User updateUser = new User();
        updateUser.setId(updateDTO.getId());
        updateUser.setUsername(updateDTO.getUsername());
        updateUser.setEmail(updateDTO.getEmail());
        updateUser.setStatus(updateDTO.getStatus());

        int rows = userMapper.update(updateUser);
        if (rows != 1) {
            throw new BusinessException("更新用户失败");
        }
        log.info("更新用户成功，userId={}", updateUser.getId());
    }

    @Override
    public void delete(UserDeleteDTO deleteDTO) {
        User user = userMapper.selectById(deleteDTO.getId());
        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        int rows = userMapper.deleteById(deleteDTO.getId());
        if (rows != 1) {
            throw new BusinessException("删除用户失败");
        }
        log.info("删除用户成功，userId={} username={}", user.getId(), user.getUsername());
    }
}