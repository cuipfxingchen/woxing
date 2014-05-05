package com.hdsx.taxi.woxing.web.mq.handler;

import com.google.inject.Inject;
import com.hdsx.taxi.woxing.bean.Order;
import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;
import com.hdsx.taxi.woxing.mqutil.message.handle.IMQMsgHanlder;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1009;
import com.hdsx.taxi.woxing.order.IOrderService;
import com.hdsx.taxi.woxing.web.guice.GuiceFactory;

/**
 * 抢单失败
 * @author cuipengfei
 *
 */
public class MQHandler1009 implements IMQMsgHanlder {

	@Override
	public void dohandle(MQAbsMsg mqmsg) {

		MQMsg1009 msg = (MQMsg1009) mqmsg;

		/**
		 * 注意状态跟order中对应
		 */
		byte state=msg.getReasoncode();
		if(state==0){//无车
			state=Order.STATE_NOCAR;
		}else if(state==1){//无车应答
			state=Order.STATE_NOCARCall;
		}
		GuiceFactory.getInstance().doFail(msg.getOrderid(), msg.getDescribtion(), state);
	}

}
