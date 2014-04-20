package com.hdsx.taxi.woxing.xmpp.impl;

import java.util.List;

import com.google.inject.Singleton;
import com.hdsx.taxi.woxing.xmpp.IXMPPService;

@Singleton
public class XMPPService implements IXMPPService {

	@Override
	public void sendMessage(String msgString, String driverid) {
		// TODO Auto-generated method stub
		XmppClient.getXmppClient().sendMessage(msgString, driverid);
	}

	@Override
	public void sendMessagetoAll(String msgString) {
		// TODO Auto-generated method stub
		XmppClient.getXmppClient().sendMessagetoAll(msgString);
	}

	@Override
	public List<String> GetOnlineDriverList() {
		// TODO Auto-generated method stub
		return XmppClient.getXmppClient().GetOnlineDriverList();
	}

	@Override
	public int GetOnlineDriverCount() {
		// TODO Auto-generated method stub
		return XmppClient.getXmppClient().GetOnlineDriverCount();
	}

	
}
