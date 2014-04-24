package com.hdsx.taxi.woxing.xmpp.runnable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterGroup;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hdsx.taxi.woxing.xmpp.impl.XmppManager;
import com.hdsx.taxi.woxing.xmpp.util.InitApp;

public class SendMessagetoAll implements Runnable {

	private  String msgString;
	private  String groupName;
	private final static Logger logger = LoggerFactory.getLogger(SendMessagetoAll.class);
	
	public SendMessagetoAll(String groupName,String msgString) {
		super();
		this.msgString = msgString;
		this.groupName = groupName;
	}


	public void run() {
//		Message msg=JoinRoom.muc.createMessage();
//		msg.setBody(msgString);
//		try {
//			JoinRoom.muc.sendMessage(msg);
//			logger.info("群发成功："+msgString);
//		} catch (XMPPException e) {
//			logger.info("群发失败："+msgString);
//			e.printStackTrace();
//		}
		Roster roster=XmppConnection.getConnection().getRoster();
		ChatManager charManager=XmppConnection.getConnection().getChatManager();
		RosterGroup rosterGroup=roster.getGroup(groupName);
		Collection<RosterEntry> rosterEntry = rosterGroup.getEntries();
		Iterator<RosterEntry> i = rosterEntry.iterator();
		while (i.hasNext()){
//			EntriesList.add(i.next());
			RosterEntry r=i.next();
			Chat chat=charManager.createChat(r.getUser()+"@"+InitApp.SERVER_NAME, null);
			try {
				chat.sendMessage(msgString);
				logger.info("群发("+r.getUser()+")成功："+msgString);
			} catch (XMPPException e) {
				e.printStackTrace();
				logger.info("群发("+r.getUser()+")失败："+msgString);
			}
		}
		XmppManager.getXmppManager().runTask();
	}

}
