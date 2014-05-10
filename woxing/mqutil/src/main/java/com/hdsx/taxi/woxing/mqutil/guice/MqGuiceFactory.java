package com.hdsx.taxi.woxing.mqutil.guice;

import com.google.inject.Inject;
import com.hdsx.taxi.woxing.mqutil.msgpool.MQMsgPool;

public class MqGuiceFactory {

	@Inject
	static MQMsgPool mqmsgpool;
	
	public static MQMsgPool getMQMsgPool(){
		return mqmsgpool;
	}

}
