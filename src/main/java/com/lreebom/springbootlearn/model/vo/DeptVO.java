package com.lreebom.springbootlearn.model.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DeptVO {
    private Long id;
    private String deptName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private Long userCount; // 该部门下的用户数量
    private List<UserVO> userList; // 该部门下的用户列表
}
