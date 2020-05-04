package com.lpt.controller;

import com.lpt.entity.*;
import com.lpt.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private ParkService parkService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private IRedisService iRedisService;

	/*
	* 公告管理
	* */

	//获取公告列表
	@RequestMapping("/notice-list")
	public String getAllNotice(Model model) {
		List<Notice> noticeList = noticeService.getAll();
		model.addAttribute("noticeList",noticeList);
		model.addAttribute("noticeNum",noticeList.size());
		return "notice/notice-list";
	}

	//根据标题模糊查找公告
	@RequestMapping("/findNoticeByTitle")
	public String findNoticeByTitle(@RequestParam("titleTmp")String title,Model model) {
		List<Notice> noticeList = noticeService.getByTitle(title);
		model.addAttribute("noticeList",noticeList);
		model.addAttribute("noticeNum",noticeList.size());
		model.addAttribute("titleTmp",title);
		return "notice/notice-list";
	}

	//添加
	@RequestMapping("/notice-add")
	public String addNotice()
	{
		return "notice/notice-add";
	}

	//保存
	@ResponseBody
	@RequestMapping("/notice-save")
	public Map<String,String> saveNotice(HttpSession session,@ModelAttribute("notice") Notice notice){
		Map<String,String> map = new HashMap<String,String>();
		map.put("res", "1");
		Admin admin = (Admin)session.getAttribute("LogAdmin");
		notice.setAdmin_id(admin.getId());
		notice.setAdmin_name(admin.getName());
		if(noticeService.addNotice(notice)) {
			try {
				iRedisService.set(String.valueOf(notice.getId()), notice.getContent());
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("res", "0");
		}
		return map;
	}

	//删除
	@ResponseBody
	@RequestMapping("/delNotice")
	public Map<String,String> delNotce(@RequestParam("noticeId") int id){
		Map<String,String> map = new HashMap<String,String>();
		map.put("res", "1");
		if(noticeService.delNotice(id)) {
			iRedisService.remove(String.valueOf(id));
			map.put("res", "0");
		}
		return map;
	}

	//公告编辑
	@RequestMapping("/notice-edit")
	public String editNotice(@RequestParam("noticeId") int id,Model model){
		Notice notice = noticeService.getById(id);
		model.addAttribute("notice",notice);
		return "notice/notice-edit";
	}

	//修改
	@ResponseBody
	@RequestMapping("/notice-update")
	public Map<String,String> updateNotice(Notice notice){
		Map<String,String> map = new HashMap<String,String>();
		map.put("res", "1");
		if(noticeService.updateNotice(notice)) {
			try {
				iRedisService.set(String.valueOf(notice.getId()), notice.getContent());
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("res", "0");
		}
		return map;
	}

	/*
	 * 车位管理
	 * */

	//获取车位列表
	@RequestMapping("/car-list")
	public String getAllCar(Model model,HttpSession session) {
		Admin admin=(Admin) session.getAttribute("LogAdmin");
		List<Park> carList=new ArrayList<Park>();
		if(admin.getAdminType().equals("2")){//私家车管理只能看到个人名下车位
			carList= parkService.getAllByOwnerNo(admin.getUserId());
		}else{
			carList= parkService.getAll();
		}
		model.addAttribute("carList",carList);
		model.addAttribute("carNum",carList.size());
		return "car/car-list";
	}

	//模糊查找车位详情
	@RequestMapping("/findCarByKey")
	public String findCarByKey(@RequestParam("keyTmp")String key,Model model) {
		List<Park> carList = parkService.getByKey(key);
		model.addAttribute("carList",carList);
		model.addAttribute("carNum",carList.size());
		model.addAttribute("keyTmp",key);
		return "car/car-list";
	}

	//车位编辑
	@RequestMapping("/car-edit")
	public String editCar(@RequestParam("carId") int id,Model model){
		Park car = parkService.getById(id);
		model.addAttribute("car",car);
		return "car/car-edit";
	}

	//保存
	@ResponseBody
	@RequestMapping("/car-update")
	public Map<String,String> saveMessage(@ModelAttribute("car") Park car){
		Map<String,String> map = new HashMap<String,String>();
		map.put("res", "1");
		if(parkService.updateCar(car)) {
			map.put("res", "0");
		}
		return map;
	}

	//删除
	@ResponseBody
	@RequestMapping("/delCar")
	public Map<String,String> delCar(@RequestParam("carId") int id){
		Map<String,String> map = new HashMap<String,String>();
		map.put("res", "1");
		if(parkService.delCar(id)) {
			map.put("res", "0");
		}
		return map;
	}

	//跳转添加页
	@RequestMapping("/car-add")
	public String addCar(){
		return "car/car-add";
	}

	//添加车次
	@ResponseBody
	@RequestMapping("/car-save")
	public Map<String,String> carSave(@ModelAttribute("car") Park car,HttpSession session){
		Admin admin=(Admin) session.getAttribute("LogAdmin");//获取当前登录用户
		String adminType=admin.getAdminType();//管理员类型
		if("1".equals(adminType)){//公共管理员
			car.setParkCate("0");//公共车位
			car.setOwnerName("公共");
		}else if("2".equals(adminType)){//私家车位管路员
			car.setParkCate("1");//私家车位
			car.setOwnerNo(admin.getUserId());//车位所有人
			car.setOwnerName(admin.getName());
		}else {
			car.setParkCate("0");//公共车位
			car.setOwnerName("公共");
		}
		Map<String,String> map = new HashMap<String,String>();
		map.put("res", "1");
		if(parkService.addCar(car)) {
			map.put("res", "0");
		}
		return map;
	}
	
	/*
	* 订单管理
	* */

	//获取订单列表
	@RequestMapping("/order-list")
	public String getAllOrder(Model model,HttpSession session) {
		Admin admin=(Admin) session.getAttribute("LogAdmin");
		List<Order> orderList = new ArrayList<Order>();
		if(admin.getAdminType().equals("2")){
			orderList = orderService.getAllByOwnerNo(admin.getUserId());
		}else{
			orderList = orderService.getAll();
		}
		for(Order order:orderList){
			if(order.getStatus().equals("1")){
				Park park=parkService.getById(order.getParkId());//取停车位信息
				Users user=userService.getUserById(order.getUserId());//取停车人积分
				Integer point=user.getPoint();
					if(park.getPriceModel().equals("2")){//按次计费
						order.setPrice(park.getUnitPrice());//按次收费等于单价
					}else{//按时收费
					//计算相差小时数字
					Integer hours=DateUtils.getDatePoor(DateUtils.getNowDateString(), order.getStartTime());
					Double price=park.getUnitPrice()*hours;
					order.setPrice(price);//按小时计价算总价
				}
				if(point>=100&&point<=300) {//100~300享9折
					order.setPriceDz(order.getPrice()*0.9);
				}else if(point>=301&&point<=500) {//300~500享8折
					order.setPriceDz(order.getPrice()*0.8);
				}else if(point>=501) {//500以上享7折
					order.setPriceDz(order.getPrice()*0.7);
				}else {
					order.setPriceDz(order.getPrice());
				}
			}
			
		}
		model.addAttribute("orderList",orderList);
		model.addAttribute("orderNum",orderList.size());
		return "order/order-list";
	}

	//模糊查询订单
	@RequestMapping("/findOrderByKey")
	public String findOrderByKey(@RequestParam("keyTmp")String key,Model model) {
		List<Order> orderList = orderService.getByKey(key);
		model.addAttribute("orderList",orderList);
		model.addAttribute("orderNum",orderList.size());
		model.addAttribute("keyTmp",key);
		return "order/order-list";
	}
	
	//删除订单
	@ResponseBody
	@RequestMapping("/delOrder")
	public Map<String,String> delOrder(@RequestParam("orderId") int id){
		Map<String,String> map = new HashMap<String,String>();
		map.put("res", "1");
		Order order=orderService.getById(id);
		Park park=parkService.getById(order.getParkId());
		park.setStatus("0");
		parkService.updateCarStatus(park);
		if(orderService.delOrder(id)) {
			map.put("res", "0");
		}
		return map;
	}

	//车辆进入
	@ResponseBody
	@RequestMapping("/carEnter")
	public Map<String,String> carEnter(@RequestParam("orderId") int id){
		Map<String,String> map = new HashMap<String,String>();
		map.put("res", "1");
		Order order=orderService.getById(id);
		order.setStatus("1");
		Park park=parkService.getById(order.getParkId());
		park.setStatus("2");
		if(orderService.updateOrderStatus(order)) {
			parkService.updateCarStatus(park);
			map.put("res", "0");
		}
		return map;
	}

	//付费
	@ResponseBody
	@RequestMapping("/carPaym")
	public Map<String,String> carPaym(@RequestParam("orderId") int id){
		Map<String,String> map = new HashMap<String,String>();
		map.put("res", "1");
		Order order=orderService.getById(id);
		order.setStatus("2");
		Park park=parkService.getById(order.getParkId());
		park.setStatus("0");
		order.setEndTime(DateUtils.getNowDateString());
		if(park.getPriceModel().equals("2")){//按次计费
			order.setPrice(park.getUnitPrice());//按次收费等于单价
		}else{//按时收费
			//计算相差小时数字
			Integer hours=DateUtils.getDatePoor(DateUtils.getNowDateString(), order.getStartTime());
			Double price=park.getUnitPrice()*hours;
			order.setPrice(price);//按小时计价算总价
		}
		Users user=userService.getUserById(order.getUserId());
		user.setPoint(user.getPoint()+order.getPrice().intValue());//更新积分
		if(orderService.updateOrderStatus(order)) {
			parkService.updateCarStatus(park);
			userService.updatePoint(user);
			map.put("res", "0");
		}
		return map;
	}

	/*
	* 积分管理
	* */

	//后台获取积分列
	@RequestMapping("/point-list")
	public String getAllUserPoint(Model model) {
		List<Users> pointList = userService.getAllPoint();
		model.addAttribute("pointList",pointList);
		model.addAttribute("pointNum",pointList.size());
		return "point/point-list";
	}

	//后台获取积分列表模糊查
	@RequestMapping("/findPointByName")
	public String findPointByName(@RequestParam("nameTmp")String name,Model model) {
		List<Users> pointList = userService.getPointByName(name);
		model.addAttribute("pointList",pointList);
		model.addAttribute("pointNum",pointList.size());
		model.addAttribute("nameTmp",name);
		return "point/point-list";
	}

	/*
	* 留言管理
	* */

	//获取留言列表
	@RequestMapping("/message-list")
	public String getAllMessage(Model model) {
		List<Message> messageList = messageService.getAll();
		model.addAttribute("messageList",messageList);
		model.addAttribute("messageNum",messageList.size());
		return "message/message-list";
	}

	//根据留言内容模糊查询获取留言
	@RequestMapping("/findMessageByContent")
	public String findMessageByContent(@RequestParam("contentTmp")String content,Model model) {
		List<Message> messageList = messageService.getByContent(content);
		model.addAttribute("messageList",messageList);
		model.addAttribute("messageNum",messageList.size());
		model.addAttribute("contentTmp",content);
		return "message/message-list";
	}

	//管理员删除留言
	@ResponseBody
	@RequestMapping("/delMessage")
	public Map<String,String> delMessage(@RequestParam("messageId") int id){
		Map<String,String> map = new HashMap<String,String>();
		map.put("res", "1");
		if(messageService.delMessage(id)) {
			map.put("res", "0");
		}
		return map;
	}
	
	//跳转留言回复页面
	@RequestMapping("/answer-add")
	public String addMessage(@RequestParam("messageId") int id,Model model){
		model.addAttribute("messageId", id);
		return "message/answer-add";
	}
		
	//回复留言
	@ResponseBody
	@RequestMapping("/answer-save")
	public Map<String,String> answer(HttpSession session,@ModelAttribute("message") Message message){
		Map<String,String> map = new HashMap<String,String>();
		map.put("res", "1");
		Admin admin = (Admin)session.getAttribute("LogAdmin");
		message.setAdmin_id(admin.getId());
		message.setAdmin_name(admin.getName());
		if(messageService.updateMessage(message)) {
			map.put("res", "0");
		}
		return map;
	}

	/*
	 * 管理员管理
	 * */

	//管理员登录
	@RequestMapping("/login")
	public String login(@ModelAttribute("admin") Admin admin,HttpSession session,Model model) {
		if(admin.getUserId()==null) {
			model.addAttribute("msg", "请输入账户和密码");
			return "admin/login";
		}
		admin = adminService.login(admin);
		if(admin!=null) {
			session.setAttribute("LogAdmin", admin);
			if(admin.getAdminType().equals("0")){//超级管理员
				return "admin/index";
			}else{
				return "admin/indexPri";
			}
		}else {
			model.addAttribute("msg", "管理员账号或密码不正确");
			return "admin/login";
		}
	}

	//管理员退出登录
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("LogAdmin");
		return "admin/login";
	}

	//获取管理员列表
	@RequestMapping("/admin-list")
	public String getAllAdmin(Model model) {
		List<Admin> adminList = adminService.getAllAdmin();
		model.addAttribute("adminList",adminList);
		model.addAttribute("adminNum",adminList.size());
		return "admin/admin-list";
	}

	//根据姓名模糊查询
	@RequestMapping("/findAdminByName")
	public String getByName(@RequestParam("nameTmp")String name,Model model) {
		List<Admin> adminList = adminService.getAdminByName(name);
		model.addAttribute("adminList",adminList);
		model.addAttribute("adminNum",adminList.size());
		model.addAttribute("nameTmp",name);
		return "admin/admin-list";
	}

	//添加管理员
	@RequestMapping("/admin-add")
	public String addAdmin(){
		return "admin/admin-add";
	}

	@ResponseBody
	@RequestMapping("/admin-save")
	public Map<String,String> saveAdmin(@ModelAttribute("admin") Admin admin){
		Map<String,String> map = new HashMap<String,String>();
		map.put("res", "1");
		if(adminService.addAdmin(admin)) {
			map.put("res", "0");
		}
		return map;
	}

	//删除管理员账号用过Id
	@ResponseBody
	@RequestMapping("/delAdmin")
	public Map<String,String> delAdmin(@RequestParam("adminId") int id){
		Map<String,String> map = new HashMap<String,String>();
		map.put("res", "1");
		if(adminService.delAdmin(id)) {
			map.put("res", "0");
		}
		return map;
	}

	@RequestMapping("/change-info")
	public String editAdmin(@RequestParam("adminId") int id,Model model){
		Admin admin = adminService.getAdminById(id);
		model.addAttribute("admin",admin);
		return "admin/change-info";
	}

	@ResponseBody
	@RequestMapping("/updateAdmin")
	public Map<String,String> updateAdmin(Admin admin){
		Map<String,String> map = new HashMap<String,String>();
		map.put("res", "1");
		if(adminService.updateAdmin(admin)) {
			map.put("res", "0");
		}
		return map;
	}

	/*
	 * 用户管理
	 * */

	//后台模糊查询用户
	@RequestMapping("/findUserByName")
	public String findUserByName(@RequestParam("nameTmp")String name,Model model) {
		List<Users> userList = userService.getByName(name);
		model.addAttribute("userList",userList);
		model.addAttribute("userNum",userList.size());
		model.addAttribute("nameTmp",name);
		return "user/user-list";
	}

	//后台获取用户列表
	@RequestMapping("/user-list")
	public String getAllUser(Model model) {
		List<Users> userList = userService.getAll();
		model.addAttribute("userList",userList);
		model.addAttribute("userNum",userList.size());
		return "user/user-list";
	}

	//停用用户账号用过Id
	@ResponseBody
	@RequestMapping("/stopUser")
	public Map<String,String> stopAdmin(@RequestParam("userId") int id){
		Map<String,String> map = new HashMap<String,String>();
		map.put("res", "1");
		if(userService.updateStautsById(id, 1)) {
			map.put("res", "0");
		}
		return map;
	}

	//启用管理员账号用过Id
	@ResponseBody
	@RequestMapping("/startUser")
	public Map<String,String> startAdmin(@RequestParam("userId") int id){
		Map<String,String> map = new HashMap<String,String>();
		map.put("res", "1");
		if(userService.updateStautsById(id, 0)) {
			map.put("res", "0");
		}
		return map;
	}
}
