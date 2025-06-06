package com.example.service.impl;

import com.example.mapper.EmpMapper;
import com.example.mapper.LogMapper;
import com.example.mapper.StudentMapper;
import com.example.pojo.*;
import com.example.service.ReportService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private LogMapper logMapper;

    @Override
    public JobOption getEmpJobData() {
        List<Map<String, Object>> list = empMapper.countEmpJobData();
        /*
         ! ! ! AI
         List<String> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        * */
        List<String> jobList = list.stream().map(dataMap -> dataMap.get("pos").toString()).toList();
        List<Integer> dataList = list.stream().map(dataMap -> Integer.parseInt(dataMap.get("num").toString())).toList();
        /*
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();
        */
        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public List<Map> getStudentDegreeData() {
        return studentMapper.getStudentDegreeData();
    }


    @Override
    public ClazzCountOption getStudentCountData() {
        List<Map<String, Object>> countList = studentMapper.getStudentCount();
        if(!CollectionUtils.isEmpty(countList)){
            List<Object> clazzList = countList.stream().map(map -> {
                return map.get("cname");
            }).toList();

            List<Object> dataList = countList.stream().map(map -> {
                return map.get("scount");
            }).toList();

            return new ClazzCountOption(clazzList, dataList);
        }
        return null;
    }

    @Override
    public PageResult<OperateLog> page(LogQueryParm logQueryParm) {
        PageHelper.startPage(logQueryParm.getPage(), logQueryParm.getPageSize());

        List<OperateLog> logList = empMapper.logList(logQueryParm);

        Page<OperateLog> p = (Page<OperateLog>) logList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }


}
