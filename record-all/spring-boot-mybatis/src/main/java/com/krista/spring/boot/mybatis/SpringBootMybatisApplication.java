package com.krista.spring.boot.mybatis;

import com.krista.spring.boot.base.MyMapper;
import com.krista.spring.boot.mybatis.dao.IpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * SpringBootMybatisApplication
 * <p>
 * https://blog.csdn.net/Dongguabai/article/details/80729563
 * tk.mybatis.spring.annotation.MapperScan 而不是 org.mybatis.spring.annotation.MapperScan
 * @author dw_wangdonghong
 * @version V1.0
 * @date 2018/11/26 16:25
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.krista.spring.boot.mybatis.dao"}, markerInterface = MyMapper.class)
public class SpringBootMybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisApplication.class, args);
    }

    @Autowired
    private IpDao ipDao;

    @Bean
    CommandLineRunner sampleCommandLineRunner() {
        System.out.println(this.ipDao.selectByPrimaryKey(22L));
        return (args) -> System.out.println(this.ipDao.findById(11L));
    }
}
