package com.lpt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lpt.entity.Order;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderMapper {

	//查询全部订单
	@Select("select * from orders")
	List<Order> findAllOrder();
	
	//查询订单信息
	@Select("select * from orders where parkNo like #{key} or plateNum like #{key}")
	List<Order> findByKey(@Param("key") String key);
	
	//根据车主id查询订单信息
	@Select("select * from orders where userId=#{id}")
	List<Order> findByUserId(@Param("id") int id);

	//根据订单id查询订单信息
	@Select("select * from orders where id = #{id}")
	Order findById(@Param("id") int id);
	
	//添加订单信息
	@Insert("insert into orders( userId, plateNum, parkId, parkNo, parkArea, startTime, endTime, price, status,phone,ownerNo) values(#{userId},#{plateNum},#{parkId},#{parkNo},#{parkArea},#{startTime},#{endTime},#{price},#{status},#{phone},#{ownerNo})")
    public void insertOrder(Order order);
	
	//删除订单信息
	@Delete("delete from orders where id=#{id}")
	public void deleteOrder(int id);

	//修改订单信息
	@Update("update orders set status=#{status},price=#{price},endTime=#{endTime} where id=#{id}")
	public void updateStatus(Order order);

	//根据所属人查询订单
	@Select("select * from orders where ownerNo = #{ownerNo}")
	List<Order> findByOwnerNo(@Param("ownerNo") String  ownerNo);
}
