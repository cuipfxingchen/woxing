package com.hdsx.taxi.woxing.mqutil.message.order;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;

/**
 * 预约订单执行通知 订单id Long 车牌号 String[8] 经度 UINT32 纬度 UINT32 时间 BCD[7] yyyymmddhhmmss
 * 
 * @author Steven
 * 
 */
public class MQMsg1005 extends MQAbsMsg {
	long orderid;

	String carLicensenumber;
	double lat;
	double lon;
	String time;

	@Override
	protected void decodebody(BytesMessage msg) throws JMSException {

		this.orderid = msg.readLong();
		this.carLicensenumber = msg.readUTF();
		this.lat = msg.readDouble();
		this.lon = msg.readDouble();
		this.time = msg.readUTF();

	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {
		msg.writeLong(this.orderid);
		msg.writeUTF(this.carLicensenumber);
		msg.writeDouble(this.lat);
		msg.writeDouble(this.lon);
		msg.writeUTF(this.time);
		return msg;
	}

	public long getOrderid() {
		return orderid;
	}

	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}

	public String getCarLicensenumber() {
		return carLicensenumber;
	}

	public void setCarLicensenumber(String carLicensenumber) {
		this.carLicensenumber = carLicensenumber;
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

	public MQMsg1005() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MQMsg1005(String customId) {
		super(customId);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected short getMessageId() {
		return 0x1005;
	}

}
