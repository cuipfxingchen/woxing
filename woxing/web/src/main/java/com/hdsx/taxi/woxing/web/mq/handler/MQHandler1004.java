package com.hdsx.taxi.woxing.web.mq.handler;

import com.google.inject.Inject;
import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;
import com.hdsx.taxi.woxing.mqutil.message.handle.IMQMsgHanlder;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1004;
import com.hdsx.taxi.woxing.order.IOrderService;

/**
 * 订单驾驶员取消流程
 * 
 * @author Steven
 * 
 */
public class MQHandler1004 implements IMQMsgHanlder {

	@Inject
	IOrderService os;

	@Override
	public void dohandle(MQAbsMsg mqmsg) {
		MQMsg1004 msg = (MQMsg1004) mqmsg;

		os.cancelOrderByDriver(msg.getOrderid(), msg.getReasoncode());
	}

}
