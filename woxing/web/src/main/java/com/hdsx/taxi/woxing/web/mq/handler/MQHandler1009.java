package com.hdsx.taxi.woxing.web.mq.handler;

import com.google.inject.Inject;
import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;
import com.hdsx.taxi.woxing.mqutil.message.handle.IMQMsgHanlder;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1009;
import com.hdsx.taxi.woxing.order.IOrderService;

public class MQHandler1009 implements IMQMsgHanlder {

	@Inject
	IOrderService os;

	@Override
	public void dohandle(MQAbsMsg mqmsg) {

		MQMsg1009 msg = (MQMsg1009) mqmsg;

		os.doFail(msg.getOrderid(), msg.getDescribtion(), msg.getReasoncode());
	}

}
