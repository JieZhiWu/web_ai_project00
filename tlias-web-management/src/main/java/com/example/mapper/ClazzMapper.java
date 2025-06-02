package com.example.mapper;

import com.example.pojo.Clazz;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ClazzMapper {

    /**
     * 分页查询班级列表
     */
    List<Clazz> list(String name, LocalDate begin, LocalDate end);


    /**
     * 查询所有班级列表
     */
    @Select("select * from clazz")
    List<Clazz> findAll();

    /**
     * 添加班级
     */
    @Insert("insert into clazz(name,room,begin_date,end_date,master_id,subject,create_time,update_time) " +
            "values(#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void add(Clazz clazz);

    /**
     * 根据id查询班级信息
     */
    @Select("select * from clazz where id = #{id}")
    Clazz getById(Integer id);

    /**
     * 更新班级信息
     */
    void update(Clazz clazz);

    /**
     * 根据id删除班级信息
     */
    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);
}
