package com.example.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class EmpQueryParm {
    private Integer page = 1; //当前页码
    private Integer pageSize = 10; //每页显示条数
    private String name; //姓名
    private Integer gender; //性别
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin; //入职时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end; //离职时间
}
