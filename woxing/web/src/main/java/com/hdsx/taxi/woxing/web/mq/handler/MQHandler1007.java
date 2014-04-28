package com.hdsx.taxi.woxing.web.mq.handler;

import java.util.HashMap;

import com.google.inject.Inject;
import com.hdsx.taxi.woxing.bean.Order;
import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;
import com.hdsx.taxi.woxing.mqutil.message.handle.IMQMsgHanlder;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1007;
import com.hdsx.taxi.woxing.order.IOrderService;
import com.hdsx.taxi.woxing.order.OrderPool;
import com.hdsx.taxi.woxing.xmpp.IXMPPService;
import com.hdsx.taxi.woxing.xmpp.XMPPBean;

/**
 * 乘客上车通知
 * @author cuipengfei
 *
 */
public class MQHandler1007 implements IMQMsgHanlder {

	@Inject
	IOrderService os;
	
	@Override
	public void dohandle(MQAbsMsg mqmsg) {

		MQMsg1007 msg = (MQMsg1007) mqmsg;
		os.getOnCarMsg(msg);
	}

}
