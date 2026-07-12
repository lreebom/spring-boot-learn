package com.lreebom.springbootlearn.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserUpdateDTO {
    @NotNull(message = "用户ID不能为空")
    @Min(value = 1, message = "用户ID必须大于0")
    private Long id;

    private String username;

    @Email(message = "邮箱格式不正确")
    private String email;

    private Integer status;

    @Min(value = 1, message = "部门ID必须大于0")
    private Long deptId;
}
