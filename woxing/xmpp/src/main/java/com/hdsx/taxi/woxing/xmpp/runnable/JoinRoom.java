package com.hdsx.taxi.woxing.xmpp.runnable;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smackx.muc.DiscussionHistory;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hdsx.taxi.woxing.xmpp.impl.XmppManager;
import com.hdsx.taxi.woxing.xmpp.util.InitApp;


public class JoinRoom implements Runnable {

	public static MultiUserChat muc;
	private final static Logger logger = LoggerFactory.getLogger(JoinRoom.class);
	public void run() {
		// TODO Auto-generated method stub
		join();
		XmppManager.getXmppManager().runTask();
	}

	private void join(){
		muc = new MultiUserChat(XmppConnection.getConnection(), InitApp.ROOM);
		DiscussionHistory history = new DiscussionHistory();  
		history.setMaxChars(0);
		try {
			muc.join(InitApp.USERNAME,null,history,1000);
			logger.info("加入房间："+InitApp.ROOM+"成功");
		} catch (XMPPException e) {
			logger.info("加入房间："+InitApp.ROOM+"失败");
			e.printStackTrace();
		}
		muc.addMessageListener(packetListener);
	}
	
	public static PacketListener packetListener =  new PacketListener() {
		public void processPacket(Packet packet) {
			Message mes=(Message) packet;
			logger.info("收到消息："+mes.getBody());
			
		}
	};
}
