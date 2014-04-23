package com.hdsx.taxi.woxing.mqutil.message.order;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;

/**
 * 抢车失败
 * 
 * @author Steven
 * 
 */
public class MQMsg1009 extends MQAbsMsg {
	long orderid;
	byte reasoncode;
	String describtion;

	@Override
	protected short getMessageId() {
		// TODO Auto-generated method stub
		return 0x1009;
	}

	@Override
	protected void decodebody(BytesMessage msg) throws JMSException {
		this.orderid = msg.readLong();
		this.reasoncode = msg.readByte();
		this.describtion = msg.readUTF();

	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {
		msg.writeLong(orderid);
		msg.writeByte(reasoncode);
		msg.writeUTF(describtion);
		return msg;
	}

	public long getOrderid() {
		return orderid;
	}

	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}

	public byte getReasoncode() {
		return reasoncode;
	}

	public void setReasoncode(byte reasoncode) {
		this.reasoncode = reasoncode;
	}

	public String getDescribtion() {
		return describtion;
	}

	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}

}
