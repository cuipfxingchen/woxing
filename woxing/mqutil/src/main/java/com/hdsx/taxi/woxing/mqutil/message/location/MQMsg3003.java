package com.hdsx.taxi.woxing.mqutil.message.location;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;

/*******************************************************************************
 * <b>类名:MQMsg3003</b> <br/>
 * 功能：...<br/>
 * 日期: 2013年8月24日<br/>
 * 
 * @author 谢广泉 xiegqooo@gmail.com
 * @version 1.0.0
 * 
 ******************************************************************************/
public class MQMsg3003 extends MQAbsMsg {

	Integer cont;

	public MQMsg3003() {
		super();
	}

	public MQMsg3003(String customId) {
		super(customId);
	}

	public Integer getCont() {
		return cont;
	}

	public void setCont(Integer cont) {
		this.cont = cont;
	}

	@Override
	protected short getMessageId() {
		return 0x3003;
	}

	@Override
	protected void decodebody(BytesMessage msg) throws JMSException {
		this.cont = msg.readInt();
	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {
		msg.writeInt(this.cont);
		return msg;
	}

}
