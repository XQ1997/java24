package com.kaishengit.redis.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class SpringDataRedisTest {

    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.redisTemplate.setKeySerializer(new StringRedisSerializer());
        this.redisTemplate.setValueSerializer(new StringRedisSerializer());
    }

    @Test
    public void setValue() {
        redisTemplate.opsForValue().set("dataName","Spring");
    }
    @Test
    public void getValue() {
        String value = redisTemplate.opsForValue().get("dataName");
        System.out.println(value);
    }

    @Test
    public void setList() {
        redisTemplate.opsForList().rightPushAll("data-list","aa","bb","cc");
    }


}
