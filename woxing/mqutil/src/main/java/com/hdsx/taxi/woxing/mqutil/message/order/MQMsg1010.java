package com.hdsx.taxi.woxing.mqutil.message.order;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;

/**
 * 乘客位置上傳
 * 
 * 上车时经度 UINT32 上车时纬度 UINT32 上车时间 BCD[7] yyyymmddhhmmss
 * 
 * @author Steven
 * 
 */
public class MQMsg1010 extends MQAbsMsg {
	long orderid;
	double lat, lon;
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


	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
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
		this.lat = msg.readDouble();
		this.lon = msg.readDouble();
		this.time = msg.readUTF();
	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {
		msg.writeLong(orderid);
		msg.writeDouble(lat);
		msg.writeDouble(lon);
		msg.writeUTF(time);
		return msg;
	}

}
