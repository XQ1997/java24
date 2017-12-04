package com.kaishengit.redis.test;

import com.alibaba.fastjson.JSON;
import com.kaishengit.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class SpringJedisTest {

    @Autowired
    private JedisPool jedisPool;

    @Test
    public void setName() {
        Jedis jedis = jedisPool.getResource();
        jedis.set("user:2:name","Jerry");
        jedis.close();
    }

    @Test
    public void setUserToRedis() {
        User user = new User(10009,"王老五","8877665","洛杉矶");

        Jedis jedis = jedisPool.getResource();

        Map<String,String> map = new HashMap<>();
        map.put("id",user.getId().toString());
        map.put("userName",user.getUserName());
        map.put("password",user.getPassword());
        map.put("address",user.getAddress());

        jedis.hmset("user:10009",map);

        jedis.close();

    }

    @Test
    public void getUserFromRedis() {
        Jedis jedis = jedisPool.getResource();
        Map<String,String> result = jedis.hgetAll("user:10009");

        User user = new User();
        user.setUserName(result.get("userName"));
        user.setAddress(result.get("address"));
        user.setPassword(result.get("password"));
        user.setId(Integer.valueOf(result.get("id")));

        System.out.println(user);

        jedis.close();
    }

    @Test
    public void saveUser() {
        Jedis jedis = jedisPool.getResource();
        User user = new User(1009,"李司棋","123123","北京");
        jedis.set("user:1009", JSON.toJSONString(user));
        jedis.close();
    }

    @Test
    public void getUser() {
        Jedis jedis = jedisPool.getResource();
        User user = JSON.parseObject(jedis.get("user:1009"),User.class);
        System.out.println(user);
        jedis.close();
    }





}
