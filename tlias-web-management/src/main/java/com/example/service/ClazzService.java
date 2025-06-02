package com.example.service;

import com.example.pojo.Clazz;
import com.example.pojo.PageResult;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

public interface ClazzService {
    /**
     * 班级分页查询
     */
    PageResult page(String name, LocalDate begin, LocalDate end, Integer page, Integer pageSize);


    /**
     * 获取所有班级信息
     */
    List<Clazz> findAll();

    /**
     * 添加班级信息
     */
    void add(Clazz clazz);

    /**
     * 根据id查询班级信息
     */
    Clazz findInfo(Integer id);

    /**
     * 修改班级信息
     */
    void update(Clazz clazz);


    /**
     * 删除班级信息
     */
    void delete(Integer id);
}
