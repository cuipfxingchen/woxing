package com.hdsx.taxi.woxing.web.mq.handler;

import com.google.inject.Inject;
import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;
import com.hdsx.taxi.woxing.mqutil.message.handle.IMQMsgHanlder;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1008;
import com.hdsx.taxi.woxing.order.IOrderService;

/**
 * 更新订单号
 * @author cuipengfei
 *
 */
public class MQHandler1008 implements IMQMsgHanlder {

	@Inject
	IOrderService os;

	@Override
	public void dohandle(MQAbsMsg mqmsg) {

		MQMsg1008 msg = (MQMsg1008) mqmsg;

		os.update(msg.getNewid(), msg.getOldid());
	}

}
