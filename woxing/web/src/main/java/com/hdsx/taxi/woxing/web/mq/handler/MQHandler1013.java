package com.hdsx.taxi.woxing.web.mq.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;
import com.hdsx.taxi.woxing.mqutil.message.handle.IMQMsgHanlder;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1013;
import com.hdsx.taxi.woxing.web.guice.GuiceFactory;


public class MQHandler1013 implements IMQMsgHanlder {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(MQHandler1013.class);



	@Override
	public void dohandle(MQAbsMsg mqmsg) {

		if(mqmsg instanceof MQMsg1013){
			MQMsg1013 msg = (MQMsg1013) mqmsg;
			GuiceFactory.getInstance2().noticeDriverCount(msg);

		}
		


	}

}
