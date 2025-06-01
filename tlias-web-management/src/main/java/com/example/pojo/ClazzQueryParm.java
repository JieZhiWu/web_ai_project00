package com.example.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ClazzQueryParm {
    private Integer page = 1;  //当前页码
    private Integer pageSize = 10; //每页显示条数
    private String name; //班级名称
    private String  room; //教室
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin; // 开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end; // 结束时间


}
