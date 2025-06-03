package com.example.service;

import com.example.pojo.PageResult;
import com.example.pojo.Student;

import java.time.LocalDate;

public interface StudentService {
    /**
     * 分页查询
     */
    PageResult page(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize);

     /**
     * 查询全部班级
     */
    Object findAll();

    /**
     * 添加
     */
    void save(Student student);

     /**
     * 根据ID 查询
     */
    Student findById(Integer id);

     /**
     * 修改
     */
    void update(Student student);

     /**
     * 删除
     */
    void delete(Integer id);

    /**
     * 扣分处理
     */
    void violationHandle(Integer id, Integer score);
}
