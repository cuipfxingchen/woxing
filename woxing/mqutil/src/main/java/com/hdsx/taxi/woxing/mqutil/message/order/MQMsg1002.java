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
 * ClassName:Msg1002 Function: 查询订单回复 Date: 2013年7月3日 下午4:24:42
 * 
 * @author 谢广泉
 * @see
 */
public class MQMsg1002 extends MQAbsMsg {

	long orderId;
	byte state; // 0：处理中 1：完成调度（有车） 2：完成调度（无车）

	public MQMsg1002() {
	}

	public MQMsg1002(String customId) {
		super(customId);
	}

	/*
	 * 重写ToSting方法
	 */
	@Override
	public String toString() {
		return "MQMsg1002 [orderId=" + orderId + ", state=" + state + "]";
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	@Override
	protected short getMessageId() {
		return 0x1002;
	}

	@Override
	protected void decodebody(BytesMessage msg) throws JMSException {
		this.orderId = msg.readLong();
		this.state = msg.readByte();
	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {
		msg.writeLong(this.orderId);
		msg.writeByte(this.state);
		return msg;
	}

}
