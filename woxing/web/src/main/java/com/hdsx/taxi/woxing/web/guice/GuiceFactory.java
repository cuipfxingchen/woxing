package com.hdsx.taxi.woxing.web.guice;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.hdsx.taxi.woxing.order.IOrderService;

public class GuiceFactory {

	@Inject
	static Provider<IOrderService> orderServiceProvoider;
	@Inject
	static IOrderService os;

	public static IOrderService getInstance() {
		return orderServiceProvoider.get();
	}

	public static IOrderService getInstance2() {
		return os;
	}

}
