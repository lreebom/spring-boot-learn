package com.lreebom.springbootlearn.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoleVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String roleCode;
    private String roleName;
    private Integer status;
    private LocalDateTime createTime;
}
