package com.hdsx.taxi.woxing.web.mq.handler;

import java.util.HashMap;
import java.util.Map;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;
import com.hdsx.taxi.woxing.mqutil.message.handle.IMQMsgHanlder;

/**
 * *****************************************************************************
 * <b>类名:HanlderFactory</b> <br/>
 * 功能：hanlder生成内容<br/>
 * 日期: 2013年8月24日<br/>
 * 
 * @author 谢广泉 xiegqooo@gmail.com
 * @version 1.0.0
 * 
 ***************************************************************************** 
 */
public class HanlderFactory {

	private static Map<Short, IMQMsgHanlder> strateg = new HashMap<Short, IMQMsgHanlder>();

	/**
	 * 使用 map 代替 if 、
	 */
	static {
//		strateg.put((short) 0x0001, new MQHandler0001());
//		strateg.put((short) 0x0003, new MQHandler0003());
		strateg.put((short) 0x1001, new MQHandler1001());
		strateg.put((short) 0x1002, new MQHandler1002());
		strateg.put((short) 0x3001, new MQHandler3001());
		strateg.put((short) 0x3002, new MQHandler3002());
//		strateg.put((short) 0x3004, new MQHandler3004());
//		strateg.put((short) 0x3005, new MQHandler3005());
//		strateg.put((short) 0x3006, new MQHandler3006());
//		strateg.put((short) 0x3008, new MQHandler3008());
		strateg.put((short) 0x5001, new MQHandler5001());
	}

	/**
	 * 
	 * 方法名：getHandlder <br/>
	 * 编写人：谢广泉<br/>
	 * 日期：2013年8月24日<br/>
	 * 功能描述：<br/>
	 * 生成 hanlder 处理内容
	 * 
	 * @param mqmsg
	 * @return
	 */
	public static IMQMsgHanlder getHandlder(MQAbsMsg mqmsg) {
		short id = mqmsg.getHead().getMsgId();
		IMQMsgHanlder hanlder = strateg.get(id);
		// hanlder.dohandle(mqmsg);
		return hanlder;
	}

}
