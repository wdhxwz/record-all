package com.krista.springboot.v1x.test.controller;

import com.krista.springboot.v1x.test.dao.UserRepository;
import com.krista.springboot.v1x.test.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * UserController
 *
 * @author dw_wangdonghong
 * @date 2018/10/25 19:25
 */
@RestController
@RequestMapping("/user")
@Api(tags = "user-service", description = "用户模块")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "根据用户id获取用户")
    @GetMapping("/{id}")
    public User findById(@PathVariable Long id){
        User findOne = this.userRepository.findOne(id);

        return findOne;
    }

    @ApiOperation(value = "新增用户")
    @PostMapping(value = "add")
    public Object addUser(@RequestBody User user){
        user = this.userRepository.save(user);

        return user.getId();
    }
}
