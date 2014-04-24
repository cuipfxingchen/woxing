package com.hdsx.taxi.woxing.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.resteasy.annotations.Form;

import com.hdsx.taxi.woxing.bean.Order;
import com.hdsx.taxi.woxing.web.rest.bean.RestBean;

@Path("/rest/test")
public class TestRest {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(TestRest.class);

	
	
	@POST
	@Path("/1")
	@Produces("application/json;charset=UTF-8")
	public RestBean Test(@Form Order order){
		RestBean<Integer> r = new RestBean<>();
		logger.info("收到消息ok");
		return r;
	}
	
	@POST
	@Path("/2")
	@Produces("application/json;charset=UTF-8")
	public RestBean Test2(@FormParam("customid") String ss){
		RestBean<String> r = new RestBean<>();
		logger.info("收到消息ok"+ss);
		return r;
	}
}
