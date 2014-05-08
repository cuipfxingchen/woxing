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
 * ClassName:Msg1003 Function: 取消订单结果 Date: 2013年7月3日 下午4:24:42
 * 
 * @author 谢广泉
 * @see
 */
public class MQMsg1003 extends MQAbsMsg {

	long orderId;
	byte cancle; // 0 ：成功 , 1: 失败
	String explain="";

	public MQMsg1003() {
		super();
	}

	public MQMsg1003(String customId) {
		super(customId);
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public byte getCancle() {
		return cancle;
	}

	public void setCancle(byte cancle) {
		this.cancle = cancle;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	@Override
	protected short getMessageId() {
		return 0x1003;
	}

	@Override
	protected void decodebody(BytesMessage msg) throws JMSException {

		this.orderId = msg.readLong();
		this.cancle = msg.readByte();
		this.explain = msg.readUTF();

	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {
		msg.writeLong(this.orderId);
		msg.writeByte(this.cancle);
		msg.writeUTF(this.explain);
		return msg;
	}

}
