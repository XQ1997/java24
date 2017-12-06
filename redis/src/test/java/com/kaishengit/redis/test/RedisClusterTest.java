package com.kaishengit.redis.test;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class RedisClusterTest {

    @Test
    public void save() throws IOException {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(10);
        config.setMinIdle(5);

        Set<HostAndPort> hostSets = new HashSet<>();
        hostSets.add(new HostAndPort("192.168.135.30",6379));
        hostSets.add(new HostAndPort("192.168.135.40",6379));
        hostSets.add(new HostAndPort("192.168.135.41",6379));
        hostSets.add(new HostAndPort("192.168.135.50",6379));
        hostSets.add(new HostAndPort("192.168.135.60",6379));
        hostSets.add(new HostAndPort("192.168.135.31",6379));

        JedisCluster jedisCluster = new JedisCluster(hostSets,config);
        jedisCluster.set("foo","bar");
        jedisCluster.close();

    }

}
