package cn.kgc.service.impl;

import cn.kgc.entities.Emp;
import cn.kgc.mapper.EmpMapper;
import cn.kgc.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @auth dacai
 * @date 2020/7/10 21:34
 */
@Service
@Slf4j
public class EmpServiceImpl implements EmpService {
    @Autowired
    public RedisTemplate redisTemplate;

    @Resource
    private EmpMapper empMapper;

    @Override
    public void add(Emp emp) {
        empMapper.insert(emp);
    }

    @Override
    public  Object getEmpById(Integer id) {
        // 先从缓存获取数据，如果有则直接返回
        //                   如果无，则查询mysql,并将数据设置到缓存
        String key = "user:" + id;
        Object userObj = redisTemplate.opsForValue().get(key);
        if(userObj == null){
            synchronized (this.getClass()){
                userObj = redisTemplate.opsForValue().get(key);
                if(userObj == null ){
                    log.debug("----> 查询数据库..............");
                    // 查数据库
                    Emp emp = empMapper.selectByPrimaryKey(id);
                    redisTemplate.opsForValue().set(key,emp);
                    return emp;
                }else{
                    log.debug("----> 查询缓存(同步代码块)>>>>>>>>>>>>>>>>>");
                    return userObj;
                }
            }

        }else{
            log.debug("----> 查询缓存>>>>>>>>>>>>>>>>>");
        }
        return userObj;
    }
}
