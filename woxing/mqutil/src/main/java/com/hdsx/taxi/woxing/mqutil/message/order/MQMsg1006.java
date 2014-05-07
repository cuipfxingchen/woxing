package com.hdsx.taxi.woxing.mqutil.message.order;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;

/**
 * 付款通知 车牌号 String[8] 驾驶员监督卡号 String[19] 交易金额 short 单位：角 电召费用 short 单位：角 时间
 * BCD[7] yyyymmddhhnnss
 * 
 * @author Steven
 * 
 */
public class MQMsg1006 extends MQAbsMsg {

	public long getOrderid() {
		return orderid;
	}

	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}

	public String getCarlicensenumber() {
		return carlicensenumber;
	}

	public void setCarlicensenumber(String carlicensenumber) {
		this.carlicensenumber = carlicensenumber;
	}

	public String getDriverid() {
		return driverid;
	}

	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}

	public short getFee() {
		return fee;
	}

	public void setFee(short fee) {
		this.fee = fee;
	}

	public short getFee2() {
		return fee2;
	}

	public void setFee2(short fee2) {
		this.fee2 = fee2;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	long orderid;
	String carlicensenumber;
	String driverid;
	short fee;
	short fee2;
	String time;

	@Override
	protected short getMessageId() {
		return 0x1006;
	}

	@Override
	protected void decodebody(BytesMessage msg) throws JMSException {
		this.orderid = msg.readLong();
		this.carlicensenumber = msg.readUTF();
		this.driverid = msg.readUTF();
		this.fee = msg.readShort();
		this.fee2 = msg.readShort();
		this.time = msg.readUTF();
	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {
		msg.writeLong(this.orderid);
		msg.writeUTF(this.carlicensenumber);
		msg.writeUTF(this.driverid);
		msg.writeShort(this.fee);
		msg.writeShort(this.fee2);
		msg.writeUTF(this.time);
		return msg;
	}

}
