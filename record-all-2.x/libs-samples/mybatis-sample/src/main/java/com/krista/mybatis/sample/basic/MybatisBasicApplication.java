package com.krista.mybatis.sample.basic;

import com.krista.extend.utils.JsonUtil;
import com.krista.mybatis.sample.mapper.SysUserMapper;
import com.krista.mybatis.sample.model.SysUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.mybatis.mapper.mapperhelper.MapperHelper;

import java.io.Reader;
import java.util.List;

/**
 * MybatisBasicApplication
 *
 * @author krista
 * @version V1.0
 * @since 2019/4/6 17:59
 */
public class MybatisBasicApplication {
    private static Logger logger = LoggerFactory.getLogger(MybatisBasicApplication.class);
    private static SqlSessionFactory sqlSessionFactory;

    private static void init() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        init();
        try(SqlSession sqlSession = sqlSessionFactory.openSession();){
//            List<SysUser> sysUsers = sqlSession.selectList("com.krista.mybatis.sample.mapper.SysUserMapper.selectAll");

            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);

            // 使用通用mapper
            MapperHelper mapperHelper = new MapperHelper();
            mapperHelper.registerMapper(SysUserMapper.class);;
            mapperHelper.processConfiguration(sqlSession.getConfiguration());

            List<SysUser> sysUsers = sysUserMapper.selectAll();

            System.out.println(JsonUtil.toJson(sysUsers));
        }
    }
}
