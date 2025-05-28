package com.example.mapper;

import com.example.pop.EmpExpr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工工作经历
 */
@Mapper
public interface EmpExprMapper {
//    void insertBatch(@Param("exprList") List<EmpExpr> exprList);
    void insertBatch(List<EmpExpr> exprList);

}
