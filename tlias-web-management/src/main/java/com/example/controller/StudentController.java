package com.example.controller;

import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.pojo.Student;
import com.example.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.security.Provider;
import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 分页查询学生信息
     */
    @GetMapping
    public Result page(String name,
                       Integer degree,
                       Integer clazzId,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("分页查询: name={},degree={},clazzId={},page={},pageSize={}", name, degree, clazzId, page, pageSize);
        PageResult pageResult = studentService.page(name, degree, clazzId, page, pageSize);
        return Result.success(pageResult);
    }

    /**
     * 获取所有班级信息
     */
    @GetMapping("/list")
    public Result findAll() {
        log.info("获取所有班级信息");
        return Result.success(studentService.findAll());
    }

    /**
     * 添加学生信息
     */
    @PostMapping
    public Result save(@RequestBody Student student) {
        log.info("添加学生信息: {}", student);
        studentService.save(student);
        return Result.success();
    }

    /**
     * 根据ID查询学生信息
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        log.info("根据id查询学生信息: {}", id);
        Student student = studentService.findById(id);
        return Result.success(student);
    }

    /**
     * 修改学生信息
     */
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改学生信息: {}", student);
        studentService.update(student);
        return Result.success();
    }

    /**
     * 删除学生信息
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除学生信息: {}", id);
        studentService.delete(id);
        return Result.success();
    }

    /**
     * 违纪处理
     */
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id, @PathVariable Integer score) {
        studentService.violationHandle(id, score);
        return Result.success();
    }

}
