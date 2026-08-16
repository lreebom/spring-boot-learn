package com.lreebom.springbootlearn.controller;

import com.lreebom.springbootlearn.common.R;
import com.lreebom.springbootlearn.model.dto.RoleCreateDTO;
import com.lreebom.springbootlearn.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping("/create")
    public R<String> create(@RequestBody @Validated RoleCreateDTO createDTO) {
        return R.ok(roleService.create(createDTO));
    }
}
