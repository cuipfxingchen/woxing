package com.hdsx.taxi.woxing.mqutil.message.location;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;

/*******************************************************************************
 * <b>类名:MQMsg2004</b> <br/>
 * 功能：打车指数<br/>
 * 日期: 2013年9月13日<br/>
 * 
 * @author 谢广泉 xiegqooo@gmail.com
 * @version 1.0.0
 * 
 ******************************************************************************/
public class MQMsg2004 extends MQAbsMsg {

	double lat, lon;

	public MQMsg2004() {
		super();
	}

	public MQMsg2004(String customId) {
		super(customId);
	}

	@Override
	protected short getMessageId() {
		return 0x2004;
	}

	@Override
	protected void decodebody(BytesMessage msg) throws JMSException {
		this.lat = msg.readInt() / 10E6;
		this.lon = msg.readInt() / 10E6;
	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {
		msg.writeInt((int) (this.lat * 10E6));
		msg.writeInt((int) (this.lon * 10E6));
		return msg;
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

}
