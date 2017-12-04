package com.kaishengit.redis.test;

import com.kaishengit.pojo.User;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class ProtostuffTest {

    @Autowired
    private JedisPool jedisPool;

    @Test
    public void setUser() {
        User user = new User(1011,"杰克逊","123123","美国");

        Jedis jedis = jedisPool.getResource();

        //将对象序列化为字节数组
        Schema<User> userSchema = RuntimeSchema.getSchema(User.class);
        byte[] bytes = ProtostuffIOUtil.toByteArray(user,userSchema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));

        jedis.set("user:1011".getBytes(),bytes);

        jedis.close();

    }

    @Test
    public void getUser() {
        Jedis jedis = jedisPool.getResource();

        byte[] bytes = jedis.get("user:1011".getBytes());

        Schema<User> userSchema = RuntimeSchema.getSchema(User.class);
        User user = new User();
        ProtostuffIOUtil.mergeFrom(bytes,user,userSchema);

        System.out.println(user);
        jedis.close();
    }
}
