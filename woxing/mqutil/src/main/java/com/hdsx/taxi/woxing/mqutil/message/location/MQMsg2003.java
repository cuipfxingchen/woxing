package com.hdsx.taxi.woxing.mqutil.message.location;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;

/*******************************************************************************
 * <b>类名:MQMsg2003</b> <br/>
 * 功能：...<br/>
 * 日期: 2013年8月24日<br/>
 * 
 * @author 谢广泉 xiegqooo@gmail.com
 * @version 1.0.0
 * 
 ******************************************************************************/
public class MQMsg2003 extends MQAbsMsg {

	String city;

	public MQMsg2003() {
		super();
	}

	public MQMsg2003(String customId) {
		super(customId);
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	protected short getMessageId() {

		return 0x2003;
	}

	@Override
	protected void decodebody(BytesMessage msg) throws JMSException {

		this.city = msg.readUTF();

	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {

		msg.writeUTF(this.city);

		return msg;
	}

}
