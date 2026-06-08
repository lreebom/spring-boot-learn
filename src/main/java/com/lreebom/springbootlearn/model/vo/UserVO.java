package com.lreebom.springbootlearn.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserVO {
    private Long id;
    private String username;
    private String email;
    private Integer status;
    private String statusName;
    private LocalDateTime createTime;
}
