package com.example.controller;


import com.example.pojo.Clazz;
import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.service.ClazzService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 班级管理Controller
 */
@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    /**
     * 条件分页查询班级
     */
    @GetMapping
    public Result page(String name,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        PageResult pageResult = clazzService.page(name, begin, end, page, pageSize);
        return Result.success(pageResult);
    }

    /**
     * 查询全部班级信息
     */
    @GetMapping("/list")
    public Result findAll() {
        List<Clazz> clazzList = clazzService.findAll();
        return Result.success(clazzList);
    }

    /**
     * 添加班级信息
     */
    @PostMapping
    public Result add(@RequestBody Clazz clazz) {
        log.info("添加班级信息: {}", clazz);
        clazzService.add(clazz);
        return Result.success();
    }

    /**
     * 根据 id查询班级信息
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据id查询班级信息: {}", id);
        Clazz clazz = clazzService.findInfo(id);
        return Result.success(clazz);
    }

    /**
     * 修改班级信息
     */
    @PutMapping
    public Result update(@RequestBody Clazz clazz) {
        log.info("修改班级信息: {}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    /**
     * 删除班级信息
     */
    @DeleteMapping( "/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除班级信息: {}", id);
        clazzService.delete(id);
        return Result.success();
    }

}
