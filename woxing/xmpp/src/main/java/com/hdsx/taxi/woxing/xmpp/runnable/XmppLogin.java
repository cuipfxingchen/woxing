package com.hdsx.taxi.woxing.xmpp.runnable;

import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.keepalive.KeepAliveManager;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.ping.PingFailedListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hdsx.taxi.woxing.xmpp.impl.XmppManager;
import com.hdsx.taxi.woxing.xmpp.util.InitApp;

public class XmppLogin implements Runnable {
	public static boolean islogin=false;
	private final static Logger logger = LoggerFactory.getLogger(XmppLogin.class);
	private KeepAliveManager keepAliveManager;
	
	public void run() {
		login();
		XmppManager.getXmppManager().runConnectionTask();
	}
	
	public void login(){
		if (XmppConnection.getConnection().isConnected()) {
			if(!XmppConnection.getConnection().isAuthenticated()){
				try {
					XmppConnection.getConnection().login(InitApp.USERNAME, InitApp.PW);
					if(XmppConnection.getConnection().isAuthenticated()){
						logger.info("openfire登陆成功");
					}else{
						logger.info("openfire登陆失败");
					}
					Presence presence = new Presence(Presence.Type.available);
					XmppConnection.getConnection().sendPacket(presence);
					XmppConnection.getConnection().addConnectionListener(
							connectionListener);
					keepAliveManager=KeepAliveManager.getInstanceFor(XmppConnection.getConnection());
					keepAliveManager.setPingInterval(InitApp.PINGINTERVAL);
					keepAliveManager.addPingFailedListener(pingFailedListener);
				} catch (XMPPException e) {
					e.printStackTrace();
					logger.info("openfire登陆失败");
				}
			}
		} else {
			XmppConnection.openConnection();
		}
		
	}
	
	
	private ConnectionListener connectionListener = new ConnectionListener() {

		public void connectionClosed() {
			logger.info("connectionClosed");
			
		}

		public void connectionClosedOnError(Exception arg0) {
			logger.info("connectionClosedOnError");
			XmppManager.getXmppManager().connected();
		}

		public void reconnectingIn(int arg0) {
			logger.info("reconnectingIn");
			
		}

		public void reconnectionFailed(Exception arg0) {
			logger.info("reconnectionFailed");
			XmppManager.getXmppManager().connected();
			
		}

		public void reconnectionSuccessful() {
			logger.info("reconnectionSuccessful");
			
		}
		
	};
	
	private PingFailedListener pingFailedListener=new PingFailedListener(){

		public void pingFailed() {
			logger.info("pingFailed");
			keepAliveManager.stopPinging();
		}
		
	};
}
