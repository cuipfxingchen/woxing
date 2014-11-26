package com.hdsx.taxi.woxing.bean;

public class OrderResult {

	public static byte RESULT_STATE_ONHANLDE = 0;     //正在处理
	public static byte RESULT_STATE_FINISH_NOCAR = 1; //完成，无车
	public static byte RESULT_STATE_FINISH_HASCAR = 2;  //完成，有车
	public static byte RESULT_STATE_NOORDER = 3;    //没有订单

	long id;
	String taxiid;
	String carNum;
	String driver_id;
	public String getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(String driver_id) {
		this.driver_id = driver_id;
	}

	String driver_tel;
	String driver_name;
	String car_color;
	String car_type;
	String car_company;

	public String getTaxiid() {
		return taxiid;
	}

	public void setTaxiid(String taxiid) {
		this.taxiid = taxiid;
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getDriver_tel() {
		return driver_tel;
	}

	public void setDriver_tel(String driver_tel) {
		this.driver_tel = driver_tel;
	}

	public String getDriver_name() {
		return driver_name;
	}

	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}

	public String getCar_color() {
		return car_color;
	}

	public void setCar_color(String car_color) {
		this.car_color = car_color;
	}

	public String getCar_type() {
		return car_type;
	}

	public void setCar_type(String car_type) {
		this.car_type = car_type;
	}

	public String getCar_company() {
		return car_company;
	}

	public void setCar_company(String car_company) {
		this.car_company = car_company;
	}

	byte state;

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "OrderResult [id=" + id + ", taxiid=" + taxiid + ", carNum="
				+ carNum + ", driver_id=" + driver_id + ", driver_tel="
				+ driver_tel + ", driver_name=" + driver_name + ", car_color="
				+ car_color + ", car_type=" + car_type + ", car_company="
				+ car_company + ", state=" + state + "]";
	}

}
