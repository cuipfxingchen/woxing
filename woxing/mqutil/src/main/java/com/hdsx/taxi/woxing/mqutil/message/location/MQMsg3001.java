/**
 * Project Name:MQMessage
 * File Name:Msg20011.java
 * Package Name:com.hdsx.taxi.message.location
 * Date:2013年7月3日下午3:22:12
 * Copyright (c) 2013, 谢广泉  All Rights Reserved.
 * 
 *
 */

package com.hdsx.taxi.woxing.mqutil.message.location;

import java.util.ArrayList;
import java.util.List;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import com.hdsx.taxi.woxing.bean.CarInfo;
import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;

/**
 * *****************************************************************************
 * <b>类名:MQMsg3001</b> <br/>
 * 功能：...<br/>
 * 日期: 2013年8月24日<br/>
 * 
 * @author 谢广泉 xiegqooo@gmail.com
 * @version 1.0.0
 * 
 ***************************************************************************** 
 */
public class MQMsg3001 extends MQAbsMsg {

	short count;
	List<CarInfo> cars = new ArrayList<CarInfo>();

	public MQMsg3001() {
		super();
	}

	public MQMsg3001(String customId) {
		super(customId);
	}

	public List<CarInfo> getCars() {
		return cars;
	}

	public void setCars(List<CarInfo> cars) {
		this.cars = cars;
	}

	@Override
	protected short getMessageId() {
		return 0x3001;
	}

	@Override
	protected void decodebody(BytesMessage msg) throws JMSException {
		this.count = msg.readShort();
		if (this.count > 0) {
			for (int i = 0; this.count > i; i++) {
				CarInfo car = new CarInfo();
				 car.setId(msg.readUTF());
				car.setLisencenumber(msg.readUTF());
				car.setDriverName(msg.readUTF());
				car.setLon(msg.readInt() / 10E6d);
				car.setLat(msg.readInt() / 10E6d);
				car.setCompany(msg.readUTF());
				cars.add(car);
			}
		}
	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {

		msg.writeShort((short) cars.size());

		for (int i = 0; i < cars.size(); i++) {
			CarInfo car = cars.get(i);
			msg.writeUTF(car.getId());
			msg.writeUTF(car.getLisencenumber());
			msg.writeUTF(car.getDriverName());
			msg.writeInt((int) (car.getLon() * 10E6));
			msg.writeInt((int) (car.getLat() * 10E6));
			msg.writeUTF(car.getCompany());
		}

		return msg;
	}

	@Override
	public String toString() {
		return "车辆:" + count;
		// +"\t所有车辆:"+cars;
	}

}
