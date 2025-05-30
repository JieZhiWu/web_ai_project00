package com.example.service;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParm;
import com.example.pojo.PageResult;

import java.util.List;

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
     * 删除员工信息
     */
    void delete(List<Integer> ids);

    /**
     * 返回员工信息
     */
    Emp getInfo(Integer id);

    /**
     * 更新员工信息
     */
    void update(Emp emp);
    /**
     * 分页查询
     * @param page 页码
     * @param pageSize 每页条数
     * @return
     */
//    PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);
}
