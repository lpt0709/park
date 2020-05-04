package com.lpt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lpt.entity.Order;
import com.lpt.mapper.OrderMapper;

@Service
public class OrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	
	//获取全部订单
	public List<Order> getAll() {
		// TODO Auto-generated method stub
		return orderMapper.findAllOrder();
	}

	//根据订单id查询
	public Order getById(int id) {
		return orderMapper.findById(id);
	}
	
	//模糊查询，根据车主id查询
	public List<Order> getByUserId(int id) {
		return orderMapper.findByUserId(id);
	}
		
	//模糊查询
	public List<Order> getByKey(String tmpKey) {
		String key = "%"+tmpKey+"%";
		return orderMapper.findByKey(key);
	}
	
	//删除订单
	public boolean delOrder(int id) {
		try {
			orderMapper.deleteOrder(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	//添加订单
	public boolean addOrder(Order order) {
		try {
			orderMapper.insertOrder(order);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	//更新订单
	public boolean updateOrderStatus(Order order) {
		try {
			orderMapper.updateStatus(order);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	//获取全部
		public List<Order> getAllByOwnerNo(String ownerNo) {
			// TODO Auto-generated method stub
			return orderMapper.findByOwnerNo(ownerNo);
		}
}
