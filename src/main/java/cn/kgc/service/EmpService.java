package cn.kgc.service;

import cn.kgc.entities.Emp;

/**
 * TODO
 *
 * @auth dacai
 * @date 2020/7/10 21:33
 */
public interface EmpService {
    public void add(Emp emp);
    public Object getEmpById(Integer id);
}
