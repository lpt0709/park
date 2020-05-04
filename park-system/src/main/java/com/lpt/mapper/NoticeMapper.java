package com.lpt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.lpt.entity.Notice;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface NoticeMapper {

	/*
		@Options(useGeneratedKeys=true, keyProperty="id")
		useGeneratedKeys=true：获取由数据库自动生成的主键
		keyProperty="id"：指定把获取到的主键值注入到Notice实体类的id属性
	 */
	//添加信息
	@Insert("insert into notice(title,content,createDate,admin_id,admin_name) values(#{title},#{content},SYSDATE(),#{admin_id},#{admin_name})")
	@Options(useGeneratedKeys=true, keyProperty="id")
    public void insertNotice(Notice notice);
	
	//删除信息
	@Delete("delete from notice where id=#{id}")
	public void deleteNoticeById(int id);
	
	//修改信息
	@Update("update notice set title=#{title},content=#{content} where id=#{id}")
	public void updateNotice(Notice notice);
	
	//查询信息
	@Select("select * from notice where title like #{title}")
	List<Notice> findByTitle(@Param("title") String title);
	
	//查询信息
	@Select("select * from notice where id=#{id}")
	Notice findById(@Param("id") int id);
	
	@Select("select * from notice order by createDate desc limit 0,10")
	List<Notice> findAllNotice();
	
	
}
