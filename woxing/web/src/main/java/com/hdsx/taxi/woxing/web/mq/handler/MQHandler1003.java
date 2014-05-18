package com.hdsx.taxi.woxing.web.mq.handler;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;
import com.hdsx.taxi.woxing.mqutil.message.handle.IMQMsgHanlder;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1003;
import com.hdsx.taxi.woxing.web.guice.GuiceFactory;

public class MQHandler1003 implements IMQMsgHanlder {


	@Override
	public void dohandle(MQAbsMsg mqmsg) {
		if(mqmsg instanceof MQMsg1003){
			MQMsg1003 msg=(MQMsg1003)mqmsg;
			long orderId=msg.getOrderId();
			String customId=GuiceFactory.getInstance().getMqCustomid(orderId);
			msg.getHead().setCustomId(customId);
			GuiceFactory.getMQMsgPool().put(msg);
		}
	}

}
