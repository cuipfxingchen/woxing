package com.hdsx.taxi.woxing.bean;

import java.util.Date;

import com.vividsolutions.jts.geom.Envelope;

/**
 * 车辆信息
 * 
 * @author Steven
 * 
 */
public class CarInfo {

	String idflag;// 电招平台id;
	String id;
	Date time;
	double lat, lon;
	int direction=0;
	float speed=0;
	boolean gpsavailabe;

	String lisencenumber = "";// 车牌号码
	String DriverName = "";// 司机称呼
	String company = "";// 出租车公司
	String driverphone="";
	String driverid;
	public String getDriverid() {
		return driverid;
	}

	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}

	String creditLevel="";

	public String getCreditLevel() {
		return creditLevel;
	}

	public void setCreditLevel(String creditLevel) {
		this.creditLevel = creditLevel;
	}

	public String getDriverphone() {
		return driverphone;
	}

	public void setDriverphone(String driverphone) {
		this.driverphone = driverphone;
	}

	public String getLisencenumber() {
		return lisencenumber;
	}

	public void setLisencenumber(String lisencenumber) {
		this.lisencenumber = lisencenumber;
	}

	public String getDriverName() {
		return DriverName;
	}

	public void setDriverName(String driverName) {
		DriverName = driverName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getIdflag() {
		return idflag;
	}

	public void setIdflag(String idflag) {
		this.idflag = idflag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	@Override
	public String toString() {
		return "CarInfo [idflag=" + idflag + ", id=" + id + ", time=" + time
				+ ", lat=" + lat + ", lon=" + lon + ", direction=" + direction
				+ ", speed=" + speed + ", gpsavailabe=" + gpsavailabe
				+ ", lisencenumber=" + lisencenumber + ", DriverName="
				+ DriverName + ", company=" + company + ", isEmpty=" + isEmpty
				+ "]";
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public boolean isGpsavailabe() {
		return gpsavailabe;
	}

	public void setGpsavailabe(boolean gpsavailabe) {
		this.gpsavailabe = gpsavailabe;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	boolean isEmpty;

	public Envelope toEnv() {
		Envelope env = new Envelope(this.lon, this.lon, this.lat, this.lat);
		return env;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CarInfo) {
			CarInfo a = (CarInfo) obj;

			if (this.id == a.getId())
				return true;

		}

		return false;
	}

	public CarInfo copy() {

		CarInfo c = new CarInfo();
		c.setDirection(this.direction);
		c.setEmpty(isEmpty);
		c.setGpsavailabe(gpsavailabe);
		c.setId(id);
		c.setIdflag(idflag);
		c.setLat(lat);
		c.setLon(lon);
		c.setSpeed(speed);
		c.setDirection(direction);
		c.setTime(time);
//		System.out.println(this.toString());
//		System.out.println(c.toString());
		return c;

	}
	


}
