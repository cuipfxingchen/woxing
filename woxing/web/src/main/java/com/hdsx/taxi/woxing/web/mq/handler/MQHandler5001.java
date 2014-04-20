package com.hdsx.taxi.woxing.web.mq.handler;

import com.google.inject.Inject;
import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;
import com.hdsx.taxi.woxing.mqutil.message.handle.IMQMsgHanlder;
import com.hdsx.taxi.woxing.mqutil.msgpool.MQMsgPool;

public class MQHandler5001 implements IMQMsgHanlder {

	@Inject
	MQMsgPool msgpool;

	@Override
	public void dohandle(MQAbsMsg mqmsg) {
		msgpool.put(mqmsg);
	}

}
