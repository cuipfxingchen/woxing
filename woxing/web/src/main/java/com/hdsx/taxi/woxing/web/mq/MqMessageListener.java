package com.hdsx.taxi.woxing.web.mq;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;
import com.hdsx.taxi.woxing.mqutil.message.MessageFacotry;
import com.hdsx.taxi.woxing.web.mq.handler.HanlderFactory;

/*******************************************************************************
 * <b>类名:MqMessageListener</b> <br/>
 * 功能：...<br/>
 * 日期: 2013年8月24日<br/>
 * 
 * @author 谢广泉 xiegqooo@gmail.com
 * @version 1.0.0
 * 
 ******************************************************************************/
public class MqMessageListener implements MessageListener {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(MqMessageListener.class);


	public void onMessage(Message message) {

		try {

			if (message instanceof BytesMessage) {
				// 与判断接收到的Message属于何种类型
				BytesMessage msg = (BytesMessage) message;
				/*
				 * 获取判定参数 messageTypeId来判定接收到得Message信息属于什么通讯信息类型
				 * 
				 * messageTypeId 参数代表通讯信息类型判定参数
				 */
				MQAbsMsg mqmsg = MessageFacotry.getMessage(msg);
				mqmsg.decode(msg);
				if (logger.isDebugEnabled()) {
					logger.debug("onMessage(Message) - 收到数据" + mqmsg.toString()); //$NON-NLS-1$
				}
				HanlderFactory.getHandlder(mqmsg).dohandle(mqmsg);

			}

		} catch (JMSException e) {
			logger.error("onMessage(Message)", e); //$NON-NLS-1$
		}
	}

}
