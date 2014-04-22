/**
 * Project Name:MQMessage
 * File Name:Msg2002.java
 * Package Name:com.hdsx.taxi.message.location
 * Date:2013年7月3日下午4:22:55
 * Copyright (c) 2013, 谢广泉  All Rights Reserved.
 * 
 *
 */

package com.hdsx.taxi.woxing.mqutil.message.location;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;

/**
 * *****************************************************************************
 * <b>类名:Msg2002</b> <br/>
 * 功能：查询目标订单车辆信息<br/>
 * 日期: 2013年7月31日<br/>
 * 
 * @author 谢广泉 xiegqooo@gmail.com
 * @version 1.0.0
 * 
 ***************************************************************************** 
 */
public class MQMsg2002 extends MQAbsMsg {

	long orderId;

	public MQMsg2002() {
		super();
	}

	public MQMsg2002(String customId) {
		super(customId);
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderid(long orderId) {
		this.orderId = orderId;
	}

	@Override
	protected short getMessageId() {

		return 0x2002;
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
