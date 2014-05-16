package com.hdsx.taxi.woxing.web.mq.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;
import com.hdsx.taxi.woxing.mqutil.message.handle.IMQMsgHanlder;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1012;
import com.hdsx.taxi.woxing.web.guice.GuiceFactory;

/**
 * 司机位置信息推送
 * @author cuipengfei
 *
 */
public class MQHandler1012 implements IMQMsgHanlder {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(MQHandler1012.class);



	@Override
	public void dohandle(MQAbsMsg mqmsg) {

		if(mqmsg instanceof MQMsg1012){
			MQMsg1012 msg = (MQMsg1012) mqmsg;
			GuiceFactory.getLocationService().driverSitePush(msg);
		}
		


	}

}
