package com.lpt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.lpt.entity.Users;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
	
	//添加用户信息
	@Insert("insert into user(name,phone,plate_num,password,createDate,stauts,userId) values(#{name},#{phone},#{plate_num},#{password},SYSDATE(),0,#{userId})")
    public void insertUsers(Users users);
	
	//删除用户信息
	@Delete("delete from user where id=#{id}")
	public void deleteUserById(int id);
	
	//修改用户状态
	@Update("update user set stauts=#{stauts} where id=#{id}")
	public void updateUserStauts(@Param("id") int id,@Param("stauts") int stauts);

	//修改用户积分
	@Update("update user set point=#{point} where id=#{id}")
	public void updateUserPoint(Users user);

	//修改用户联系电话，积分
	@Update("update user set phone=#{phone},plate_num=#{plate_num} where id=#{id}")
	public void updateUser(Users user);

	//修改密码
	@Update("update user set password=#{password} where id=#{id}")
	public void updateUserPwd(Users user);

	//姓名模糊查询用户信息
	@Select("select * from user where name like #{name}")
	List<Users> findByName(@Param("name") String name);

	//查询用户信息
	@Select("select * from user")
	List<Users> findAllUser();

	//根据id查询用户信息
	@Select("select * from user where id=#{id}")
	Users findUserById(@Param("id") int id);

	//积分模糊查询用户
	@Select("select * from user order by point desc")
	List<Users> findAllPoint();

	//模糊查询
	@Select("select * from user where name like #{name} order by point desc")
	List<Users> findPointByName(@Param("name") String name);
	
	//登陆使用
	@Select("select * from user where userId=#{userId} and password = #{password}")
	List<Users> findUserByNameAndPwd(@Param("userId") String adminName,@Param("password") String password);
}
