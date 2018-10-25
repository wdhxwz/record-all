package com.krista.springboot.v1x.test.controller;

import com.krista.springboot.v1x.test.dao.UserRepository;
import com.krista.springboot.v1x.test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 *
 * @author dw_wangdonghong
 * @date 2018/10/25 19:25
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id){
        User findOne = this.userRepository.findOne(id);

        return findOne;
    }
}
