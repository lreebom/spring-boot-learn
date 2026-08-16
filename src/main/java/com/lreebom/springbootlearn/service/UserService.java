package com.lreebom.springbootlearn.service;

import com.lreebom.springbootlearn.common.PageResult;
import com.lreebom.springbootlearn.model.dto.*;
import com.lreebom.springbootlearn.model.vo.RoleVO;
import com.lreebom.springbootlearn.model.vo.UserVO;

import java.util.List;

public interface UserService {
    UserVO getById(Long id);

    Long create(UserCreateDTO createDTO);

    PageResult<UserVO> page(UserPageQueryDTO pageQueryDTO);

    void update(UserUpdateDTO updateDTO);

    void delete(UserDeleteDTO deleteDTO);

    void assignRoles(UserRoleAssignDTO assignDTO);

    List<RoleVO> getRolesByUserId(Long userId);
}
