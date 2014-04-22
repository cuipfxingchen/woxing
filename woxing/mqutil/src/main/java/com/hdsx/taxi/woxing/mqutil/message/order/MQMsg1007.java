package com.hdsx.taxi.woxing.mqutil.message.order;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;

/**
 * 乘客上车通知
 * 
 * 上车时经度 UINT32 上车时纬度 UINT32 上车时间 BCD[7] yyyymmddhhmmss
 * 
 * @author Steven
 * 
 */
public class MQMsg1007 extends MQAbsMsg {
	long orderid;
	float lat, lon;
	String time;

	@Override
	protected short getMessageId() {
		return 0x1007;
	}

	public long getOrderid() {
		return orderid;
	}

	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLon() {
		return lon;
	}

	public void setLon(float lon) {
		this.lon = lon;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	protected void decodebody(BytesMessage msg) throws JMSException {
		this.orderid = msg.readLong();
		this.lat = msg.readFloat();
		this.lon = msg.readFloat();
		this.time = msg.readUTF();
	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {
		msg.writeLong(orderid);
		msg.writeFloat(lat);
		msg.writeFloat(lon);
		msg.writeUTF(time);
		return msg;
	}

}
