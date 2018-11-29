package com.krista.spring.boot.mybatis.dao;

import com.krista.spring.boot.mybatis.model.SensitiveWord;
import com.krista.spring.boot.starter.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * SensitiveWordDao
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @date 2018/11/29 16:32
 */
@Mapper
public interface SensitiveWordDao extends MyMapper<SensitiveWord> {
}
