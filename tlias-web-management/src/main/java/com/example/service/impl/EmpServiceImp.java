package com.example.service.impl;


import com.example.mapper.EmpExprMapper;
import com.example.mapper.EmpMapper;
import com.example.pop.Emp;
import com.example.pop.EmpExpr;
import com.example.pop.EmpQueryParm;
import com.example.pop.PageResult;
import com.example.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImp implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);

        /**
         * 批量插入工作信息
         */
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)){
            //遍历集合,为每个元素添加员工id
            exprList.forEach(empExpr -> {empExpr.setEmpId(emp.getId());});
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public PageResult<Emp> page(EmpQueryParm empQueryParm) {
        PageHelper.startPage(empQueryParm.getPage(),empQueryParm.getPageSize());

        List<Emp> empList = empMapper.list(empQueryParm);

        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<>(p.getTotal(),p.getResult());
    }

//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        Long total = empMapper.count();
//
//        Integer start = (page-1)*pageSize;
//        List<Emp> rows = empMapper.List(start, pageSize);
//
//        return new PageResult<Emp>(total,rows);
//    }

}
