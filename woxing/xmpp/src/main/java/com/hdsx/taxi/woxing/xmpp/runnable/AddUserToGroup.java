package com.hdsx.taxi.woxing.xmpp.runnable;

import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hdsx.taxi.woxing.xmpp.impl.XmppManager;
import com.hdsx.taxi.woxing.xmpp.util.InitApp;


public class AddUserToGroup implements Runnable {

	private String groupName;
	private String customid;;
	private final static Logger logger = LoggerFactory.getLogger(AddUserToGroup.class);
	
	public AddUserToGroup(String customid,String groupName) {
		super();
		this.groupName = groupName;
		this.customid = customid;
	}

	public void run() {
		// TODO Auto-generated method stub
		add();
		XmppManager.getXmppManager().runTask();
	}

	private void add(){
    	RosterGroup group = XmppConnection.getConnection().getRoster().getGroup(groupName);
                // 这个组已经存在就添加到这个组，不存在创建一个组
                RosterEntry entry = XmppConnection.getConnection().getRoster().getEntry(customid);
                try {
                    if (group != null) {
                        if (entry != null)
                            group.addEntry(entry);
                    } else {
                        RosterGroup newGroup = XmppConnection.getConnection().getRoster().createGroup(groupName);
                        if (entry != null)
                            newGroup.addEntry(entry);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
	}
	
}
