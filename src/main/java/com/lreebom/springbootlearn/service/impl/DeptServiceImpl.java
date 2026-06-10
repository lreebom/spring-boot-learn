package com.lreebom.springbootlearn.service.impl;

import com.lreebom.springbootlearn.common.BusinessException;
import com.lreebom.springbootlearn.mapper.DeptMapper;
import com.lreebom.springbootlearn.model.entity.Dept;
import com.lreebom.springbootlearn.model.vo.DeptVO;
import com.lreebom.springbootlearn.service.DeptService;
import org.springframework.stereotype.Service;

@Service
public class DeptServiceImpl implements DeptService {

    private final DeptMapper deptMapper;

    public DeptServiceImpl(DeptMapper deptMapper) {
        this.deptMapper = deptMapper;
    }

    private static DeptVO convertToVO(Dept dept) {
        DeptVO deptVo = new DeptVO();
        deptVo.setId(dept.getId());
        deptVo.setDeptName(dept.getDeptName());
        deptVo.setCreateTime(dept.getCreateTime());
        deptVo.setUpdateTime(dept.getUpdateTime());
        return deptVo;
    }

    @Override
    public DeptVO getById(Long id) {
        Dept dept = deptMapper.selectById(id);
        if (dept == null) {
            throw new BusinessException("部门不存在");
        }
        return convertToVO(dept);
    }
}
