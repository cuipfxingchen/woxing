package com.hdsx.taxi.woxing.mqutil.msgpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.hdsx.taxi.woxing.bean.util.CacheManagerUtil;
import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;

@Singleton
public class MQMsgPool {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(MQMsgPool.class);

	final static String MQ_MSG_CACHE_NAME = "mqmsgpoolcache";

	private static final byte MAXCOUNT = (byte) 200; // 从缓存中取消息的最大次数

	private static final byte SLEEP_PER_TIME = 100; // 每次取消息的间隔
	Ehcache cache;

	@Inject
	public MQMsgPool(CacheManagerUtil cm) {
		cache = cm.getCm().getEhcache(MQ_MSG_CACHE_NAME);
	}

	/**
	 * 插入消息
	 * 
	 * @param msg
	 */
	public void put(MQAbsMsg msg) {
		Element e = new Element(toKey(msg), msg);
		cache.put(e);
		logger.debug("添加消息成功");
	}
	
	public MQAbsMsg  get(String key)
	{
		Element e = this.cache.get(key);
		if(e==null) return null;
		
		return (MQAbsMsg) e.getObjectValue();
	}

	/**
	 * 定义消息的唯一id
	 * 
	 * @param msg
	 * @return
	 */
	private String toKey(MQAbsMsg msg) {

		return new String(msg.getHead().getCustomId() + ","
				+ msg.getHead().getMsgId());
	}

	/**
	 * 
	 * 方法名：toKey <br/>
	 * 日期：2013年8月26日<br/>
	 * 功能描述：<br/>
	 * 通过 msgid + coustomid 拼写 key
	 * 
	 * @param customid
	 * @param msgid
	 * @return
	 */
	public static String toKey(String customid, int msgid) {
		return new String(customid + "," + msgid);
	}

	/**
	 * 获取消息
	 * 
	 * @param customid
	 * @param msgid
	 * @return
	 */
	public MQAbsMsg getMsg_depa(String customid, int msgid) {

		int count = 0;
		while (count < MAXCOUNT) {
			Element element = cache.get(toKey(customid, msgid));
			if (element != null) {
				MQAbsMsg obj = (MQAbsMsg) element.getObjectValue();
				if (Remove(customid, msgid)) {
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

	/**
	 * 移除消息
	 * 
	 * @param customid
	 * @param msgid
	 */
	public boolean Remove(String customid, int msgid) {
		return cache.remove(toKey(customid, msgid));
	}



}
