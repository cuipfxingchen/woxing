package com.hdsx.taxi.woxing.web.mq.handler;

import com.google.inject.Inject;
import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;
import com.hdsx.taxi.woxing.mqutil.message.handle.IMQMsgHanlder;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1006;
import com.hdsx.taxi.woxing.order.IOrderService;

/**
 * 付款通知
 * @author cuipengfei
 *
 */
public class MQHandler1006 implements IMQMsgHanlder {

	@Inject
	IOrderService os;
	
	@Override
	public void dohandle(MQAbsMsg mqmsg) {

		MQMsg1006 msg = (MQMsg1006) mqmsg;
		os.payMoney(msg);
	}

}
