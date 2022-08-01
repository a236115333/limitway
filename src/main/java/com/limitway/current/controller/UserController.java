package com.limitway.current.controller;


import com.limitway.current.base.UserInfo;
import com.limitway.current.response.BaseResponse;
import com.limitway.current.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user",method = RequestMethod.POST)
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/queryUser")
    public UserInfo selectUser(Integer id){

        return userService.selectUser(id);
    }


    @RequestMapping("/addUser")
    public BaseResponse addUser(UserInfo userInfo){

        BaseResponse flag = userService.addUser(userInfo);
        return flag;
    }
}
