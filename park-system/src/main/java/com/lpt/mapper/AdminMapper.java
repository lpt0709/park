package com.lpt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lpt.entity.Admin;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminMapper {
	
	//添加信息
	@Insert("insert into admin(name,password,createDate,adminType,userId) values(#{name},#{password},SYSDATE(),#{adminType},#{userId})")
    public void insert(Admin admin);
	
	//删除信息
	@Delete("delete from admin where id=#{id}")
	public void delete(int id);
	
	//修改信息
	@Update("update admin set name=#{name},password=#{password} where id=#{id}")
	public void update(Admin admin);
	
	//根据管理员姓名查询信息
	@Select("select * from admin where name like #{name}")
	List<Admin> findByName(@Param("name") String name);
	
	//根据id查询信息
	@Select("select * from admin where id=#{id}")
	Admin findById(@Param("id") int id);

	//查询所有
	@Select("select * from admin")
	List<Admin> findAll();
	
	//登录
	@Select("select * from admin where userId=#{userId} and password = #{password}")
	List<Admin> login(Admin admin);
}
