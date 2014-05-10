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
 * 付款成功通知
 * @author cuipengfei
 *
 */
public class MQMsg0006 extends MQAbsMsg {

	long orderId;
	byte cancle; // 0 ：现金 , 1: 银行卡 2：微信 3：支付宝
	String explain="";

	public MQMsg0006() {
		super();
	}

	public MQMsg0006(String customId) {
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
		return 0x0006;
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
