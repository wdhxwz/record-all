package com.krista.springboot.v1x.test.dao;

import com.krista.springboot.v1x.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户Repository
 *
 * @author dw_wangdonghong
 * @date 2018/10/25 19:24
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
