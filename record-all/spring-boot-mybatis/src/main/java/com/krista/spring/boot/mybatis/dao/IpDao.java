package com.krista.spring.boot.mybatis.dao;

import com.krista.spring.boot.mybatis.model.Ip;
import com.krista.spring.boot.starter.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * IpDao
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @date 2018/11/26 16:26
 */
@Mapper
public interface IpDao extends MyMapper<Ip> {
    Ip findById(Long id);
}
