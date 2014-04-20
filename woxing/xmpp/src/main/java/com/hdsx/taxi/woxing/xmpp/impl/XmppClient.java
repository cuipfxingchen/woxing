package com.hdsx.taxi.woxing.xmpp.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hdsx.taxi.woxing.xmpp.runnable.JoinRoom;
import com.hdsx.taxi.woxing.xmpp.runnable.SendMessage;
import com.hdsx.taxi.woxing.xmpp.runnable.SendMessagetoAll;

public class XmppClient {

	private static XmppClient xmppClient;
	
	public static XmppClient getXmppClient(){
		if(xmppClient==null){
			xmppClient=newXmppClient();
		}
		return xmppClient;
	}

	private static synchronized XmppClient newXmppClient(){
		if(xmppClient==null){
			return new XmppClient();
		}else{
			return xmppClient;
		}
		
	}
	
	public XmppClient() {
		super();
		XmppManager.getXmppManager().connected();
	}
	
	public void sendMessage(String msgString, String driverid){
		XmppManager.getXmppManager().addTash(new SendMessage(msgString, driverid));
	}

	public void sendMessagetoAll(String msgString) {
		XmppManager.getXmppManager().addTash(new SendMessagetoAll(msgString));
	}
	
	public List<String > GetOnlineDriverList(){
		Iterator<String> it=JoinRoom.muc.getOccupants();
		List<String > list=new ArrayList<String>();
		while(it.hasNext()){
			list.add(it.next());
		}
		return list;
	}
	
	public int GetOnlineDriverCount(){
		Iterator<String> it=JoinRoom.muc.getOccupants();
		int count=0;
		while(it.hasNext()){
			it.next();
			count++;
		}
		return count;
	}
}
