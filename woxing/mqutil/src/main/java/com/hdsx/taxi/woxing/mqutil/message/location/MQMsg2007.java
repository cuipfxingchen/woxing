package com.hdsx.taxi.woxing.mqutil.message.location;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;

/*******************************************************************************
 * <b>类名:MQMsg2007</b> <br/>
 * 功能：扬招站区域<br/>
 * 日期: 2013年9月14日<br/>
 * 
 * @author 谢广泉 xiegqooo@gmail.com
 * @version 1.0.0
 * 
 ******************************************************************************/
public class MQMsg2007 extends MQAbsMsg {

	double xlon, ylat;
	double xdlon, ydlat;

	public MQMsg2007() {
		super();
	}

	public MQMsg2007(String customId) {
		super(customId);
	}

	@Override
	protected short getMessageId() {
		return 0x2007;
	}

	@Override
	protected void decodebody(BytesMessage msg) throws JMSException {
		this.xlon = msg.readInt() / 10E6;
		this.xdlon = msg.readInt() / 10E6;
		this.ylat = msg.readInt() / 10E6;
		this.ydlat = msg.readInt() / 10E6;
	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {
		msg.writeInt((int) (this.xlon * 10E6));
		msg.writeInt((int) (this.xdlon * 10E6));
		msg.writeInt((int) (this.ylat * 10E6));
		msg.writeInt((int) (this.ydlat * 10E6));
		return msg;
	}

	public double getXlon() {
		return xlon;
	}

	public void setXlon(double xlon) {
		this.xlon = xlon;
	}

	public double getYlat() {
		return ylat;
	}

	public void setYlat(double ylat) {
		this.ylat = ylat;
	}

	public double getXdlon() {
		return xdlon;
	}

	public void setXdlon(double xdlon) {
		this.xdlon = xdlon;
	}

	public double getYdlat() {
		return ydlat;
	}

	public void setYdlat(double ydlat) {
		this.ydlat = ydlat;
	}

	

}
