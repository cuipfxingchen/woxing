package com.hdsx.taxi.woxing.xmpp.runnable;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hdsx.taxi.woxing.xmpp.impl.XmppManager;

public class SendMessagetoAll implements Runnable {

	private  String msgString;
	private final static Logger logger = LoggerFactory.getLogger(SendMessagetoAll.class);
	
	public SendMessagetoAll(String msgString) {
		super();
		this.msgString = msgString;
	}


	public void run() {
		Message msg=JoinRoom.muc.createMessage();
		msg.setBody(msgString);
		try {
			JoinRoom.muc.sendMessage(msg);
			logger.info("群发成功："+msgString);
		} catch (XMPPException e) {
			logger.info("群发失败："+msgString);
			e.printStackTrace();
		}
		XmppManager.getXmppManager().runTask();
	}

}
