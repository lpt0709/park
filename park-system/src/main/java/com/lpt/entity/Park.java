package com.lpt.entity;

public class Park {
	/*主键id*/
	private int id;
	/*停车位编号*/
	private String parkNo;
	/*停车位类别，0公共*/
	private String parkCate;
	/*停车位所属区域*/
	private String parkArea;
	/*所属人代码*/
	private String ownerNo;
	/*所属人名称*/
	private String ownerName;
	/*计费模式 月0/时1/次2*/
	private String priceModel;
	/*单价*/
	private Double unitPrice;
	/*是否收取过夜费*/
	private String nigthFlag;
	/*过夜费用*/
	private Double nigthPrice;
	/*可用开始时间*/
	private String startTime;
	/*可用结束时间*/
	private String endTime;
	/*状态 0可用 1已预订 2已使用*/
	private String status;
	/*车位大小，长*/
	private Double length;
	/*车位大小，宽*/
	private Double width;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getParkNo() {
		return parkNo;
	}
	public void setParkNo(String parkNo) {
		this.parkNo = parkNo;
	}
	public String getParkCate() {
		return parkCate;
	}
	public void setParkCate(String parkCate) {
		this.parkCate = parkCate;
	}
	public String getParkArea() {
		return parkArea;
	}
	public void setParkArea(String parkArea) {
		this.parkArea = parkArea;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getPriceModel() {
		return priceModel;
	}
	public void setPriceModel(String priceModel) {
		this.priceModel = priceModel;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getNigthFlag() {
		return nigthFlag;
	}
	public void setNigthFlag(String nigthFlag) {
		this.nigthFlag = nigthFlag;
	}
	public Double getNigthPrice() {
		return nigthPrice;
	}
	public void setNigthPrice(Double nigthPrice) {
		this.nigthPrice = nigthPrice;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getLength() {
		return length;
	}
	public void setLength(Double length) {
		this.length = length;
	}
	public Double getWidth() {
		return width;
	}
	public void setWidth(Double width) {
		this.width = width;
	}
	
	
}
