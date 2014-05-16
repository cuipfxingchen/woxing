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
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg0007;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1001;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1002;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1003;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1004;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1005;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1006;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1007;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1008;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1009;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1012;
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
		else if (id == 0x0002)
			return new MQMsg0002();
		else if (id == 0x0003)
			return new MQMsg0003();
		else if (id == 0x0007)
			return new MQMsg0007();
		else if (id == 0x1001)
			return new MQMsg1001();
		else if (id == 0x1002)
			return new MQMsg1002();
		else if (id == 0x1003)
			return new MQMsg1003();
		else if (id == 0x1004)
			return new MQMsg1004();
		else if (id == 0x1005)
			return new MQMsg1005();
		else if (id == 0x1006)
			return new MQMsg1006();
		else if (id == 0x1007)
			return new MQMsg1007();
		else if (id == 0x1008)
			return new MQMsg1008();
		else if (id == 0x1009)
			return new MQMsg1009();
		else if (id == 0x1012)
			return new MQMsg1012();

		/**
		 * 发送 位置内容
		 */
		if (id == 0x2001)
			return new MQMsg2001();
		else if (id == 0x2002)
			return new MQMsg2002();
		else if (id == 0x2003)
			return new MQMsg2003();
		else if (id == 0x2004)
			return new MQMsg2004();
		else if (id == 0x2005)
			return new MQMsg2005();
		else if (id == 0x2006)
			return new MQMsg2006();
		else if (id == 0x2007)
			return new MQMsg2007();
		else if (id == 0x2008)
			return new MQMsg2008();

		/**
		 * 返回 位置内容
		 */
		else if (id == 0x3001)
			return new MQMsg3001();
		else if (id == 0x3002)
			return new MQMsg3002();
		else if (id == 0x3003)
			return new MQMsg3003();
		else if (id == 0x3004)
			return new MQMsg3004();
		else if (id == 0x3005)
			return new MQMsg3005();
		else if (id == 0x3006)
			return new MQMsg3006(); // 跟 2007 和 2006 都使用同样返回内容

		else if (id == 0x3008)
			return new MQMsg3008();
		else if (id == 0x4001)
			return new MQMsg4001();
		else if (id == -1)
			return new IsDebugMsg();
		return null;
	}

}
