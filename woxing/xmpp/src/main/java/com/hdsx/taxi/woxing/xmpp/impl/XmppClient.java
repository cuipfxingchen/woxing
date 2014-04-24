package com.hdsx.taxi.woxing.xmpp.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterGroup;
import org.jivesoftware.smack.XMPPConnection;

import com.hdsx.taxi.woxing.xmpp.XMPPBean;
import com.hdsx.taxi.woxing.xmpp.runnable.AddUserToGroup;
import com.hdsx.taxi.woxing.xmpp.runnable.JoinRoom;
import com.hdsx.taxi.woxing.xmpp.runnable.SendMessage;
import com.hdsx.taxi.woxing.xmpp.runnable.SendMessagetoAll;
import com.hdsx.taxi.woxing.xmpp.runnable.XmppConnection;
import com.hdsx.taxi.woxing.xmpp.util.InitApp;

public class XmppClient {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(XmppClient.class);

	private static XmppClient xmppClient;

	public static XmppClient getXmppClient() {
		if (xmppClient == null) {
			xmppClient = newXmppClient();
		}
		return xmppClient;
	}

	private static synchronized XmppClient newXmppClient() {
		if (xmppClient == null) {
			return new XmppClient();
		} else {
			return xmppClient;
		}

	}

	public XmppClient() {
		super();
		XmppManager.getXmppManager().connected();
	}

	public void sendMessage(String msgString, String driverid) {
		XmppManager.getXmppManager().addTash(
				new SendMessage(msgString, driverid));
	}

	public void sendMessagetoAll(String groupName,String msgString) {
		XmppManager.getXmppManager().addTash(new SendMessagetoAll(groupName,msgString));
	}

//	public List<String> GetOnlineDriverList() {
//		Iterator<String> it = JoinRoom.muc.getOccupants();
//		List<String> list = new ArrayList<String>();
//		while (it.hasNext()) {
//			list.add(it.next());
//		}
//		return list;
//	}
//
//	public int GetOnlineDriverCount() {
//		Iterator<String> it = JoinRoom.muc.getOccupants();
//		int count = 0;
//		while (it.hasNext()) {
//			it.next();
//			count++;
//		}
//		return count;
//	}

	/**
     * 把一个好友添加到一个组中
     * @param userJid
     * @param groupName
     */
    public void addUserToGroup(String customid,String group) {
    	XmppManager.getXmppManager().addTash(
				new AddUserToGroup(customid, group));
    }
	
	public void sendMessage(String customid, XMPPBean value) {
		ObjectMapper mapper = new ObjectMapper();
		String json;
		try {
			json = mapper.writeValueAsString(value);
			XmppManager.getXmppManager().addTash(
					new SendMessage(json, customid));
		} catch (JsonGenerationException e) {
			logger.error("转换Json错误:", e);
		} catch (JsonMappingException e) {
			logger.error("转换Json错误:", e);
		} catch (IOException e) {
			logger.error("转换Json错误:", e);
		}

	}
}
