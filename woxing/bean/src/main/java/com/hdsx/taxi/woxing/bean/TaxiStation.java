package com.hdsx.taxi.woxing.bean;

import com.vividsolutions.jts.geom.Point;

/**
 * 出租车扬招站
 * 
 * @author Steven
 * 
 */
public class TaxiStation {

	String id;// id
	String number; // 编号
	String district; // 所属区县
	String roadname; // 道路名称
	String position; // 位置描述
	double stopnum; // 车位数量
	String stoptype; // 停车类型
	String servicetype; // 服务类型
	Point point;

	public double getStopnum() {
		return stopnum;
	}

	public void setStopnum(double stopnum) {
		this.stopnum = stopnum;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

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

	@Override
	public String toString() {
		return "TaxiStation [id=" + id + ", number=" + number + ", district="
				+ district + ", roadname=" + roadname + ", position="
				+ position + ", stopnum=" + stopnum + ", stoptype=" + stoptype
				+ ", servicetype=" + servicetype + ", point=" + point + "]";
	}
}
