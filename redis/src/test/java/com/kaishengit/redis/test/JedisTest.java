package com.kaishengit.redis.test;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JedisTest {

    @Test
    public void setString() {
        Jedis jedis = new Jedis("192.168.135.30",6379);
        jedis.set("name-2","李思");
        jedis.close();
    }

    @Test
    public void getString() {
        Jedis jedis = new Jedis("192.168.135.30",6379);
        String name = jedis.get("name-2");
        System.out.println(name);
        jedis.close();
    }

    @Test
    public void setList() {
        Jedis jedis = new Jedis("192.168.135.30",6379);
        jedis.lpush("user:1:address","北京中关村","焦作万达广场");
        jedis.close();
    }

    @Test
    public void getList() {
        try(Jedis jedis = new Jedis("192.168.135.30",6379)) {
            List<String> result = jedis.lrange("user:1:address", 0, -1);
            for (String str : result) {
                System.out.println(str);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void pooled() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(10);
        config.setMinIdle(3);

        JedisPool jedisPool = new JedisPool(config,"192.168.135.30",6379);
        Jedis jedis = jedisPool.getResource();

        Map<String,String> data = new HashMap<String,String>();
        data.put("id","1");
        data.put("age","23");
        data.put("address","beijing");

        jedis.hmset("user:1",data);

        jedis.close();
        jedisPool.destroy();
    }

}
