package com.example.controller;

import com.example.pojo.*;
import com.example.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequestMapping("/log")
@RestController
public class LogController {

    @Autowired
    private ReportService reportService;

    @RequestMapping("/page")
    public Result page(LogQueryParm logQueryParm) {
        log.info("分页查询: logQueryParm={}", logQueryParm);
        PageResult<OperateLog> pageResult = reportService.page(logQueryParm);
        return Result.success(pageResult);
    }
}
