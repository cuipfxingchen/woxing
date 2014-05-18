package com.hdsx.taxi.woxing.mqutil.msgpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.ehcache.Element;

import com.google.inject.Inject;
import com.hdsx.taxi.woxing.mqutil.guice.MqGuiceFactory;
import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;

public class ReturnMsgUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(ReturnMsgUtil.class);
	
	public static  int MAXCOUNT =  200; // 从缓存中取消息的最大次数

	public static  int SLEEP_PER_TIME = 100; // 每次取消息的间隔
	public MQAbsMsg getMsg(String customid, int msgid) {

		int count = 0;
		while (count < MAXCOUNT) {
				MQAbsMsg obj = MqGuiceFactory.getMQMsgPool().get(MQMsgPool.toKey(customid, msgid));
				if(obj!=null)
				{
				if (MqGuiceFactory.getMQMsgPool().Remove(customid, msgid)) {
					logger.debug("移除消息成功");
				}
				return obj;
				}
		
			count++;
			try {
				Thread.sleep(SLEEP_PER_TIME);
			} catch (InterruptedException e) {
				logger.error("超时错误", e);
			}
		}
		return null;
	}
}
