package com.hdsx.taxi.woxing.mqutil.message.location;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;

/*******************************************************************************
 * <b>类名:MQMsg2006</b> <br/>
 * 功能：扬招站<br/>
 * 日期: 2013年9月14日<br/>
 * 
 * @author 谢广泉 xiegqooo@gmail.com
 * @version 1.0.0
 * 
 ******************************************************************************/
public class MQMsg2006 extends MQAbsMsg {

	double lat, lon;
	short range;

	public MQMsg2006() {
		super();
	}

	public MQMsg2006(String customId) {
		super(customId);
	}

	@Override
	protected short getMessageId() {
		return 0x2006;
	}

	@Override
	protected void decodebody(BytesMessage msg) throws JMSException {

		this.lat = msg.readInt() / 10E6;
		this.lon = msg.readInt() / 10E6;
		this.range = msg.readShort();

	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {

		msg.writeInt((int) (this.lat * 10E6));
		msg.writeInt((int) (this.lon * 10E6));
		msg.writeShort(range);
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

	public short getRange() {
		return range;
	}

	public void setRange(short range) {
		this.range = range;
	}

}
