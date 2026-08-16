package com.lreebom.springbootlearn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lreebom.springbootlearn.common.BusinessException;
import com.lreebom.springbootlearn.mapper.RoleMapper;
import com.lreebom.springbootlearn.model.dto.RoleCreateDTO;
import com.lreebom.springbootlearn.model.entity.Role;
import com.lreebom.springbootlearn.model.enums.RoleStatusEnum;
import com.lreebom.springbootlearn.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public String create(RoleCreateDTO createDTO) {

        Role existRoleCode = roleMapper.selectOne(new LambdaQueryWrapper<Role>().eq(Role::getRoleCode, createDTO.getRoleCode()));
        if (existRoleCode != null) {
            throw new BusinessException("角色编码已存在");
        }

        Role existRoleName = roleMapper.selectOne(new LambdaQueryWrapper<Role>().eq(Role::getRoleName, createDTO.getRoleName()));
        if (existRoleName != null) {
            throw new BusinessException("角色名称已存在");
        }

        Role role = new Role();
        BeanUtils.copyProperties(createDTO, role);
        role.setStatus(RoleStatusEnum.ENABLED.getCode());

        int rows = roleMapper.insert(role);
        if (rows != 1) {
            throw new BusinessException("创建角色失败");
        }

        log.info("创建角色成功，roleId={} roleCode={}", role.getId(), role.getRoleCode());
        return String.valueOf(role.getId());
    }
}
