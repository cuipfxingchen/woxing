/**
 * Project Name:MQMessage
 * File Name:Msg20011.java
 * Package Name:com.hdsx.taxi.message.location
 * Date:2013年7月3日下午3:22:12
 * Copyright (c) 2013, 谢广泉  All Rights Reserved.
 * 
 *
 */

package com.hdsx.taxi.woxing.mqutil.message.location;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;


/**
 * ClassName:Msg20011 Function: 查询周边空车 Date: 2013年7月3日 下午3:22:12
 * 
 * @author 谢广泉
 * @see
 */
public class MQMsg2001 extends MQAbsMsg {

	double lat, lon;
	short range;

	public MQMsg2001() {
	}

	public MQMsg2001(String customId) {
		super(customId);
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

	@Override
	protected short getMessageId() {

		return 0x2001;
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

	@Override
	public String toString() {
		return "纬度:"+lat+"\t经度:"+lon+"\t半径:"+range;
	}
	
}
