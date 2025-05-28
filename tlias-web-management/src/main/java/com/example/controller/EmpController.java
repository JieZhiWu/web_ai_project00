package com.example.controller;

import com.example.pop.Emp;
import com.example.pop.EmpQueryParm;
import com.example.pop.PageResult;
import com.example.pop.Result;
import com.example.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * 员工管理Controller
 */
@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * 分页查询
     *  page:当前页码
     *  pageSize:每页条数
     */
/*    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Integer gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("分页查询,参数: page={}, pageSize={}, name={}, gender={}, begin={}, end={}",  page, pageSize, name, gender, begin, end);
        PageResult<Emp> pageResult = empService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pageResult);
    }*/

    @GetMapping
    public Result page(EmpQueryParm empQueryParm) {
        log.info("分页查询,参数: {}", empQueryParm);
        PageResult<Emp> pageResult = empService.page(empQueryParm);
        return Result.success(pageResult);
    }

    /**
     * 新增员工
     */
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("新增员工,员工信息: {}", emp);
        empService.save(emp);
        return Result.success();
    }

}
