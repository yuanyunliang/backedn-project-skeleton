package com.orange.eduback.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.*;

public class KeyGenerator {
    public static void main(String[] args) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
        //System.out.println(base64Key);
        testJedisUtilsHashMap();
       // List<String> list = new ArrayList<>(List.of("ca","ab","cb","bc","ba"));
        //list.removeIf(s->s.contains("a"));
        //System.out.println(list);
       // System.out.println(MailUtils.generateMailCode());
    }

    private static void testJedisUtils() {
        JedisUtils jedisUtils = JedisUtils.INSTANCE;
        jedisUtils.set("test", "test");
        jedisUtils.set("test1", "test1");
        System.out.println(jedisUtils.get("test"));
        jedisUtils.keys("test*").forEach(System.out::println);
    }
    private static void testJedisUtilsHashMap() {
        JedisUtils jedisUtils = JedisUtils.INSTANCE;
        Map<String,String> map = new HashMap<>();
        map.put("test5","tes55");
        map.put("test6","test66");
        if(jedisUtils.exists("test")){
            jedisUtils.del("test");
        }
        jedisUtils.hset("test", map);
        System.out.println(jedisUtils.hget("test"));

    }
}