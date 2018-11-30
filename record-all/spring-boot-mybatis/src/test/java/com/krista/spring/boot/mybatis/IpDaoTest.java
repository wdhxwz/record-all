package com.krista.spring.boot.mybatis;

import com.krista.extend.utils.IpUtil;
import com.krista.extend.utils.JsonUtil;
import com.krista.spring.boot.mybatis.dao.IpDao;
import com.krista.spring.boot.mybatis.model.Ip;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

/**
 * IpDaoTest
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @date 2018/11/30 15:21
 */
public class IpDaoTest extends BaseTest {
    private static String key = "ZSet:Ip";

    @Autowired
    private IpDao ipDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // 如果用redis，可以使用sortedset，有序集合，将ip转为10进制，作为set的分数，地区作为set的值，这个每个地区会有两条记录，
    // from_ip对应的地区值为* + 地区值；
    // 判断逻辑就是：将给定ip先转成10进制,然后从集合中找出第一个比这个数大的分数，如果对应的值是*开头的，则ip地址没有归属地，否则，对应的值就是归属地

    @Test
    public void findByIdTest() {
        BoundZSetOperations<String, String> boundZSetOps = redisTemplate.boundZSetOps(key);
        // Integer total = ipDao.selectCount(null);
//        Example example = new Example(Ip.class);
//        example = example.selectProperties("province");
//        example.setDistinct(true);
        List<Ip> ips = ipDao.findDistinct();
        System.out.println("total:" + ips.size());
        ips.forEach(ip -> {
            String fromIp = ip.getFromIp();
            String toIp = ip.getToIp();
            String address = ip.getCountry() + ip.getProvince() + ip.getCity() + ip.getCounty();
            boundZSetOps.add("*" + address, IpUtil.ipToLong(fromIp));
            boundZSetOps.add(address, IpUtil.ipToLong(toIp));
        });

//        List<String> provinceList = ips.stream().map(Ip::getProvince).collect(Collectors.toList());
//        provinceList.forEach(province -> {
//            Example example2 = new Example(Ip.class);
//            example2.createCriteria().andEqualTo("province", province);
//            List<Ip> list = ipDao.selectByExample(example2);
//            System.out.println(province + ",total:" + list.size());
//            list.forEach(ip -> {
//                String fromIp = ip.getFromIp();
//                String toIp = ip.getToIp();
//                String address = ip.getCountry() + ip.getProvince() + ip.getCity() + ip.getCounty() + ip.getRange();
//                boundZSetOps.add("*" + address, IpUtil.ipToLong(fromIp));
//                boundZSetOps.add(address, IpUtil.ipToLong(toIp));
//            });
//        });

//        Ip ip = ipDao.findById(1L);
//        System.out.println("fromIp:" + ip.getFromIp() + "," + IpUtil.ipToLong(ip.getFromIp()));
//        System.out.println("toIp:" + ip.getToIp() + "," + IpUtil.ipToLong(ip.getToIp()));
//        System.out.println(JsonUtil.toJson(ip));
        System.out.println("done");
    }
}
