package com.lreebom.springbootlearn.controller;

import com.lreebom.springbootlearn.common.R;
import com.lreebom.springbootlearn.model.vo.DeptVO;
import com.lreebom.springbootlearn.service.DeptService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/depts")
public class DeptController {

    private final DeptService deptService;

    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }

    @GetMapping("/getById")
    public R<DeptVO> getById(@RequestParam
                             @NotNull(message = "部门ID不能为空")
                             @Min(value = 1, message = "部门ID必须大于0")
                             Long id) {
        return R.ok(deptService.getById(id));
    }
}
