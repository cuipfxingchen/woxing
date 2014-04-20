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
 * ClassName:Msg0002 Function: 查询订单状态 Date: 2013年7月3日 下午4:24:42
 * 
 * @author 谢广泉
 * @see
 */
public class MQMsg0002 extends MQAbsMsg {

	long orderId;

	public MQMsg0002() {
	}

	public MQMsg0002(String customId) {
		super(customId);
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	@Override
	protected short getMessageId() {
		return 0x0002;
	}

	@Override
	protected void decodebody(BytesMessage msg) throws JMSException {
		this.orderId = msg.readLong();
	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {
		msg.writeLong(this.orderId);
		return msg;
	}

}
