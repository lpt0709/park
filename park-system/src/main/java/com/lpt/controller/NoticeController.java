package com.lpt.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lpt.entity.Notice;
import com.lpt.service.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;

	//公告详情
	@RequestMapping("/notice-detail")
	public String showNotice(@RequestParam("id") int id,Model model){
		Notice notice = noticeService.getById(id);
		model.addAttribute("notice",notice);
		return "notice-detail";
	}
}
