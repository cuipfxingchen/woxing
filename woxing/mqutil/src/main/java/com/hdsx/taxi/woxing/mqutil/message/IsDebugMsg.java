package com.hdsx.taxi.woxing.mqutil.message;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

public class IsDebugMsg extends MQAbsMsg {
	public IsDebugMsg() {
		super();
	}

	public IsDebugMsg(String customId) {
		super(customId);
	}

	boolean isdebug;

	public boolean isIsdebug() {
		return isdebug;
	}

	public void setIsdebug(boolean isdebug) {
		this.isdebug = isdebug;
	}

	@Override
	protected short getMessageId() {
		// TODO Auto-generated method stub
		return (short) 0xffff;
	}

	@Override
	protected void decodebody(BytesMessage msg) throws JMSException {
		// TODO Auto-generated method stub
		this.isdebug = msg.readBoolean();

	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {
		// TODO Auto-generated method stub
		msg.writeBoolean(this.isdebug);
		return msg;
	}

}
