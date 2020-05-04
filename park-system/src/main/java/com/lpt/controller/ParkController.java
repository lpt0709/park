package com.lpt.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lpt.entity.DateUtils;
import com.lpt.entity.Park;
import com.lpt.entity.Order;
import com.lpt.entity.Users;
import com.lpt.service.ParkService;
import com.lpt.service.UserService;
import com.lpt.service.OrderService;

@Controller
@RequestMapping("/car")
public class ParkController {

	@Autowired
	private ParkService parkService;
	
	@Autowired
	private OrderService orderService;

	//获取车位列表
	@RequestMapping("/car-select")
	public String getCarByKey(Model model) {
		List<Park> carListPub = parkService.getAllPub();//全部公共车位		
		//List<Park> carListPri = parkService.getAllPri();//全部私家车位
		String nowTime=DateUtils.getNowTime();
		/*for(Park park:carListPri){
			if(nowTime.compareTo(park.getStartTime())<0||nowTime.compareTo(park.getEndTime())>0) {
				park.setStatus("2");
			}*/
		Integer pubTotal=parkService.findPubTotal();//公共停车位
		//Integer priTotal=parkService.findPriTotal(nowTime);//私家车停车位
		Integer total=pubTotal/*+priTotal*/;
		model.addAttribute("pubCarList",carListPub);
		//model.addAttribute("priCarList",carListPri);
		model.addAttribute("parkTotal", "剩余停车位共："+total/*+"个，公共车位："+pubTotal+"个，私家车位："+priTotal+"个"*/);
		return "list";
	}

	@RequestMapping("/findCar")
	public String findCar(@RequestParam("status") int status,Model model) {
		model.addAttribute("status",status);
		if(status==-1) {//查询全部
			List<Park> carListPub = parkService.getAllPub();//全部公共车位		
			//List<Park> carListPri = parkService.getAllPri();//全部私家车位
			String nowTime=DateUtils.getNowTime();
			/*for(Park park:carListPri){
				if(nowTime.compareTo(park.getStartTime())<0||nowTime.compareTo(park.getEndTime())>0) {
					park.setStatus("2");
				}
			}*/
			model.addAttribute("pubCarList",carListPub);
			//model.addAttribute("priCarList",carListPri);
		}else {
			List<Park> carListPub = parkService.getPubByKey(status);//全部公共车位		
			//List<Park> carListPri = parkService.getPriByKey(status);//全部私家车位
			List<Park> carListPriNew=new ArrayList<Park>();
			String nowTime=DateUtils.getNowTime();
			/*for(int i=0;i<carListPri.size();i++){
				if(nowTime.compareTo(carListPri.get(i).getStartTime())>=0&&nowTime.compareTo(carListPri.get(i).getEndTime())<=0) {
					carListPriNew.add(carListPri.get(i));
				}
			}*/
			model.addAttribute("pubCarList",carListPub);
			model.addAttribute("priCarList",carListPriNew);
		}
		String nowTime=DateUtils.getNowTime();
		Integer pubTotal=parkService.findPubTotal();//公共停车位
		Integer priTotal=parkService.findPriTotal(nowTime);//私家车停车位
		Integer total=pubTotal+priTotal;
		//model.addAttribute("parkTotal", "剩余停车位共："+total+"个，公共车位："+pubTotal+"个，私家车位："+priTotal+"个");
		model.addAttribute("parkTotal", "剩余停车位共："+total+"个");
		return "list";
	}

	//获取车位详情
	@RequestMapping("/detail")
	public String editCar(@RequestParam("id") int id,Model model){
		Park car = parkService.getById(id);
		model.addAttribute("car",car);
		return "detail";
	}

	//停车结束，进行缴费
	@RequestMapping("/buy")
	public String buy(HttpSession session,@RequestParam("id") int id){
		Park car = parkService.getById(id);//查询停车信息
		Users user = (Users)session.getAttribute("LogUser");
		Order order =new Order();
		order.setParkArea(car.getParkArea());//停车场区域
		order.setParkId(car.getId());//停车位ID
		order.setParkNo(car.getParkNo());//停车位编号
		order.setPrice(0.00d);//停车费用
		order.setUserId(user.getId());//停车人Id
		order.setPlateNum(user.getPlate_num());//车牌号
		order.setStatus("0");//状态，已预约
		order.setStartTime(DateUtils.getNowDateString());
		order.setPhone(user.getPhone());
		order.setOwnerNo(car.getOwnerNo());//停车位所属人代码
		if(orderService.addOrder(order)){
			car.setStatus("1");//停车位状态更新成已预约
			parkService.updateCarStatus(car);
		}
		return "redirect:/order/showOrder";
	}
}
