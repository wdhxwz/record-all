package com.krista.dubbo.user.center.api;

import com.krista.dubbo.user.center.api.bean.UserVo;

/**
 * UserService
 *
 * @author krista
 * @version V1.0
 * @since 2019/5/24 22:55
 */
public interface UserService {
    /**
     * 根据Id获取用户信息
     */
    UserVo getUser(Long id);
}
