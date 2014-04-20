package com.hdsx.taxi.woxing.mqutil.message.location;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;

/**
 * 通过车辆ID查询车辆信息
 * @author Steven
 *
 */
public class MQMsg2008 extends MQAbsMsg {
	String taxiid;

	public String getTaxiid() {
		return taxiid;
	}

	public void setTaxiid(String taxiid) {
		this.taxiid = taxiid;
	}

	public MQMsg2008() {
	}

	public MQMsg2008(String customId) {
		super(customId);
	}

	@Override
	protected short getMessageId() {
		// TODO Auto-generated method stub
		return 0x2008;
	}

	@Override
	protected void decodebody(BytesMessage msg) throws JMSException {

		this.taxiid = msg.readUTF();

	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {
		// TODO Auto-generated method stub
		msg.writeUTF(this.taxiid);
		return msg;
	}

}
