package com.lpt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lpt.entity.Park;
import com.lpt.mapper.ParkMapper;

@Service
public class ParkService {
	
	@Autowired
	private ParkMapper parkMapper;
	
	//获取全部
	public List<Park> getAll() {
		// TODO Auto-generated method stub
		return parkMapper.findAllPark();
	}

	//根据所属人代码获取全部
	public List<Park> getAllByOwnerNo(String ownerNo) {
		// TODO Auto-generated method stub
		return parkMapper.findAllParkByOwnerNo(ownerNo);
	}

	//根据状态获取全部
	public List<Park> getAllByKey(int status) {
		// TODO Auto-generated method stub
		return parkMapper.findAllParkByKey(status);
	}

	//根据车位状态模糊查询
	public List<Park> getByKey(String key) {
		// TODO Auto-generated method stub
		return parkMapper.findParkByKey("%"+key+"%");
	}

	//查询
	public Park getById(int id) {
		return parkMapper.findCarById(id);
	}

	//通过id修改
	public boolean updateCar(Park car) {
		try {
			parkMapper.updateCar(car);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	//修改车位状态
	public boolean updateCarStatus(Park car) {
		try {
			parkMapper.updateCarStatus(car);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	//删除
	public boolean delCar(int id) {
		try {
			parkMapper.deleteCar(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	//添加
	public boolean addCar(Park car) {
		try {
			parkMapper.insertCar(car);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return false;
		}
	}

	//获取全部公共车位
		public List<Park> getAllPub() {
			// TODO Auto-generated method stub
			return parkMapper.findAllParkPub();
		}

	//获取全部私家车车位
	public List<Park> getAllPri() {
		// TODO Auto-generated method stub
		return parkMapper.findAllParkPri();
	}

	//根据状态查询公共车位
	public List<Park> getPubByKey(int status) {
		// TODO Auto-generated method stub
		return parkMapper.findPubParkByKey(status);
	}

	//根据状态查询公共车位
	public List<Park> getPriByKey(int status) {
		// TODO Auto-generated method stub
		return parkMapper.findPriParkByKey(status);
	}

	//根据状态查询公共车位
		public Integer findPubTotal() {
			// TODO Auto-generated method stub
			return parkMapper.findPubTotal();
		}

		//根据状态查询公共车位
		public Integer findPriTotal(String nowTime) {
			// TODO Auto-generated method stub
			return parkMapper.findPriTotal(nowTime);
		}
}
