package com.lreebom.springbootlearn.service;

import com.lreebom.springbootlearn.common.PageResult;
import com.lreebom.springbootlearn.model.dto.UserCreateDTO;
import com.lreebom.springbootlearn.model.dto.UserDeleteDTO;
import com.lreebom.springbootlearn.model.dto.UserPageQueryDTO;
import com.lreebom.springbootlearn.model.dto.UserUpdateDTO;
import com.lreebom.springbootlearn.model.vo.UserVO;

public interface UserService {
    UserVO getById(Long id);

    Long create(UserCreateDTO createDTO);

    PageResult<UserVO> page(UserPageQueryDTO pageQueryDTO);

    void update(UserUpdateDTO updateDTO);

    void delete(UserDeleteDTO deleteDTO);
}
