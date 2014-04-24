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
 * ClassName:Msg0003 Function: 取消订单 Date: 2013年7月3日 下午4:24:42
 * 
 * @author 谢广泉
 * @see
 */
public class MQMsg0003 extends MQAbsMsg {

	long orderId;
	String cancel;
	String passengerName;// 乘客名称 String
	byte passengerSex;// 乘客性别 byte 1：男，2：女
	String passengerPhone;// 乘客电话 String
	byte causecode; // 取消原因

	public MQMsg0003() {
		super();
	}

	public MQMsg0003(String customId) {
		super(customId);
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getCancel() {
		return cancel;
	}

	public void setCancel(String cancel) {
		this.cancel = cancel;
	}

	@Override
	protected short getMessageId() {
		return 0x0003;
	}

	@Override
	protected void decodebody(BytesMessage msg) throws JMSException {
		this.orderId = msg.readLong();
		this.cancel = msg.readUTF();
		this.passengerName = msg.readUTF();
		this.passengerPhone = msg.readUTF();
		this.passengerSex = msg.readByte();
		this.causecode = msg.readByte();
	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {
		msg.writeLong(this.orderId);
		msg.writeUTF(this.cancel);
		msg.writeUTF(this.passengerName);
		msg.writeUTF(this.passengerPhone);
		msg.writeByte(passengerSex);
		msg.writeByte(this.causecode);
		return msg;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public byte getPassengerSex() {
		return passengerSex;
	}

	public void setPassengerSex(byte passengerSex) {
		this.passengerSex = passengerSex;
	}

	public String getPassengerPhone() {
		return passengerPhone;
	}

	public void setPassengerPhone(String passengerPhone) {
		this.passengerPhone = passengerPhone;
	}

	public byte getCausecode() {
		return causecode;
	}

	public void setCausecode(byte causecode) {
		this.causecode = causecode;
	}

}
