package com.example.service;

import com.example.pojo.JobOption;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 获取员工职位人数
     */
    JobOption getEmpJobData();

    /**
     * 获取员工性别人数
     */
    List<Map<String, Object>> getEmpGenderData();
}
