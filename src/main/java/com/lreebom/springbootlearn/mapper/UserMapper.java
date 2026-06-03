package com.lreebom.springbootlearn.mapper;

import com.lreebom.springbootlearn.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    User selectById(@Param("id") Long id);

    int insert(User user);

    User selectByUsername(@Param("username") String username);

    User selectByEmail(@Param("email") String email);

    List<User> selectPage(@Param("offset") int offset, @Param("limit") int limit);

    Long count();

    int update(User user);

    int deleteById(@Param("id") Long id);
}
