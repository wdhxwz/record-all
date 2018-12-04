package com.krista.springframework.v4x.aop.test.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * UserService
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @date 2018/11/29 10:04
 */
@Service
public class UserService {
    @CacheEvict(value={"mycache", "mycache2"}, allEntries = true)
    public void clearCache(){

    }

    @CachePut(value = "mycache", key = "#user.id")
    public User save(User user) {
        return user;
    }
    @CacheEvict(value = "mycache", key = "#user.id") //移除指定key的数据
    public User delete(User user) {
        return user;
    }
    @Cacheable(cacheNames = "mycache", key = "#id")
    public User findById(final Long id) {
        System.out.println("cache miss, invoke find by id, id:" + id);
        Random random = new Random();
        User user = new User(id,
                "wangd_"+random.nextInt(10000)+"_"+System.currentTimeMillis(),
                "wd@123.com"+random.nextInt(10000)+"_"+System.currentTimeMillis());
        return user;
    }

    @Cacheable(value="mycache2", key = "#username.concat(#email)", condition = "#username eq 'wangd'")
    public User findByUsernameAndEmail(String username, String email){
        Random random = new Random();
        System.out.println("cache2 miss, invoke find by name and email, name:" + username +
                ", email:"+email);
        User user = new User(System.currentTimeMillis()+random.nextInt(10000),
                username,
                email);
        return user;
    }
    @Cacheable(value = "mycache2", key = "#username")
    public User findByUsername(String username){
        Random random = new Random();
        System.out.println("cache miss, invoke find by name, name:" + username);
        User user = new User(System.currentTimeMillis()+random.nextInt(10000),
                username,
                "mytestemail@123.com_"+System.currentTimeMillis());
        return user;
    }
}
