package com.hdsx.taxi.woxing.mqutil.message.order;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;

/**
 * 2.11 更新订单id通知（0x1008） 字段 数据类型 说明 原来订单id Long 新订单id Long
 * 
 * @author Steven
 * 
 */
public class MQMsg1008 extends MQAbsMsg {

	long oldid, newid;

	@Override
	protected short getMessageId() {
		// TODO Auto-generated method stub
		return 0x1008;
	}

	@Override
	protected void decodebody(BytesMessage msg) throws JMSException {
		this.oldid = msg.readLong();
		this.newid = msg.readLong();

	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {
		msg.writeLong(oldid);
		msg.writeLong(newid);
		return msg;
	}

	public long getOldid() {
		return oldid;
	}

	public void setOldid(long oldid) {
		this.oldid = oldid;
	}

	public long getNewid() {
		return newid;
	}

	public void setNewid(long newid) {
		this.newid = newid;
	}

}
