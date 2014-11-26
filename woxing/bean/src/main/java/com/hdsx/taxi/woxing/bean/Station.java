package com.hdsx.taxi.woxing.bean;
/*******************************************************************************
 * <b>类名:Station</b> <br/>
 * 功能：...<br/>
 * 日期: 2013年9月14日<br/>
 * 
 * @author 谢广泉 xiegqooo@gmail.com
 * @version 1.0.0
 * 
 ******************************************************************************/
public class Station {
	String id;// id
	String number; // 编号
	String district; // 所属区县
	String roadname; // 道路名称
	String position; // 位置描述
	double stopnum; // 车位数量
	String stoptype; // 停车类型
	String servicetype; // 服务类型
	double x;
	double y;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getRoadname() {
		return roadname;
	}
	public void setRoadname(String roadname) {
		this.roadname = roadname;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public double getStopnum() {
		return stopnum;
	}
	public void setStopnum(double stopnum) {
		this.stopnum = stopnum;
	}
	public String getStoptype() {
		return stoptype;
	}
	public void setStoptype(String stoptype) {
		this.stoptype = stoptype;
	}
	public String getServicetype() {
		return servicetype;
	}
	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	@Override
	public String toString() {
		return "Station [id=" + id + ", number=" + number + ", district="
				+ district + ", roadname=" + roadname + ", position="
				+ position + ", stopnum=" + stopnum + ", stoptype=" + stoptype
				+ ", servicetype=" + servicetype + ", x=" + x + ", y=" + y
				+ "]";
	}
	
}
