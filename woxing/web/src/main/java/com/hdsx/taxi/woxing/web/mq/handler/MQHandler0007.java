package com.hdsx.taxi.woxing.web.mq.handler;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;
import com.hdsx.taxi.woxing.mqutil.message.handle.IMQMsgHanlder;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg0007;
import com.hdsx.taxi.woxing.web.guice.GuiceFactory;

public class MQHandler0007 implements IMQMsgHanlder {


	@Override
	public void dohandle(MQAbsMsg mqmsg) {
		
		if(mqmsg instanceof MQMsg0007){
			MQMsg0007 msg=(MQMsg0007)mqmsg;
			long orderId=msg.getOrderId();
			String customId=GuiceFactory.getInstance().getMqCustomid(orderId);
			msg.getHead().setCustomId(customId);
			GuiceFactory.getMQMsgPool().put(msg);
		}
	}

}
