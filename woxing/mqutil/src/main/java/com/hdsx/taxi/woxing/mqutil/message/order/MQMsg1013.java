package com.hdsx.taxi.woxing.mqutil.message.order;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;

/**
 * 通知乘客接收到订单请求的司机个数
 * @author cuipengfei
 *
 */
public class MQMsg1013 extends MQAbsMsg {

	long orderId;
	int count;

	@Override
	protected short getMessageId() {
		// TODO Auto-generated method stub
		return 0x1013;
	}

	@Override
	protected void decodebody(BytesMessage msg) throws JMSException {
		this.orderId = msg.readLong();
		this.count = msg.readInt();

	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {
		msg.writeLong(orderId);
		msg.writeInt(count);
		return msg;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}



}
