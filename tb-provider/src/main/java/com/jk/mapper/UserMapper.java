package com.jk.mapper;

import com.jk.entity.UserEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select(" select * from sys_user ")
    List<UserEntity> selectUserList();

    @Delete(" delete from sys_user where id = #{value} ")
    void deleteUserList(Integer id);
}
