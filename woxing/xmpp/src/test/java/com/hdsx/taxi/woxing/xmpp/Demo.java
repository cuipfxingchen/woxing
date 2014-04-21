package com.hdsx.taxi.woxing.xmpp;

import com.hdsx.taxi.woxing.xmpp.impl.XMPPService;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IXMPPService iXMPPService=new XMPPService();
		iXMPPService.sendMessage("", "");
	}

}
