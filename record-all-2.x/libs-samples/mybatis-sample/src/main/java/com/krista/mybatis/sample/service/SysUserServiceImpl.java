package com.krista.mybatis.sample.service;

import com.krista.extend.utils.JsonUtil;
import com.krista.mybatis.sample.mapper.SysUserMapper;
import com.krista.mybatis.sample.model.SysUser;
import com.krista.mybatis.sample.multidatasource.MultiDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SysUserService
 *
 * @author krista
 * @version V1.0
 * @since 2019/4/7 10:15
 */
@Service
public class SysUserServiceImpl implements SysUserService{
    @Autowired
    private SysUserMapper sysUserMapper;

    @MultiDataSource("aaa")
    @Override
    public void selectAll() {
        List<SysUser> sysUserList = this.sysUserMapper.selectAll();
        System.out.println(JsonUtil.toJson(sysUserList));
    }
}
