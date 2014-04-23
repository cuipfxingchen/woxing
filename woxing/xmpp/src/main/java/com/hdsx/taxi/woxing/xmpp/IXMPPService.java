package com.hdsx.taxi.woxing.xmpp;

import java.util.List;

/**
 * XMPP服务
 * 
 * @author Steven
 * 
 */
public interface IXMPPService {

	/**
	 * 根据用户名发送消息
	 * 
	 * @param msgString
	 *            消息内容
	 * @param driverid
	 *            用户名
	 */
	void sendMessage(String msgString, String driverid);

	/**
	 * 群发消息
	 * 
	 * @param msgString
	 *            消息内容
	 */
	void sendMessagetoAll(String msgString);

	/**
	 * 获取群组在线用户名集合
	 * 
	 * @return 在线用户名集合
	 */
	List<String> GetOnlineDriverList();

	/**
	 * 获取群组在线人数
	 * 
	 * @return 在线人数
	 */
	int GetOnlineDriverCount();

	/**
	 * 发送消息
	 * @param customid
	 * @param value
	 */
	void sendMessage(String customid, XMPPBean value);
}
