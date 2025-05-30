package com.example.controller;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParm;
import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 员工删除
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("员工删除,员工id: {}", ids);
        empService.delete(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据id查询员工信息: {}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("员工修改,员工信息: {}", emp);
        empService.update(emp);
        return Result.success();
    }
}
