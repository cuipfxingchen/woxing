package com.hdsx.taxi.woxing.web.listener;

import javax.jms.JMSException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hdsx.taxi.woxing.mqutil.MQService;
import com.hdsx.taxi.woxing.web.mq.MqMessageListener;

public class MQListener implements ServletContextListener {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(MQListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent event) {

		MQService.getInstance().close();

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {

			MQService.getInstance().init(new MqMessageListener());

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
