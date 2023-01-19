package cn.kgc.controller;

import cn.kgc.entities.Emp;
import cn.kgc.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TODO
 *
 * @auth dacai
 * @date 2020/7/10 21:36
 */
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;

    @PostMapping("/emp")
    public String addEmp(Emp emp){
        empService.add(emp);
        return "add ok";
    }

    @GetMapping("/emp/{id}")
    public Object getEmpById(@PathVariable("id") Integer id){
        ExecutorService es = Executors.newFixedThreadPool(200);
        for(int i=0 ;i<500;i++){
            es.submit(new Runnable() {
                @Override
                public void run() {
                    empService.getEmpById(id);
                }
            });
        }
        return empService.getEmpById(id);
    }
}
