package com.jk.controller;

import com.jk.entity.UserEntity;

import com.jk.service.UserServiceFeign;
import com.jk.utils.Constant;
import com.jk.utils.RedisUtil;
import com.jk.utils.StringUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {

    @Resource
    private UserServiceFeign userService;

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(String name) {
        return userService.hello(name);
    }

    @RequestMapping("/saveOrder")
    @ResponseBody
    @HystrixCommand(fallbackMethod = "saveOrderFail")
    public Object saveOrder(Integer userId, Integer productId, HttpServletRequest request) {
        return userService.saveOrder(userId, productId);
    }

    //注意，方法签名一定要要和api方法一致 自定义降级方法
    public Object saveOrderFail(Integer userId, Integer productId, HttpServletRequest request) {

        System.out.println("controller 保存订单降级方法");

        String sendValue  = redisUtil.get(Constant.SAVE_ORDER_WARNING_KEY).toString();

        String ipAddr = request.getRemoteAddr();

        //新启动一个线程进行业务逻辑处理
        // 开启一个独立线程，进行发送警报，给开发人员，处理问题
        new Thread( ()->{
            if(StringUtil.isNotEmpty(sendValue)) {
                System.out.println("紧急短信，用户下单失败，请离开查找原因,ip地址是="+ipAddr);

                //发送一个http请求，调用短信服务 TODO
                // 写发送短信代码，带有参数发送 userId  productId


                redisUtil.set(Constant.SAVE_ORDER_WARNING_KEY, "用户保存订单失败", 60);
            }else{
                System.out.println("已经发送过短信，1分钟内不重复发送");
            }
        }).start();

        // 反馈给用户看的
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", -1);
        map.put("message", "抢购排队人数过多，请您稍后重试。");

        return map;
    }

    @RequestMapping("/selectUserList")
    @ResponseBody
    public List<UserEntity> selectUserList(){

        List<UserEntity> userEntityList = (List<UserEntity>)redisUtil.get(Constant.SELECT_USER_LIST);

        if (userEntityList == null || userEntityList.size() < 0 || userEntityList.isEmpty()){
            userEntityList = userService.findUserList();
            redisUtil.set(Constant.SELECT_USER_LIST,userEntityList,60);
        }

        return userEntityList;
    }

    @RequestMapping("/deleteUserById")
    @ResponseBody
    public void deleteUserList(Integer id) {
       redisUtil.del(Constant.SELECT_USER_LIST);
        userService.deleteUserList(id);
    }
}
