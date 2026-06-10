package com.lreebom.springbootlearn.mapper;

import com.lreebom.springbootlearn.model.entity.Dept;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptMapper {
    Dept selectById(Long id);
}
