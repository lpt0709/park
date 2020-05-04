package com.lpt.entity;

public class Order {
	/*主键id*/
	private int id;
	/*停车人id*/
	private int userId;
	/*车牌号码*/
	private String plateNum;
	/*停车位id*/
	private int parkId;
	/*停车位编号*/
	private String parkNo;
	/*停车位所属区域*/
	private String parkArea;
	/*停车开始时间*/
	private String startTime;
	/*停车结束时间*/
	private String endTime;
	/*停车费用*/
	private Double price;
	/*订单状态 0预定 1进入未支付 2出场已支付*/
	private String status;
	/*电话*/
	private String phone;
	/*停车位所属人代码*/
	private String ownerNo;
	/*打折后费用*/
	private Double priceDz;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlateNum() {
		return plateNum;
	}
	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}
	public int getParkId() {
		return parkId;
	}
	public void setParkId(int parkId) {
		this.parkId = parkId;
	}
	public String getParkNo() {
		return parkNo;
	}
	public void setParkNo(String parkNo) {
		this.parkNo = parkNo;
	}
	public String getParkArea() {
		return parkArea;
	}
	public void setParkArea(String parkArea) {
		this.parkArea = parkArea;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public Double getPriceDz() {
		return priceDz;
	}
	public void setPriceDz(Double priceDz) {
		this.priceDz = priceDz;
	}
}
