package com.limitway.current.service;


import com.github.pagehelper.PageInfo;
import com.limitway.current.base.UserInfo;
import com.limitway.current.mapper.UserInfoMapper;
import com.limitway.current.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {


    @Autowired
    public UserInfoMapper userInfoMapper;

    public UserInfo selectUser(Integer id){

        return  userInfoMapper.selectByPrimaryKey(id);
    }


    public BaseResponse addUser(UserInfo userInfo){
        BaseResponse   response = BaseResponse.success();
        userInfo.setCreateTime(new Date());
          int num = userInfoMapper.insert(userInfo);
          if(num > 0 ){
              response.setData(userInfo);
              return response;
          }
        response = BaseResponse.error();
                response.setData("操作失败！");
          return response;
    }
}
