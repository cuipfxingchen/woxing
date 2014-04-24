package com.hdsx.taxi.woxing.mqutil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Singleton;
import com.hdsx.taxi.woxing.mqutil.message.MQMessage;

@Singleton
public class MQService {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(MQService.class);

	/**
	 * Logger for this class
	 */

	static MQService obj;

	public static MQService getInstance() {
		if (obj == null)
			obj = new MQService();
		return obj;
	}

	MessageProducer pro = null;
	private Session session;
	MessageConsumer consumer;

	HashMap<String, MessageConsumer> consumerMap;
	HashMap<String, MessageProducer> producerMap;;
	Connection connection;

	/**
	 * 城市端使用的初始化。 城市端和中心服务端的差别在余中心端的服务连接多个Queue
	 * 
	 * @param listener
	 * @throws JMSException
	 * @throws IOException 
	 */
	public void initcity(MessageListener listener) throws JMSException, IOException {
		
		
		
		Properties p = new Properties();
		p.load(MQService.class.getResourceAsStream("/mq.properties"));
		
		String url = p.getProperty("mq.url");
		String user = p.getProperty("mq.user");
		String password = p.getProperty("mq.password");
		String citycode = p.getProperty("mq.citycode");
		logger.info("开始连接ActiveMQ");
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				user, password, url);
		connection = connectionFactory.createConnection();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue inQueue = session.createQueue(citycode + ".tocity");// 接收队列
		Queue outQueue = session.createQueue(citycode + ".fromcity");// 发送队列
		this.consumer = session.createConsumer(inQueue);
		this.consumer.setMessageListener(listener);
		this.pro = session.createProducer(outQueue);
		connection.start();
	}

	/**
	 * 服务端的初始化 城市端和中心服务端的差别在余中心端的服务连接多个Queue
	 * 
	 * @param listener
	 * @throws JMSException
	 */
	public void init(MessageListener listener) throws JMSException {

		ResourceBundle rb = ResourceBundle.getBundle("mq");
		String url = rb.getString("mq.url");
		String user = rb.getString("mq.user");
		String password = rb.getString("mq.password");
		String cities = rb.getString("mq.cities");
		String[] cityes = cities.split(",");
		List<String> list_citycode = new ArrayList<>();
		for (String c : cityes) {
			String code = rb.getString("mq.code." + c);
			if (code != null) {
				list_citycode.add(code);
			}
		}

		logger.info("开始连接ActiveMQ");
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				user, password, url);
		connection = connectionFactory.createConnection();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		consumerMap = new HashMap<>();
		producerMap = new HashMap<>();
		for (String code : list_citycode) {
			Queue inQueue = session.createQueue(code + ".fromcity");// 接收队列
			Queue outQueue = session.createQueue(code + ".tocity");// 发送队列
			MessageConsumer c = session.createConsumer(inQueue);
			consumerMap.put(code, c);
			producerMap.put(code, session.createProducer(outQueue));
			c.setMessageListener(listener);
		}
		connection.start();
		logger.info("连接ActiveMQ成功");
	}

	@Deprecated
	public void init(String user, String password, String url, String inqueue,
			String outqueue, MessageListener listener) throws JMSException {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				user, password, url);
		logger.info("MQ inited OKEY");
		connection = connectionFactory.createConnection();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		javax.jms.Queue indestination = session.createQueue(inqueue);

		Queue outdestination = session.createQueue(outqueue);

		pro = session.createProducer(outdestination);

		consumer = session.createConsumer(indestination);

		consumer.setMessageListener(listener);
		connection.start();

	}

	/**
	 * 发送消息
	 * 
	 * @param citycode
	 *            城市代码
	 * @param msg
	 */
	public void sendMsg(String citycode, MQMessage msg) {
		try {
			logger.debug("session：" + session);
			BytesMessage bmsg = session.createBytesMessage();
			logger.debug("sendMsg(MQMessage) - " + msg.toString());
			bmsg = msg.encode(bmsg);
			producerMap.get(citycode).send(bmsg);
		} catch (JMSException e) {
			logger.error("sendMsg(MQMessage)", e);
		}
	}

	/**
	 * 用于城市端往中心端发送消息
	 */
	public void sendMsg(MQMessage msg) {
		try {
			BytesMessage bmsg = session.createBytesMessage();
			if (logger.isDebugEnabled()) {
				logger.debug("sendMsg(MQMessage) - " + msg.toString()); //$NON-NLS-1$
			}
			bmsg = msg.encode(bmsg);
			pro.send(bmsg);
		} catch (JMSException e) {

			logger.error("sendMsg(MQMessage)", e); //$NON-NLS-1$
		}

	}

	public void close() {
		try {

			// pro.close();
			// consumer.close();
			Set<Entry<String, MessageConsumer>> cs = consumerMap.entrySet();
			for (Iterator<Entry<String, MessageConsumer>> iterator = cs
					.iterator(); iterator.hasNext();) {
				Entry<String, MessageConsumer> entry = (Entry<String, MessageConsumer>) iterator
						.next();
				entry.getValue().close();
			}
			Set<Entry<String, MessageProducer>> ps = producerMap.entrySet();

			for (Iterator<Entry<String, MessageProducer>> iterator = ps
					.iterator(); iterator.hasNext();) {
				Entry<String, MessageProducer> entry = (Entry<String, MessageProducer>) iterator
						.next();
				entry.getValue().close();

			}
			session.close();
			connection.close();
		} catch (JMSException e) {
			logger.error("close()", e); //$NON-NLS-1$
		}
	}

}
