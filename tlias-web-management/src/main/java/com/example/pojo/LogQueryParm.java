package com.example.pojo;

import lombok.Data;

@Data
public class LogQueryParm {
    private Integer page = 1; //当前页码
    private Integer pageSize = 10; //每页显示条数
    private Integer id; //日志id
    private String operateEmpId; //操作员工号
    private String operateTime; //操作时间
    private String className; //类名
    private String methodName; //方法名
    private String methodParams; //方法参数
    private String returnValue; //返回值
    private Long costTime; //耗时

    private String operateEmpName; //操作员工姓名
}
