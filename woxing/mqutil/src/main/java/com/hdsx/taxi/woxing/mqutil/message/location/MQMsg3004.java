package com.hdsx.taxi.woxing.mqutil.message.location;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;

/*******************************************************************************
 * <b>类名:MQMsg3004</b> <br/>
 * 功能：...<br/>
 * 日期: 2013年9月13日<br/>
 * 
 * @author 谢广泉 xiegqooo@gmail.com
 * @version 1.0.0
 * 
 ******************************************************************************/
public class MQMsg3004 extends MQAbsMsg {

	/**
	 * 返回打车指数
	 */
	int result;
	
	public MQMsg3004() {
	}
	
	public MQMsg3004(String customId) {
		super(customId);
	}
	@Override
	protected short getMessageId() {
		return 0x3004;
	}

	@Override
	protected void decodebody(BytesMessage msg) throws JMSException {
		this.result = msg.readInt();
	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {
		msg.writeInt(this.result);
		return msg;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
}
