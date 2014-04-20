package com.hdsx.taxi.woxing.web.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.inject.Inject;
import com.hdsx.taxi.woxing.bean.Order;
import com.hdsx.taxi.woxing.dao.OrderMapper;

@Path("/rest/order")
public class OrderRest {

	@Inject
	OrderMapper ordermapper;

	@GET
	@Path("/getid/{id}")
	@Produces("application/json;charset=UTF-8")
	public List<Order> getTest(@PathParam("id") String id) {
		Order o = new Order();

		return ordermapper.getlist();

	}

}
