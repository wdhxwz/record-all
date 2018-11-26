package com.krista.spring.boot.starter.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * MyMapper
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @date 2018/11/26 17:11
 */
public interface MyMapper<T> extends Mapper<T> ,MySqlMapper<T> {

}
