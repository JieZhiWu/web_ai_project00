package com.example.service;

import com.example.pop.Emp;
import com.example.pop.EmpQueryParm;
import com.example.pop.PageResult;

import java.time.LocalDate;

public interface EmpService {

    /**
     * 分页查询
     */
    PageResult<Emp> page(EmpQueryParm empQueryParm);

    /**
     * 保存员工信息
     */
    void save(Emp emp);
    /**
     * 分页查询
     * @param page 页码
     * @param pageSize 每页条数
     * @return
     */
//    PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);
}
