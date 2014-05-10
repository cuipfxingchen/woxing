package com.hdsx.taxi.woxing.web.mq.handler;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;
import com.hdsx.taxi.woxing.mqutil.message.handle.IMQMsgHanlder;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1002;
import com.hdsx.taxi.woxing.web.guice.GuiceFactory;

public class MQHandler1002 implements IMQMsgHanlder {

	@Override
	public void dohandle(MQAbsMsg mqmsg) {
		if(mqmsg instanceof MQMsg1002){
			MQMsg1002 msg=(MQMsg1002)mqmsg;
			long orderId=msg.getOrderId();
			String customId=GuiceFactory.getInstance().getMqCustomid(orderId);
			msg.getHead().setCustomId(customId);
			GuiceFactory.getMQMsgPool().put(msg);
		}
	}

}
