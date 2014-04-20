package com.hdsx.taxi.woxing.mqutil.message;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg2001;
import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg2002;
import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg2003;
import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg2004;
import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg2005;
import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg2006;
import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg2007;
import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg2008;
import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg3001;
import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg3002;
import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg3003;
import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg3004;
import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg3005;
import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg3006;
import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg3008;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg0001;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg0002;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg0003;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1001;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1002;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1003;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg4001;

public class MessageFacotry {
	/**
	 * Logger for this class
	 */
	public static MQAbsMsg getMessage(BytesMessage msg) throws JMSException {
		short id = msg.readShort();
		msg.reset();

		if (id == 0x0001)
			return new MQMsg0001();
		if (id == 0x0002)
			return new MQMsg0002();
		if (id == 0x0003)
			return new MQMsg0003();
		if (id == 0x1001)
			return new MQMsg1001();
		if (id == 0x1002)
			return new MQMsg1002();
		if (id == 0x1003)
			return new MQMsg1003();
		if (id == -1)
			return new IsDebugMsg();

		/**
		 * 发送 位置内容
		 */
		if (id == 0x2001)
			return new MQMsg2001();
		if (id == 0x2002)
			return new MQMsg2002();
		if (id == 0x2003)
			return new MQMsg2003();
		if (id == 0x2004)
			return new MQMsg2004();
		if (id == 0x2005)
			return new MQMsg2005();
		if (id == 0x2006)
			return new MQMsg2006();
		if (id == 0x2007)
			return new MQMsg2007();
		if (id == 0x2008)
			return new MQMsg2008();

		/**
		 * 返回 位置内容
		 */
		if (id == 0x3001)
			return new MQMsg3001();
		if (id == 0x3002)
			return new MQMsg3002();
		if (id == 0x3003)
			return new MQMsg3003();
		if (id == 0x3004)
			return new MQMsg3004();
		if (id == 0x3005)
			return new MQMsg3005();
		if (id == 0x3006)
			return new MQMsg3006(); // 跟 2007 和 2006 都使用同样返回内容

		if (id == 0x3008)
			return new MQMsg3008();
		if (id == 0x4001)
			return new MQMsg4001();

		return null;
	}

}
