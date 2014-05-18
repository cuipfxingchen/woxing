package com.hdsx.taxi.woxing.mqutil.message.order;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;

/**
 * 司机位置信息
 * @author cuipengfei
 *
 */
public class MQMsg1012 extends MQAbsMsg {
	long orderid;
	double lat, lon;
	String carNumber="";

	@Override
	protected short getMessageId() {
		return 0x1012;
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

	

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	@Override
	protected void decodebody(BytesMessage msg) throws JMSException {
		this.orderid = msg.readLong();
		this.lat = msg.readDouble();
		this.lon = msg.readDouble();
		this.carNumber = msg.readUTF();
	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {
		msg.writeLong(orderid);
		msg.writeDouble(lat);
		msg.writeDouble(lon);
		msg.writeUTF(carNumber);
		return msg;
	}

}
