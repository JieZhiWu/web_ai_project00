package com.example.mapper;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParm;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;
import java.util.Map;

/**
 * 员工信息
 */
@Mapper
public interface EmpMapper {

    /**
     * 查询员工总记录数
     */
//    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
//    public Long count();

    /**
     * 分页查询员工列表
     */
//    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id " +
//            "order by e.update_time desc limit #{start},#{pageSize}")

//    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id " +
//            "order by e.update_time desc")
    public List<Emp> list(EmpQueryParm empQueryParm);

    /**
     * 保存员工信息
     */
    @Options(useGeneratedKeys = true, keyProperty = "id") //获取到生成的id主键
    @Insert("insert into emp(username,name,gender,phone,job,salary,image,entry_date,dept_id,create_time,update_time) " +
            "values(#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    /**
     *根据ID批量删除员工
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据ID查询员工信息
     */
    Emp getById(Integer id);

    /**
     * 更新员工信息
     */
    void updateById(Emp emp);

     /**
     * 查询员工职位人数
     */
    List<Map< String,Object>> countEmpJobData();

     /**
     * 查询员工性别人数
     */
    List<Map<String, Object>> countEmpGenderData();
}
