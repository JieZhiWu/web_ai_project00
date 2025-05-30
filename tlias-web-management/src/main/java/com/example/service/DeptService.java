package com.example.service;

import com.example.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询所有部门信息
     */
    List<Dept> findAll();

    /**
     * 根据id删除部门信息
     */
    void deleteById(Integer id);

    /**
     * 添加部门信息
     */
    void add(Dept dept);

    /**
     * 根据id查询部门信息
     */
    Dept findById(Integer id);

    void update(Dept dept);
}
