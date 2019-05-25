package com.krista.dubbo.user.center.service;

import com.krista.dubbo.user.center.api.UserService;
import com.krista.dubbo.user.center.api.bean.UserVo;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl
 *
 * @author krista
 * @version V1.0
 * @since 2019/5/24 23:02
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Override
    public UserVo getUser(Long id) {
        return new UserVo();
    }
}
