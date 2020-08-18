package com.jk.mapper;

import com.jk.entity.UserEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select(" SELECT su.*, ty.name as typeName from sys_user su LEFT JOIN t_type ty ON su.type_id = ty.id ")
    List<UserEntity> selectUserList();



    @Delete(" delete from sys_user where id = #{value} ")
    void deleteUserList(Integer id);

    @Insert(" INSERT into sys_user (account,name,password,sex,birthday,type_id) VALUES (#{account},#{name},#{password},#{sex},#{birthday},1) ")
    UserEntity addUserInfo(UserEntity userEntity);
}
