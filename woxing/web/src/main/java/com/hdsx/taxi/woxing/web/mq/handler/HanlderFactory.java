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

//	private static Map<Short, IMQMsgHanlder> strateg = new HashMap<Short, IMQMsgHanlder>();

	/**
	 * 使用 map 代替 if 、
	 */
//	static {
//		// strateg.put((short) 0x0001, new MQHandler0001());
//		// strateg.put((short) 0x0003, new MQHandler0003());
//		strateg.put((short) 0x1001, new MQHandler1001());
//		strateg.put((short) 0x0007, new MQHandler0007());
//		strateg.put((short) 0x1002, new MQHandler1002());
//		strateg.put((short) 0x1003, new MQHandler1003());
//		strateg.put((short) 0x1004, new MQHandler1004());
//		strateg.put((short) 0x1005, new MQHandler1005());
//		strateg.put((short) 0x1006, new MQHandler1006());
//		strateg.put((short) 0x1007, new MQHandler1007());
//		strateg.put((short) 0x1008, new MQHandler1008());
//		strateg.put((short) 0x1009, new MQHandler1009());
//		strateg.put((short) 0x3001, new MQHandler3001());
//		strateg.put((short) 0x3002, new MQHandler3002());
//
//		// strateg.put((short) 0x3004, new MQHandler3004());
//		// strateg.put((short) 0x3005, new MQHandler3005());
//		// strateg.put((short) 0x3006, new MQHandler3006());
//		// strateg.put((short) 0x3008, new MQHandler3008());
//		strateg.put((short) 0x5001, new MQHandler5001());
//	}

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
		IMQMsgHanlder hd=null;
		switch (id) {
		case 0x0007:
			hd=new MQHandler0007();
			break;
		case 0x1001:
			hd=new MQHandler1001();
			break;
		case 0x1002:
			hd=new MQHandler1002();
			break;
		case 0x1003:
			hd=new MQHandler1003();
			break;
		case 0x1004:
			hd=new MQHandler1004();
			break;
		case 0x1005:
			hd=new MQHandler1005();
			break;
		case 0x1006:
			hd=new MQHandler1006();
			break;
		case 0x1007:
			hd=new MQHandler1007();
			break;
		case 0x1008:
			hd=new MQHandler1008();
			break;
		case 0x1009:
			hd=new MQHandler1009();
			break;
		case 0x3001:
			hd=new MQHandler3001();
			break;
		case 0x3002:
			hd=new MQHandler3002();
			break;
		case 0x5001:
			hd=new MQHandler5001();
			break;
		default:
			break;
		}
		
//		IMQMsgHanlder hanlder = strateg.get(id);
		// hanlder.dohandle(mqmsg);
//		return hanlder;
		return hd;
	}

}
