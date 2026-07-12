package com.lreebom.springbootlearn.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserPageQueryDTO {

    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码必须大于0")
    private Integer pageNum = 1;

    @NotNull(message = "每页条数不能为空")
    @Min(value = 1, message = "每页条数必须大于0")
    @Max(value = 1000, message = "每页条数不能超过1000")
    private Integer pageSize = 10;

    private String username;
    private String email;
    private Integer status;

    @Min(value = 1, message = "部门ID必须大于0")
    private Long deptId;
}
