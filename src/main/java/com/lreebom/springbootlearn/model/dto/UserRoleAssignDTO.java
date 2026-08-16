package com.lreebom.springbootlearn.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class UserRoleAssignDTO {

    // 需要重新分配角色的用户。
    @NotNull(message = "用户ID不能为空")
    @Min(value = 1, message = "用户ID必须大于0")
    private Long userId;

    // 目标角色集合；传空数组表示清空该用户的全部角色。
    @NotNull(message = "角色ID列表不能为空")
    @Size(max = 20, message = "单个用户最多分配20个角色")
    private List<@NotNull(message = "角色ID不能为空") @Min(value = 1, message = "角色ID必须大于0") Long> roleIds;
}
