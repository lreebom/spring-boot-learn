package com.lreebom.springbootlearn.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoleCreateDTO {

    @NotBlank(message = "角色编码不能为空")
    @Size(max = 64, message = "角色编码长度不能超过64个字符")
    @Pattern(
            regexp = "^[A-Z][A-Z0-9_]*$",
            message = "角色编码只能包含大写字母、数字和下划线，且必须以大写字母开头"
    )
    private String roleCode;

    // 页面展示的角色名称，例如 系统管理员。
    @NotBlank(message = "角色名称不能为空")
    @Size(max = 64, message = "角色名称长度不能超过64")
    private String roleName;
}
