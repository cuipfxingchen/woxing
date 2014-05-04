package com.hdsx.taxi.woxing.web.mq.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;
import com.hdsx.taxi.woxing.mqutil.message.handle.IMQMsgHanlder;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1008;
import com.hdsx.taxi.woxing.web.guice.GuiceFactory;


public class MQHandler1008 implements IMQMsgHanlder {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(MQHandler1008.class);



	@Override
	public void dohandle(MQAbsMsg mqmsg) {

		if(mqmsg instanceof MQMsg1008){
			MQMsg1008 msg = (MQMsg1008) mqmsg;
			logger.info("duixiang:"+(GuiceFactory.getInstance()==null));
//			GuiceFactory.getInstance().update(msg.getNewid(),msg.getOldid() );
			GuiceFactory.getInstance2().update(msg.getNewid(),msg.getOldid() );
//			os.updateOrderId(msg.getOldid(), msg.getNewid());
		}
		


	}

}
