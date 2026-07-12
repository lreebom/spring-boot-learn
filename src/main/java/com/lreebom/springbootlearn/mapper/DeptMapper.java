package com.lreebom.springbootlearn.mapper;

import com.lreebom.springbootlearn.model.entity.Dept;
import com.lreebom.springbootlearn.model.vo.DeptVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptMapper {
    Dept selectById(Long id);

    Dept selectByDeptName(String deptName);

    int insert(Dept dept);

    List<DeptVO> selectList();
}
