/**
 * Project Name:MQMessage
 * File Name:MessageHead.java
 * Package Name:com.hdsx.taxi.message
 * Date:2013年7月3日下午3:02:14
 * Copyright (c) 2013, 谢广泉  All Rights Reserved.
 * 
 *
 */

package com.hdsx.taxi.woxing.mqutil.message;

/**
 * ClassName:MessageHead Date: 2013年7月3日 下午3:02:14
 * 
 * @author 谢广泉
 * @see
 */
public class MessageHead {

	short msgId;
	long msgsn;
	String customId="";

	public MessageHead() {
		this.msgsn = System.currentTimeMillis();
	}

	public short getMsgId() {
		return msgId;
	}

	public void setMsgId(short msgId) {
		this.msgId = msgId;
	}

	public long getMsgsn() {
		return msgsn;
	}

	public void setMsgsn(long msgsn) {
		this.msgsn = msgsn;
	}

	public String getCustomId() {
		return customId;
	}

	public void setCustomId(String customId) {
		this.customId = customId;
	}

}
