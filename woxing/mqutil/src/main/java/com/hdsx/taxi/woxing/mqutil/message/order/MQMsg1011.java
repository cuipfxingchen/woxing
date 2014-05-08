package com.hdsx.taxi.woxing.mqutil.message.order;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;

/**
 * 乘客反饋
 * 
 * 上车时经度 UINT32 上车时纬度 UINT32 上车时间 BCD[7] yyyymmddhhmmss
 * 
 * @author Steven
 * 
 */
public class MQMsg1011 extends MQAbsMsg {
	long orderid;
	byte type;
	String desc;

	@Override
	protected short getMessageId() {
		return 0x1011;
	}

	public long getOrderid() {
		return orderid;
	}

	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}


	

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	protected void decodebody(BytesMessage msg) throws JMSException {
		this.orderid = msg.readLong();
		this.type=msg.readByte();
		this.desc=msg.readUTF();
	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {
		msg.writeLong(orderid);
		msg.writeByte(type);
		msg.writeUTF(desc);
		return msg;
	}

}
