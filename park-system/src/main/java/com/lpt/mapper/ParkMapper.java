package com.lpt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lpt.entity.Park;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ParkMapper {
	
	//查询所有
	@Select("select * from park")
	List<Park> findAllPark();

	//根据所属模糊查询
	@Select("select * from park where ownerNo=#{ownerNo}")
	List<Park> findAllParkByOwnerNo(@Param("ownerNo")String ownerNo);

	//根据状态查询
	@Select("select * from park where  status = #{status}")
	List<Park> findAllParkByKey(@Param("status") int status);

	//
	@Select("select * from park where  parkNo like #{key}")
	List<Park> findParkByKey(@Param("key") String key);

	//根据id查询车位信息
	@Select("select * from park where id=#{id}")
	Park findCarById(@Param("id") int id);
	
	//添加车位信息
	@Insert("insert into park(parkNo, parkArea, ownerNo, ownerName, priceModel,unitPrice, nigthFlag, nigthPrice, startTime, endTime, status,parkCate,length,width)  values(#{parkNo},#{parkArea},#{ownerNo},#{ownerName},#{priceModel},#{unitPrice},#{nigthFlag},#{nigthPrice},#{startTime},#{endTime},0,#{parkCate},#{length},#{width})")
    public void insertCar(Park car);
	
	//删除车位信息
	@Delete("delete from park where id=#{id}")
	public void deleteCar(int id);

	//修改车位状态
	@Update("update park set status=#{status} where id=#{id}")
	public void updateCarStatus(Park car);
	
	//修改车位信息
	@Update("update park set parkNo=#{parkNo},parkArea=#{parkArea},priceModel=#{priceModel},unitPrice=#{unitPrice} where id=#{id}")
	public void updateCar(Park car);

	//查询全部公共车位
	@Select("select * from park where trim(ownerNo) is null")
	List<Park> findAllParkPub();

	//查询全部私家车位
	@Select("select * from park where trim(ownerNo) is not  null")
	List<Park> findAllParkPri();

	//根据状态查询公共车位
	@Select("select * from park where trim(ownerNo) is null and  status = #{status}")
	List<Park> findPubParkByKey(@Param("status") int status);

	//根据状态查询私家车位
	@Select("select * from park where trim(ownerNo) is not null and status = #{status}")
	List<Park> findPriParkByKey(@Param("status") int status);

	//统计公共可用车位
	@Select("select count(*) from park where trim(ownerNo) is  null and status ='0'")
	public Integer findPubTotal();

	//统计私家可用车位
	@Select("select count(*) from park where trim(ownerNo) is not null and status ='0' and startTime<=#{nowTime} and endTime>=#{nowTime}")
	public Integer findPriTotal(@Param("nowTime") String nowTime);
}
