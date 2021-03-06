package com.hdsx.taxi.woxing.web.mq.handler;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;
import com.hdsx.taxi.woxing.mqutil.message.handle.IMQMsgHanlder;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1007;
import com.hdsx.taxi.woxing.web.guice.GuiceFactory;

/**
 * 乘客上车通知
 * @author cuipengfei
 *
 */
public class MQHandler1007 implements IMQMsgHanlder {
	
	@Override
	public void dohandle(MQAbsMsg mqmsg) {

		MQMsg1007 msg = (MQMsg1007) mqmsg;
		GuiceFactory.getInstance().passengerGeton(msg);
		
	}
}
