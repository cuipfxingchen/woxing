/**
 * Project Name:MQMessage
 * File Name:AbsMsg.java
 * Package Name:com.hdsx.taxi.message
 * Date:2013年7月3日下午3:06:30
 * Copyright (c) 2013, 谢广泉  All Rights Reserved.
 * test by wangxiri
 *
 */

package com.hdsx.taxi.woxing.mqutil.message;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

/**
 * 
 * ClassName: AbsMsg Function: 发送信息基类 date: 2013年7月3日 下午4:04:59
 * 
 * @author 谢广泉
 */
public abstract class MQAbsMsg implements MQMessage {

	MessageHead head;

	public MQAbsMsg() {
	}

	public MQAbsMsg(String customId) {
		this.head = new MessageHead();
		head.setCustomId(customId);
		head.setMsgId(getMessageId());
	}

	/**
	 * 
	 * getMessageId: 消息ID 获取
	 * 
	 * @author 谢广泉
	 * @return
	 */
	protected abstract short getMessageId();

	/**
	 * 
	 * decodebody: 消息体解码
	 * 
	 * @author 谢广泉
	 * @param msg
	 * @throws JMSException
	 */
	protected abstract void decodebody(BytesMessage msg) throws JMSException;

	/**
	 * 
	 * encodebody: 消息体加码
	 * 
	 * @author 谢广泉
	 * @param msg
	 * @throws JMSException
	 */
	protected abstract BytesMessage encodebody(BytesMessage msg)
			throws JMSException;

	/**
	 * 
	 * getHead: 查询头消息
	 * 
	 * @author 谢广泉
	 * @return
	 */
	public MessageHead getHead() {
		return head;
	}

	/**
	 * encode : 信息加码
	 * 
	 * @see com.hdsx.taxi.message.Message#encode(javax.jms.BytesMessage)
	 */
	public BytesMessage encode(BytesMessage msg) throws JMSException {
		msg.writeShort(head.getMsgId());
		msg.writeLong(head.getMsgsn());
		msg.writeUTF(head.getCustomId());
		msg = encodebody(msg);
		return msg;
	}

	/**
	 * decode : 信息解码
	 * 
	 * @see com.hdsx.taxi.message.Message#decode(javax.jms.BytesMessage)
	 */
	public void decode(BytesMessage msg) throws JMSException {
		this.head = new MessageHead();
		this.head.setMsgId(msg.readShort());
		this.head.setMsgsn(msg.readLong());
		this.head.setCustomId(msg.readUTF());
		decodebody(msg);
	}

}
