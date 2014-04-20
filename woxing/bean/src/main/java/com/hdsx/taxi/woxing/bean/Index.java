package com.hdsx.taxi.woxing.bean;

/*******************************************************************************
 * <b>类名:Index</b> <br/>
 * 功能：<br/>
 * 日期: 2013年9月14日<br/>
 * 
 * @author 谢广泉 xiegqooo@gmail.com
 * @version 1.0.0
 * 
 ******************************************************************************/
public class Index {
	int index;  //  打车指数
	int waittime; //  等待时间
	String zones;		// 区域 

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getWaittime() {
		return waittime;
	}

	public void setWaittime(int waittime) {
		this.waittime = waittime;
	}

	public String getZones() {
		return zones;
	}

	public void setZones(String zones) {
		this.zones = zones;
	}

}
