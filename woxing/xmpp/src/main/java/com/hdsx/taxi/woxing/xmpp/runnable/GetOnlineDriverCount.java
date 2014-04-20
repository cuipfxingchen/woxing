package com.hdsx.taxi.woxing.xmpp.runnable;

import java.util.Iterator;

import com.hdsx.taxi.woxing.xmpp.impl.XmppManager;


public class GetOnlineDriverCount implements Runnable {

	public static  int count;

	public void run() {
		Iterator<String> it=JoinRoom.muc.getOccupants();
		count=0;
		while(it.hasNext()){
			it.next();
			count++;
		}
		XmppManager.getXmppManager().runTask();
	}

}
