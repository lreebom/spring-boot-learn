package com.lreebom.springbootlearn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lreebom.springbootlearn.common.BusinessException;
import com.lreebom.springbootlearn.common.PageResult;
import com.lreebom.springbootlearn.mapper.DeptMapper;
import com.lreebom.springbootlearn.mapper.UserMapper;
import com.lreebom.springbootlearn.model.dto.UserCreateDTO;
import com.lreebom.springbootlearn.model.dto.UserDeleteDTO;
import com.lreebom.springbootlearn.model.dto.UserPageQueryDTO;
import com.lreebom.springbootlearn.model.dto.UserUpdateDTO;
import com.lreebom.springbootlearn.model.entity.Dept;
import com.lreebom.springbootlearn.model.entity.User;
import com.lreebom.springbootlearn.model.enums.UserStatusEnum;
import com.lreebom.springbootlearn.model.vo.UserVO;
import com.lreebom.springbootlearn.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final DeptMapper deptMapper;

    public UserServiceImpl(UserMapper userMapper, DeptMapper deptMapper) {
        this.userMapper = userMapper;
        this.deptMapper = deptMapper;
    }

    private static UserVO convertToVO(User user) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        userVO.setStatusName(UserStatusEnum.getNameByCode(user.getStatus()));
        return userVO;
    }

    @Override
    public UserVO getById(Long id) {
        UserVO user = userMapper.selectDetailById(id);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        user.setStatusName(UserStatusEnum.getNameByCode(user.getStatus()));
        return user;
    }

    @Override
    public Long create(UserCreateDTO createDTO) {

        User existUser = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, createDTO.getUsername()));

        if (existUser != null) {
            throw new BusinessException("用户名已存在");
        }

        User existEmail = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getEmail, createDTO.getEmail()));

        if (existEmail != null) {
            throw new BusinessException("邮箱已存在");
        }

        Dept dept = deptMapper.selectById(createDTO.getDeptId());
        if (dept == null) {
            throw new BusinessException("部门不存在");
        }

        User user = new User();

        BeanUtils.copyProperties(createDTO, user);

        int rows = userMapper.insert(user);
        if (rows != 1) {
            throw new BusinessException("创建用户失败");
        }
        log.info("创建用户成功，userId={} username={}", user.getId(), user.getUsername());
        return user.getId();
    }

    @Override
    public PageResult<UserVO> page(UserPageQueryDTO queryDTO) {
        if (queryDTO.getStatus() != null && !UserStatusEnum.contains(queryDTO.getStatus())) {
            throw new BusinessException("用户状态不合法");
        }

        Page<UserVO> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());

        IPage<UserVO> pageResult = userMapper.selectPage(page, queryDTO);

        List<UserVO> records = pageResult.getRecords();

        records.forEach(user -> user.setStatusName(UserStatusEnum.getNameByCode(user.getStatus())));

        return new PageResult<>(pageResult.getTotal(), pageResult.getCurrent(), pageResult.getPages(), records);
    }

    @Override
    public void update(UserUpdateDTO updateDTO) {
        if (updateDTO.getId() == null) {
            throw new BusinessException("用户ID不能为空");
        }

        if (updateDTO.getUsername() == null && updateDTO.getEmail() == null && updateDTO.getStatus() == null && updateDTO.getDeptId() == null) {
            throw new BusinessException("至少更新一个字段");
        }

        User user = userMapper.selectById(updateDTO.getId());
        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        if (updateDTO.getUsername() != null) {
            User existUsername = userMapper.selectOne(new LambdaQueryWrapper<User>()
                    .eq(User::getUsername, updateDTO.getUsername())
                    .ne(User::getId, updateDTO.getId()));
            if (existUsername != null) {
                throw new BusinessException("用户名已存在");
            }
        }

        if (updateDTO.getEmail() != null) {
            User existEmail = userMapper.selectOne(new LambdaQueryWrapper<User>()
                    .eq(User::getEmail, updateDTO.getEmail())
                    .ne(User::getId, updateDTO.getId()));
            if (existEmail != null) {
                throw new BusinessException("邮箱已存在");
            }
        }

        if (updateDTO.getStatus() != null && !UserStatusEnum.contains(updateDTO.getStatus())) {
            throw new BusinessException("用户状态不合法");
        }

        if (updateDTO.getDeptId() != null) {
            Dept dept = deptMapper.selectById(updateDTO.getDeptId());
            if (dept == null) {
                throw new BusinessException("部门不存在");
            }
        }

        User updateUser = new User();
        BeanUtils.copyProperties(updateDTO, updateUser);

        int rows = userMapper.updateById(updateUser);
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