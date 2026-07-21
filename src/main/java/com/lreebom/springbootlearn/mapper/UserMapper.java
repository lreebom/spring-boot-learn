package com.lreebom.springbootlearn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lreebom.springbootlearn.model.dto.UserPageQueryDTO;
import com.lreebom.springbootlearn.model.entity.User;
import com.lreebom.springbootlearn.model.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    UserVO selectDetailById(@Param("id") Long id);

    IPage<UserVO> selectPage(Page<UserVO> page, @Param("query") UserPageQueryDTO queryDTO);

}
