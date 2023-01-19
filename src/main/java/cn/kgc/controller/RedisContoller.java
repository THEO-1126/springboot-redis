package cn.kgc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @auth dacai
 * @date 2020/7/10 21:07
 */
@RestController
public class RedisContoller {
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/redis/get/{key}")
    public Object get(@PathVariable("key") String key){
       return  redisTemplate.opsForValue().get(key);
    }

    @PostMapping("/redis/set/{key}/{value}")
    public Object set(@PathVariable("key") String key,
                      @PathVariable("value") String value){
        redisTemplate.opsForValue().set(key,value);
        return "set success";
    }
}
