package com.hdsx.taxi.woxing.web.mq.handler;

import com.google.inject.Inject;
import com.hdsx.taxi.woxing.bean.CarInfo;
import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;
import com.hdsx.taxi.woxing.mqutil.message.handle.IMQMsgHanlder;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1001;
import com.hdsx.taxi.woxing.mqutil.msgpool.MQMsgPool;
import com.hdsx.taxi.woxing.order.IOrderService;

/**
 * 抢单成功
 * 
 * @author Steven
 * 
 */
public class MQHandler1001 implements IMQMsgHanlder {

	@Inject
	MQMsgPool msgpool;

	@Inject
	IOrderService os;

	@Override
	public void dohandle(MQAbsMsg mqmsg) {
		MQMsg1001 msg = (MQMsg1001) mqmsg;

		CarInfo c = new CarInfo();
		c.setLisencenumber(msg.getNumber());
		c.setDriverphone(msg.getPhone());
		os.doSucess(msg.getOrderId(), c);
	}

}
