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
	String orderid;

	String carLicensenumber;
	float lat;
	float lon;
	String time;

	@Override
	protected void decodebody(BytesMessage msg) throws JMSException {

		this.orderid = msg.readUTF();
		this.carLicensenumber = msg.readUTF();
		this.lat = msg.readFloat();
		this.lon = msg.readFloat();
		this.time = msg.readUTF();

	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {
		msg.writeUTF(this.orderid);
		msg.writeUTF(this.carLicensenumber);
		msg.writeFloat(this.lat);
		msg.writeFloat(this.lon);
		msg.writeUTF(this.time);
		return null;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getCarLicensenumber() {
		return carLicensenumber;
	}

	public void setCarLicensenumber(String carLicensenumber) {
		this.carLicensenumber = carLicensenumber;
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
