package com.hdsx.taxi.woxing.web.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.inject.Inject;
import com.hdsx.taxi.woxing.bean.Order;
import com.hdsx.taxi.woxing.dao.OrderMapper;
import com.hdsx.taxi.woxing.order.IOrderService;
import com.hdsx.taxi.woxing.web.rest.bean.RestBean;

@Path("/rest/order")
public class OrderRest {

	@Inject
	OrderMapper ordermapper;
	@Inject
	IOrderService orderservice;

	
	
	/**
	 * 提交订单
	 * @param order
	 * @return
	 */
	@Path("/1/")
	@POST
	@Produces("application/json;charset=UTF-8")
	public RestBean submit(Order order) {
		RestBean<Integer> r = new RestBean<>();
		r.setResult(orderservice.submit(order));
		return r;
	}
}
