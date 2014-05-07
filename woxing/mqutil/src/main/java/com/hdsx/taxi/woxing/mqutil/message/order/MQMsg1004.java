/**
 * Project Name:MQMessage
 * File Name:Msg0001.java
 * Package Name:com.hdsx.taxi.message.order
 * Date:2013年7月3日下午4:24:42
 * Copyright (c) 2013, 谢广泉  All Rights Reserved.
 * 
 *
 */

package com.hdsx.taxi.woxing.mqutil.message.order;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;

/**
 * 驾驶员取消订单
 * 
 * @author Steven
 * 
 */
public class MQMsg1004 extends MQAbsMsg {

	public MQMsg1004() {
		super();

	}

	public MQMsg1004(String customId) {
		super(customId);

	}

	String carNumber;
	String phone;
	long orderid;

	public long getOrderid() {
		return orderid;
	}

	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	String driverid;
	String time;

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getDriverid() {
		return driverid;
	}

	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public byte getReasoncode() {
		return reasoncode;
	}

	public void setReasoncode(byte reasoncode) {
		this.reasoncode = reasoncode;
	}

	public String getReson() {
		return reson;
	}

	byte reasoncode;
	String reson; // 0-遇堵，1-事故，2-其它，3-乘客爽约

	@Override
	protected short getMessageId() {

		return 0x1004;
	}

	@Override
	protected void decodebody(BytesMessage msg) throws JMSException {

		this.carNumber = msg.readUTF();
		this.driverid = msg.readUTF();
		this.reasoncode = msg.readByte();
		this.time = msg.readUTF();
		this.phone = msg.readUTF();	
		this.orderid=msg.readLong();
	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {
		msg.writeUTF(this.carNumber);
		msg.writeUTF(this.driverid);
		msg.writeByte(this.reasoncode);
		msg.writeUTF(this.time);
		msg.writeUTF(this.phone);
		msg.writeLong(this.orderid);
		return msg;
	}

}
