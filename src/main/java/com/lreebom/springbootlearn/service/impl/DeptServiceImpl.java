package com.lreebom.springbootlearn.service.impl;

import com.lreebom.springbootlearn.common.BusinessException;
import com.lreebom.springbootlearn.mapper.DeptMapper;
import com.lreebom.springbootlearn.mapper.UserMapper;
import com.lreebom.springbootlearn.model.dto.DeptCreateDTO;
import com.lreebom.springbootlearn.model.entity.Dept;
import com.lreebom.springbootlearn.model.enums.UserStatusEnum;
import com.lreebom.springbootlearn.model.vo.DeptVO;
import com.lreebom.springbootlearn.model.vo.UserVO;
import com.lreebom.springbootlearn.service.DeptService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DeptServiceImpl implements DeptService {

    private final DeptMapper deptMapper;

    private final UserMapper userMapper;

    public DeptServiceImpl(DeptMapper deptMapper, UserMapper userMapper) {
        this.deptMapper = deptMapper;
        this.userMapper = userMapper;
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

    @Override
    public Long create(DeptCreateDTO createDTO) {
        Dept existDept = deptMapper.selectByDeptName(createDTO.getDeptName());
        if (existDept != null) {
            throw new BusinessException("部门已存在");
        }

        Dept dept = new Dept();
        dept.setDeptName(createDTO.getDeptName());
        int rows = deptMapper.insert(dept);
        if (rows != 1) {
            throw new BusinessException("部门创建失败");
        }
        return dept.getId();
    }

    @Override
    public List<DeptVO> list() {
        List<DeptVO> deptList = deptMapper.selectList();
        if (deptList.isEmpty()) {
            return deptList;
        }

        List<Long> deptIds = deptList.stream().map(DeptVO::getId).toList();

        List<UserVO> userList = userMapper.selectByDeptIds(deptIds);

        userList.forEach(user -> user.setStatusName(UserStatusEnum.getNameByCode(user.getStatus())));

        Map<Long, List<UserVO>> userMap = userList.stream().collect(Collectors.groupingBy(UserVO::getDeptId));

        deptList.forEach(dept -> {
            List<UserVO> deptUsers = userMap.getOrDefault(dept.getId(), List.of());
            dept.setUserList(deptUsers);
            dept.setUserCount(deptUsers == null ? 0L : deptUsers.size());

        });
        return deptList;
    }
}
