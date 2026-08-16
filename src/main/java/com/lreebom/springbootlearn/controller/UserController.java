package com.lreebom.springbootlearn.controller;

import com.lreebom.springbootlearn.common.PageResult;
import com.lreebom.springbootlearn.common.R;
import com.lreebom.springbootlearn.model.dto.*;
import com.lreebom.springbootlearn.model.vo.RoleVO;
import com.lreebom.springbootlearn.model.vo.UserVO;
import com.lreebom.springbootlearn.service.UserService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getById")
    public R<UserVO> getById(@RequestParam @NotNull(message = "用户ID不能为空") @Min(value = 1, message = "用户ID必须大于0") Long id) {
        return R.ok(userService.getById(id));
    }

    @PostMapping("/create")
    public R<Long> create(@RequestBody @Validated UserCreateDTO createDTO) {
        return R.ok(userService.create(createDTO));
    }

    @GetMapping("/list")
    public R<PageResult<UserVO>> list(@Validated UserPageQueryDTO queryDTO) {
        return R.ok(userService.page(queryDTO));
    }

    @PostMapping("/update")
    public R<Void> update(@RequestBody @Validated UserUpdateDTO updateDTO) {
        userService.update(updateDTO);
        return R.ok();
    }

    @PostMapping("/delete")
    public R<Void> delete(@RequestBody @Validated UserDeleteDTO deleteDTO) {
        userService.delete(deleteDTO);
        return R.ok();
    }

    @PostMapping("/assignRoles")
    public R<Void> assignRoles(@RequestBody @Validated UserRoleAssignDTO assignDTO) {
        userService.assignRoles(assignDTO);
        return R.ok();
    }

    @GetMapping("/getRolesByUserId")
    public R<List<RoleVO>> getRolesByUserId(@RequestParam @NotNull(message = "用户ID不能为空") @Min(value = 1, message = "用户ID必须大于0") Long userId) {
        return R.ok(userService.getRolesByUserId(userId));
    }
}
