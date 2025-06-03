package com.example.service.impl;

import com.example.mapper.StudentMapper;
import com.example.pojo.PageResult;
import com.example.pojo.Student;
import com.example.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult page(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Student> studentList = studentMapper.list(name, degree, clazzId);
        Page<Student> p = (Page<Student>) studentList;
        return new PageResult(p.getTotal(), p.getResult());
    }

    @Override
    public Object findAll() {
        return studentMapper.findAll();
    }

    @Override
    public void save(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);
    }

    @Override
    public Student findById(Integer id) {
        return studentMapper.findById(id);
    }

    @Override
    public void update(Student student) {
        studentMapper.update(student);
    }

    @Override
    public void delete(Integer id) {
        studentMapper.delete(id);
    }

    @Override
    public void violationHandle(Integer id, Integer score) {
        studentMapper.updateViolation(id, score);
    }

}
