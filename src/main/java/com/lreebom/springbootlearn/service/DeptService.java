package com.lreebom.springbootlearn.service;

import com.lreebom.springbootlearn.model.dto.DeptCreateDTO;
import com.lreebom.springbootlearn.model.vo.DeptVO;

import java.util.List;

public interface DeptService {
    DeptVO getById(Long id);

    Long create(DeptCreateDTO createDTO);

    List<DeptVO> list();
}
