package com.hdsx.taxi.woxing.xmpp.runnable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hdsx.taxi.woxing.xmpp.impl.XmppManager;

public class GetOnlineDriverList implements Runnable {

	public static List<String > list;
	
	public void run() {
		Iterator<String> it=JoinRoom.muc.getOccupants();
		List<String > list=new ArrayList<String>();
		while(it.hasNext()){
			list.add(it.next());
		}
		XmppManager.getXmppManager().runTask();
	}

}
