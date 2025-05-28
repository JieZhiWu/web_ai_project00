package com.example.controller;

import com.example.pop.Dept;
import com.example.pop.Result;
import com.example.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

//    private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService  deptService;

//    public DeptController(DeptService deptService) {
//        this.deptService = deptService;
//    }

//    @RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping
    public Result  list() {
        System.out.println("查询全部部门数据");
        List<Dept> deptList= deptService.findAll();
        return Result.success(deptList);
    }


    /**
     * 根据id删除部门信息
     * 方式一：使用HttpServletRequest对象接收参数
     */
/*
    @DeleteMapping("/depts")
    public Result deleteById(HttpServletRequest request) {
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        System.out.println("根据id删除部门信息: " + id);
        return Result.success();
    }
*/

    /**
     * 根据id删除部门信息
     * 方式二：使用@RequestParam注解接收参数
     * 注意事项：一旦声明@RequestParam注解，该参数在请求中必须携带该参数,否则会报错
     */
/*
    @DeleteMapping("/depts")
    public Result deleteById(@RequestParam(value = "id",required = false) Integer id) {
        System.out.println("根据id删除部门信息: " + id);
        return Result.success();
    }
*/
    /**
     * 根据id删除部门信息
     * 方式三:省略@RequestParam注解，使用形参接收参数与前端参数名称一致
     */
    @DeleteMapping
    public Result deleteById(Integer id) {
        System.out.println("根据id删除部门信息: " + id);
        log.info("根据id删除部门信息: {}", id);
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 新增部门信息
     */
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        System.out.println("新增部门信息: " + dept);
        log.info("新增部门信息: {}", dept);
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根据id查询部门信息
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        System.out.println("根据id查询部门信息: " + id);
        log.info("根据id查询部门信息: {}", id);
        Dept dept = deptService.findById(id);
        return Result.success(dept);
    }

    /**
     * 修改部门信息
     */
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        System.out.println("修改部门信息: " + dept);
        log.info("修改部门信息: {}", dept);
        deptService.update(dept);
        return Result.success();
    }

}
