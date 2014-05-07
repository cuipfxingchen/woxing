package com.hdsx.taxi.woxing.web.mq.handler;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;
import com.hdsx.taxi.woxing.mqutil.message.handle.IMQMsgHanlder;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1006;
import com.hdsx.taxi.woxing.web.guice.GuiceFactory;

/**
 * 付款通知
 * @author cuipengfei
 *
 */
public class MQHandler1006 implements IMQMsgHanlder {
	
	@Override
	public void dohandle(MQAbsMsg mqmsg) {

		MQMsg1006 msg = (MQMsg1006) mqmsg;
		GuiceFactory.getInstance().payMoney(msg);
	}

}
