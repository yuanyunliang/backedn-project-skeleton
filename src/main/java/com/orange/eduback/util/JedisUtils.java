package com.orange.eduback.util;

import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;
import java.util.Set;

public enum JedisUtils {
    //实例
    INSTANCE;
    //得到jedisPool
    private static JedisPool jedisPool;

    //redis服务器地址
    @Value("${spring.data.redis.host}")
    private static String REDIS_HOST;

    //redis服务器端口
    @Value("${spring.data.redis.port}")
    private static int REDIS_PORT;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(10);//最大连接数
        config.setMaxIdle(5);//空闲连接数
        config.setMaxWaitMillis(2*1000);
        config.setTestOnBorrow(true);

        //创建一个jedis，连接redis服务器
        jedisPool = new JedisPool(config,"127.0.0.1",6379,3*1000);
    }

    //得到jedis对象
    public Jedis getJedis(){
        return jedisPool.getResource();
    }

    //关闭jedis对象
    public void closeJedis(Jedis jedis){
        if (jedis != null){
            jedis.close();
        }
    }
    //获取某个键的值
    public String get(String key){
        Jedis jedis = getJedis();
        String value = jedis.get(key);
        closeJedis(jedis);
        return value;
    }
    //设置某个键的值
    public void set(String key,String value){
        Jedis jedis = getJedis();
        jedis.set(key,value);
        closeJedis(jedis);
    }
    //拿到所有键的集合
    public Set<String> keys(String key){
        Jedis jedis = getJedis();
        Set<String> keys = jedis.keys(key);
        closeJedis(jedis);
        return keys;

    }
    //删除某个键
    public void del(String key){
        Jedis jedis = getJedis();
        jedis.del(key);
        closeJedis(jedis);
    }
    //设置某个键的过期时间
    public void expire(String key,int seconds){
        Jedis jedis = getJedis();
        jedis.expire(key,seconds);
        closeJedis(jedis);
    }
    //判断某个键是否存在
    public boolean exists(String key){
        Jedis jedis = getJedis();
        boolean exists = jedis.exists(key);
        closeJedis(jedis);
        return exists;
    }
    //设置某个键的值，并设置过期时间
    public void setex(String key,String value,int seconds){
        Jedis jedis = getJedis();
        jedis.setex(key,seconds,value);
        closeJedis(jedis);
    }
    //设置某个键的值，如果键不存在
    public void setnx(String key,String value){
        Jedis jedis = getJedis();
        jedis.setnx(key,value);
        closeJedis(jedis);
    }
    //设置某个键的值，如果键不存在，并设置过期时间
    public void setnxexifno(String key,String value,int seconds){
        Jedis jedis = getJedis();
        jedis.setnx(key,value);
        jedis.expire(key,seconds);
        closeJedis(jedis);
    }
    //设置某个键的值，如果键不存在，并设置过期时间,如果键存在，重新设置过期时间
    public void setnxex(String key,String value,int seconds){
        Jedis jedis = getJedis();
        if(jedis.exists(key)){
            jedis.expire(key,seconds);
        }else {
            jedis.setnx(key,value);
            jedis.expire(key,seconds);
        }
        closeJedis(jedis);
    }

    //设置hash类型的键值对
    public void hset(String key, Map<String,String> map){
        Jedis jedis = getJedis();
        jedis.hset(key,map);
        closeJedis(jedis);
    }
    //获取hash类型的键值对
    public Map<String,String> hget(String key){
        Jedis jedis = getJedis();
        Map<String,String> map = jedis.hgetAll(key);
        closeJedis(jedis);
        return map;
    }
    //删除hash类型的键值对
    public void hdel(String key,String field){
        Jedis jedis = getJedis();
        jedis.hdel(key,field);
        closeJedis(jedis);
    }
    //判断hash类型的键值对是否存在
    public boolean hexists(String key,String field){
        Jedis jedis = getJedis();
        boolean exists = jedis.hexists(key,field);
        closeJedis(jedis);
        return exists;
    }
    //设置hash类型的键值对，并设置过期时间
    public void hsetex(String key,Map<String,String> map,int seconds){
        Jedis jedis = getJedis();
        jedis.hset(key,map);
        jedis.expire(key,seconds);
        closeJedis(jedis);
    }

}

