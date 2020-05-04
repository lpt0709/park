package com.lpt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lpt.entity.Notice;
import com.lpt.mapper.NoticeMapper;

@Service
public class NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;

	//通过id修改状态
	public boolean updateNotice(Notice notice) {
		try {
			noticeMapper.updateNotice(notice);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	//名称模糊查询用户
	public List<Notice> getByTitle(String tmpName) {
		String title = "%"+tmpName+"%";
		return noticeMapper.findByTitle(title);
	}

	//查询用户
	public Notice getById(int id) {
		return noticeMapper.findById(id);
	}

	//获取全部公告
	public List<Notice> getAll() {
		// TODO Auto-generated method stub
		return noticeMapper.findAllNotice();
	}
	
	//删除公告
	public boolean delNotice(int id) {
		try {
			noticeMapper.deleteNoticeById(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	//添加公告
	public boolean addNotice(Notice notice) {
		try {
			noticeMapper.insertNotice(notice);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
