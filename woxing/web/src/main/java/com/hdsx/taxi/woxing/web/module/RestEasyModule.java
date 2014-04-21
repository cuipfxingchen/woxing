package com.hdsx.taxi.woxing.web.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;
import com.hdsx.taxi.woxing.bean.util.CacheManagerUtil;
import com.hdsx.taxi.woxing.mqutil.msgpool.MQMsgPool;
import com.hdsx.taxi.woxing.order.IOrderService;
import com.hdsx.taxi.woxing.order.OrderPool;
import com.hdsx.taxi.woxing.order.OrderService;
import com.hdsx.taxi.woxing.web.rest.ComplaintRest;
import com.hdsx.taxi.woxing.web.rest.EstimateRest;
import com.hdsx.taxi.woxing.web.rest.LocationRest;
import com.hdsx.taxi.woxing.web.rest.OrderRest;
import com.hdsx.taxi.woxing.web.service.ComplaintService;
import com.hdsx.taxi.woxing.web.service.EstimateService;
import com.hdsx.taxi.woxing.web.service.LocationService;
import com.hdsx.taxi.woxing.xmpp.IXMPPService;
import com.hdsx.taxi.woxing.xmpp.impl.XMPPService;

public class RestEasyModule implements Module {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(RestEasyModule.class);

	@Override
	public void configure(Binder binder) {

		/**
		 * 绑定工具类
		 */
		binder.bind(CacheManagerUtil.class); // Ehcache管理器
		binder.bind(MQMsgPool.class).in(Singleton.class); // MQ消息池
		binder.bind(OrderPool.class).in(Singleton.class); // 订单服务池
		binder.bind(IXMPPService.class).to(XMPPService.class)
				.in(Singleton.class); // XMPPService

		/**
		 * 绑定服务
		 */

		binder.bind(LocationService.class); // 位置服务
		binder.bind(ComplaintService.class); // 投诉服务
		binder.bind(EstimateService.class); // 评价服务

		binder.bind(IOrderService.class).to(OrderService.class)
				.in(Singleton.class); // 订单服务
		/**
		 * RESTeasy
		 */
		binder.bind(OrderRest.class);
		binder.bind(LocationRest.class);
		binder.bind(ComplaintRest.class);
		binder.bind(EstimateRest.class);

	}

}
