package com.jk.service;

import com.jk.entity.UserEntity;
import com.jk.mapper.UserMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    @RequestMapping("/findUserList")
    public List<UserEntity> findUserList() {
        return userMapper.selectUserList();
    }

    @RequestMapping("/deleteUserById")
    public void deleteUserList(Integer id) {
        userMapper.deleteUserList(id);
    }

    @Override
    @RequestMapping("/hello")
    public String hello(@RequestParam String name) {
        return "hi LinDa ,what is are you doing now , you know I am your best like man ,my name is "+name;
    }

    @Override
    @RequestMapping("/saveOrder")
    public Object saveOrder(@RequestParam Integer userId, @RequestParam Integer productId) {
        Map<String, Object> orderMap = new HashMap<String, Object>();
        orderMap.put("orderId", 11111);
        orderMap.put("userId", userId);
        orderMap.put("productId", productId);
        orderMap.put("orderNo", "20200815103622123");
        orderMap.put("orderName", "一架辽宁舰");
        orderMap.put("orderPrice", 100000);
        orderMap.put("createTime", new Date());
        return orderMap;
    }


    @RequestMapping("addUserInfo")
    public UserEntity addUserInfo(UserEntity userEntity) {
        UserEntity userInfo = userMapper.addUserInfo(userEntity);
        return userInfo;

    }
}
