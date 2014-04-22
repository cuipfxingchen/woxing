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

	double xlat, ylon;
	double xdlat, ydlon;

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
		this.xlat = msg.readInt() / 10E6;
		this.xdlat = msg.readInt() / 10E6;
		this.ylon = msg.readInt() / 10E6;
		this.ydlon = msg.readInt() / 10E6;
	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {
		msg.writeInt((int) (this.xlat * 10E6));
		msg.writeInt((int) (this.xdlat * 10E6));
		msg.writeInt((int) (this.ylon * 10E6));
		msg.writeInt((int) (this.ydlon * 10E6));
		return msg;
	}

	public double getXlat() {
		return xlat;
	}

	public void setXlat(double xlat) {
		this.xlat = xlat;
	}

	public double getYlon() {
		return ylon;
	}

	public void setYlon(double ylon) {
		this.ylon = ylon;
	}

	public double getXdlat() {
		return xdlat;
	}

	public void setXdlat(double xdlat) {
		this.xdlat = xdlat;
	}

	public double getYdlon() {
		return ydlon;
	}

	public void setYdlon(double ydlon) {
		this.ydlon = ydlon;
	}

}
