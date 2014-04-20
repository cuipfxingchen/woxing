package com.hdsx.taxi.woxing.mqutil.message;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

/**
 * *****************************************************************************
 * <b>类名:MQMessage</b> <br/>
 * 功能：对 byteMessage对像进行封装<br/>
 * 日期: 2013年8月24日<br/>
 * 
 * @author 谢广泉 xiegqooo@gmail.com
 * @version 1.0.0
 * 
 *****************************************************************************
 */
public interface MQMessage {
	/**
	 * 
	 * encode:消息加码
	 * 
	 * @author 谢广泉
	 * @param msg
	 * @throws JMSException
	 */
	BytesMessage encode(BytesMessage msg) throws JMSException;

	/**
	 * 
	 * decode:消息解码
	 * 
	 * @author 谢广泉
	 * @param msg
	 * @throws JMSException
	 */
	void decode(BytesMessage msg) throws JMSException;

}
