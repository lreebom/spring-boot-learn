package com.lreebom.springbootlearn.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeptVO {
    private Long id;
    private String deptName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
