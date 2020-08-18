package com.jk.service;

import com.jk.entity.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@RequestMapping("error")
@Component
public class UserServiceFallback implements UserServiceFeign{


    @Override
    public List<UserEntity> findUserList() {

        System.out.println("熔断处理，方法查询用户集合");

        return null;
    }

    @Override
    public void deleteUserList(Integer id) {
        System.out.println("熔断处理，方法删除用户信息");
    }

    @Override
    public String hello(String name) {

        System.out.println("进入了 hello 方法 熔断器");

        return "未连接到互联网 " +
                "请试试以下办法：" +
                " " +
                "·检查网线、调制解调器和路由器 " +
                "·重新连接到 Wi-Fi 网络 " +
                "·运行 Windows 网络诊断 " +
                "ERR_INTERNET_DISCONNECTED";
    }

    @Override
    public Object saveOrder(Integer userId, Integer productId) {

        System.out.println("进入保存订单，进行熔断处理");
        return null;
    }
}
