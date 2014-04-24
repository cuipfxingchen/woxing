package com.hdsx.taxi.woxing.xmpp.impl;

import com.google.inject.Singleton;
import com.hdsx.taxi.woxing.xmpp.IXMPPService;
import com.hdsx.taxi.woxing.xmpp.XMPPBean;

@Singleton
public class XMPPService implements IXMPPService {

	@Override
	public void sendMessage(String msgString, String driverid) {
		// TODO Auto-generated method stub
		XmppClient.getXmppClient().sendMessage(msgString, driverid);
	}

	@Override
	public void sendMessagetoAll(String group,String msgString) {
		// TODO Auto-generated method stub
		XmppClient.getXmppClient().sendMessagetoAll(group,msgString);
	}

//	@Override
//	public List<String> GetOnlineDriverList() {
//		// TODO Auto-generated method stub
//		return XmppClient.getXmppClient().GetOnlineDriverList();
//	}
//
//	@Override
//	public int GetOnlineDriverCount() {
//		// TODO Auto-generated method stub
//		return XmppClient.getXmppClient().GetOnlineDriverCount();
//	}

	@Override
	public void sendMessage(String customid, XMPPBean value) {
		XmppClient.getXmppClient().sendMessage(customid, value);

	}

	@Override
	public void addUserToGroup(String customid, String group) {
		// TODO Auto-generated method stub
		XmppClient.getXmppClient().addUserToGroup(customid, group);
	}

}
