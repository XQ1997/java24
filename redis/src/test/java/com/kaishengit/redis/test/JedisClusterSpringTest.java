package com.kaishengit.redis.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class JedisClusterSpringTest {

    @Autowired
    private JedisCluster jedisCluster;

    @Test
    public void getFoo() throws IOException {
        String result = jedisCluster.get("foo");
        System.out.println(result);
        jedisCluster.close();
    }

}
