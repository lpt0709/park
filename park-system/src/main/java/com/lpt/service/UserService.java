package com.lpt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lpt.entity.Users;
import com.lpt.mapper.UserMapper;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;

	//登录
	public Users login(Users users) {
		List<Users> list = userMapper.findUserByNameAndPwd(users.getUserId(), users.getPassword());
		if(list.size()>0) {
			Users user = list.get(0);
			return user;
		}
		return null;
	}

	//修改用户积分
	public boolean updatePoint(Users users) {
		try {
			userMapper.updateUserPoint(users);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	//通过id修改车位状态
	public boolean updateStautsById(int id,int stauts) {
		try {
			userMapper.updateUserStauts(id, stauts);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	//修改密码
	public boolean updateUserPwd(Users user) {
		try {
			userMapper.updateUserPwd(user);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	//修改用户联系电话，积分
	public boolean updateUser(Users user) {
		try {
			userMapper.updateUser(user);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	//名称模糊查询用户
	public List<Users> getByName(String tmpName) {
		String name = "%"+tmpName+"%";
		return userMapper.findByName(name);
	}

	//积分模糊查询用户
	public List<Users> getPointByName(String tmpName) {
		String name = "%"+tmpName+"%";
		return userMapper.findPointByName(name);
	}
	
	//获取全部用户
	public List<Users> getAll() {
		// TODO Auto-generated method stub
		return userMapper.findAllUser();
	}
	
	//获取全部用户
	public List<Users> getAllPoint() {
		// TODO Auto-generated method stub
		return userMapper.findAllPoint();
	}

	//根据id获取用户信息
	public Users getUserById(int id) {
		// TODO Auto-generated method stub
		return userMapper.findUserById(id);
	}
	
	//添加用户
	public boolean addUser(Users users) {
		try {
			userMapper.insertUsers(users);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
