package com.lreebom.springbootlearn.service;

import com.lreebom.springbootlearn.model.dto.RoleCreateDTO;

public interface RoleService {
    String create(RoleCreateDTO createDTO);
}
