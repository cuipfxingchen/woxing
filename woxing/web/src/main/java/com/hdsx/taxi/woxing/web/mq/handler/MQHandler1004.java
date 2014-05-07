package com.hdsx.taxi.woxing.web.mq.handler;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;
import com.hdsx.taxi.woxing.mqutil.message.handle.IMQMsgHanlder;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1004;
import com.hdsx.taxi.woxing.web.guice.GuiceFactory;

/**
 * 订单驾驶员取消流程
 * 
 * @author Steven
 * 
 */
public class MQHandler1004 implements IMQMsgHanlder {

	@Override
	public void dohandle(MQAbsMsg mqmsg) {
		MQMsg1004 msg = (MQMsg1004) mqmsg;

		GuiceFactory.getInstance().cancelOrderByDriver(msg.getOrderid(), msg.getReasoncode());
	}

}
