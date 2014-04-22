/**
 * Project Name:MQMessage
 * File Name:Msg3002.java
 * Package Name:com.hdsx.taxi.message.location
 * Date:2013年7月3日下午4:23:32
 * Copyright (c) 2013, 谢广泉  All Rights Reserved.
 * 
 *
 */

package com.hdsx.taxi.woxing.mqutil.message.location;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;

/**
 * ClassName:Msg3002 Function: 位置协议 Date: 2013年7月3日 下午4:23:32
 * 
 * @author 谢广泉
 * @see
 */
public class MQMsg3002 extends MQAbsMsg {

	long orderId;
	String car_number;
	String driver_name; // 某师傅
	double lon;
	double lat;
	String commpany;
	String driver_tel;

	public String getDriver_tel() {
		return driver_tel;
	}

	public void setDriver_tel(String driver_tel) {
		this.driver_tel = driver_tel;
	}

	public MQMsg3002() {
		super();
	}

	public MQMsg3002(String customId) {
		super(customId);
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public String getCommpany() {
		return commpany;
	}

	public void setCommpany(String commpany) {
		this.commpany = commpany;
	}

	public String getCar_number() {
		return car_number;
	}

	public void setCar_number(String car_number) {
		this.car_number = car_number;
	}

	public String getDriver_name() {
		return driver_name;
	}

	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}

	@Override
	protected short getMessageId() {
		return 0x3002;
	}

	@Override
	protected void decodebody(BytesMessage msg) throws JMSException {
		this.orderId = msg.readLong();
		this.car_number = msg.readUTF();
		this.driver_name = msg.readUTF();
		this.lon = msg.readInt() / 10E6d;
		this.lat = msg.readInt() / 10E6d;
		this.commpany = msg.readUTF();
		this.driver_tel = msg.readUTF();
	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {
		msg.writeLong(this.orderId);
		msg.writeUTF(this.car_number);
		msg.writeUTF(this.driver_name);
		msg.writeInt((int) (this.lon * 10E6));
		msg.writeInt((int) (this.lat * 10E6));
		msg.writeUTF(this.commpany);
		msg.writeUTF(this.driver_tel);
		return msg;
	}

}
