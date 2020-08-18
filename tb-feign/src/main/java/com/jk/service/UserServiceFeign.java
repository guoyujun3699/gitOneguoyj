package com.jk.service;

import com.jk.entity.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "tb-provider-two",fallback = UserServiceFallback.class)
public interface UserServiceFeign extends UserService{

    /*@RequestMapping("/selectUserList")
    List<UserEntity> selectUserList();

    @RequestMapping("/deleteUserById")
    void deleteUserList(@RequestParam Integer id);*/


}
