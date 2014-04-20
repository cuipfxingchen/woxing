package com.hdsx.taxi.woxing.xmpp.runnable;

import java.io.File;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.XMPPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hdsx.taxi.woxing.xmpp.impl.XmppManager;
import com.hdsx.taxi.woxing.xmpp.util.InitApp;

public class SendMessage implements Runnable {

	private  String msgString;
	private  String driverid;
	private final static Logger logger = LoggerFactory.getLogger(SendMessage.class);
	
	public SendMessage(String msgString, String driverid) {
		super();
		this.msgString = msgString;
		this.driverid = driverid;
	}


	public void run() {
		Chat chat =JoinRoom.muc.createPrivateChat(InitApp.ROOM+File.separatorChar+driverid, null);
		try {
			chat.sendMessage(msgString);
			logger.info("向"+driverid+"发送成功："+msgString);
		} catch (XMPPException e) {
			logger.info("向"+driverid+"发送失败："+msgString);
			e.printStackTrace();
		}

		XmppManager.getXmppManager().runTask();
	}

}
