package com.hdsx.taxi.woxing.web.mq.handler;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;
import com.hdsx.taxi.woxing.mqutil.message.handle.IMQMsgHanlder;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1005;
import com.hdsx.taxi.woxing.web.guice.GuiceFactory;

/**
 * 预约订单执行
 * @author cuipengfei
 *
 */
public class MQHandler1005 implements IMQMsgHanlder {

	@Override
	public void dohandle(MQAbsMsg mqmsg) {
		MQMsg1005 msg = (MQMsg1005) mqmsg;

		GuiceFactory.getInstance().startReversation(msg);
	}

}
