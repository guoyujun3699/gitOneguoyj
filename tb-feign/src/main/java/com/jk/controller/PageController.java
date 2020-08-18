package com.jk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("page")
public class PageController {

    @RequestMapping("toUserList")
    public String toUserList(){
        return "userlist";
    }

    @RequestMapping("addUserPage")
    public String addUserPage(){
        return "";
    }

}
