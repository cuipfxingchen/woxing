package com.hdsx.taxi.woxing.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.resteasy.annotations.Form;

import com.google.inject.Inject;
import com.hdsx.taxi.woxing.bean.CarInfo;
import com.hdsx.taxi.woxing.bean.Order;
import com.hdsx.taxi.woxing.dao.OrderMapper;
import com.hdsx.taxi.woxing.order.IOrderService;
import com.hdsx.taxi.woxing.web.rest.bean.RestBean;

@Path("/rest/order")
public class OrderRest {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(OrderRest.class);

	@Inject
	OrderMapper ordermapper;
	@Inject
	IOrderService orderservice;

	/**
	 * 提交订单
	 * 
	 * @param order
	 * @return
	 */
	@Path("/1")
	@POST
	@Produces("application/json;charset=UTF-8")
	public RestBean submit(@Form Order order) {
		RestBean<Integer> r = new RestBean<>();
		String success = "成功";
		String fail = "失败";
		boolean operResult = false;
		// *业务逻辑开始
		// ordermapper.insert(order);
		if (logger.isDebugEnabled()) {
			logger.debug(
					"submit(Order) - {}", "order ----->" + order.toString()); //$NON-NLS-1$ //$NON-NLS-2$
		}
		int rtn = orderservice.submit(order);
		if (1 == rtn)
			operResult = true;

		// 业务逻辑结束
		if (operResult) {
			r.setState(RestBean.SUCESSCODE);
			r.setMsg(success);
		} else {
			r.setState(RestBean.FAILCODE);
			r.setMsg(fail);
		}

		return r;
	}

	/**
	 * 查询历史订单
	 * 
	 * @param customid
	 * @return
	 */
	@Path("/2/{customid}")
	@GET
	@Produces("application/json;charset=UTF-8")
	public RestBean getHistoryOrder(@PathParam("customid") String customid) {
		logger.info("getHistoryOrder(String) - start"); //$NON-NLS-1$

		RestBean<List<Order>> r = new RestBean<>();
		String success = "成功";
		String fail = "没有订单";
		boolean operResult = false;
		// 业务逻辑开始
		List<Order> rtn = orderservice.getHistoryOrder(customid);
		if (rtn != null) {
			r.setResult(rtn);
			operResult = true;
		}
		// 业务逻辑结束
		if (operResult) {
			r.setState(RestBean.SUCESSCODE);
			r.setMsg(success);
		} else {
			r.setState(RestBean.FAILCODE);
			r.setMsg(fail);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getHistoryOrder(String) - end"); //$NON-NLS-1$
		}
		return r;
	}

	/**
	 * 查询预约订单
	 * 
	 * @param customid
	 * @return
	 */
	@Path("/3/{customid}")
	@GET
	@Produces("application/json;charset=UTF-8")
	public RestBean getReservationOrder(@PathParam("customid") String customid) {
		logger.debug("getReservationOrder(String) - start"); //$NON-NLS-1$
		RestBean<List<Order>> r = new RestBean<>();
		String success = "成功";
		String fail = "失败";
		boolean operResult = false;
		// 业务逻辑开始
		// r.setResult();
		r.setResult(orderservice.getReservationOrder(customid));

		operResult = true;

		// 业务逻辑结束
		if (operResult) {
			r.setState(RestBean.SUCESSCODE);
			r.setMsg(success);
		} else {
			r.setState(RestBean.FAILCODE);
			r.setMsg(fail);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getReservationOrder(String) - end"); //$NON-NLS-1$
		}
		return r;
	}

	/**
	 * 取消订单
	 * 
	 * @param orderid
	 * @param reason
	 *            reason
	 * @return
	 */
	@Path("/4/{orderId}/{reason}")
	@GET
	@Produces("application/json;charset=UTF-8")
	public RestBean cancelOrder(@PathParam("orderId") long orderid,
			@PathParam("reason") byte reason) {
		RestBean<Boolean> r = new RestBean<>();
		String success = "成功";
		String fail = "失败";
		boolean operResult = false;
		// 业务逻辑开始
		r.setResult(orderservice.cancelOrderByPassenger(orderid, reason));
		operResult = true;

		// 业务逻辑结束
		if (operResult) {
			r.setState(RestBean.SUCESSCODE);
			r.setMsg(success);
		} else {
			r.setState(RestBean.FAILCODE);
			r.setMsg(fail);
		}
		return r;
	}

	/**
	 * 通过订单查询车辆信息
	 * 
	 * @param orderid
	 * @return
	 */
	@Path("/5/{orderid}")
	@GET
	@Produces("application/json;charset=UTF-8")
	public RestBean queryCarInfoByOrder(@PathParam("orderid") long orderid) {
		RestBean<CarInfo> r = new RestBean<>();
		String success = "成功";
		String fail = "失败";
		boolean operResult = false;
		// 业务逻辑开始
		r.setResult(orderservice.queryCarInfoByOrder(orderid));
		operResult = true;

		// 业务逻辑结束
		if (operResult) {
			r.setState(RestBean.SUCESSCODE);
			r.setMsg(success);
		} else {
			r.setState(RestBean.FAILCODE);
			r.setMsg(fail);
		}
		return r;
	}

	/**
	 * 更新订单，包括状态
	 * 
	 * @param order
	 * @return
	 */
	@Path("/6")
	@GET
	@Produces("application/json;charset=UTF-8")
	public RestBean update(@Form Order order) {
		RestBean<Boolean> r = new RestBean<>();
		String success = "成功";
		String fail = "失败";
		boolean operResult = false;
		// 业务逻辑开始
		r.setResult(new Boolean(orderservice.update(order)));
		operResult = true;

		// 业务逻辑结束
		if (operResult) {
			r.setState(RestBean.SUCESSCODE);
			r.setMsg(success);
		} else {
			r.setState(RestBean.FAILCODE);
			r.setMsg(fail);
		}
		return r;

	}

	/**
	 * 根据订单id查询订单状态
	 * 
	 * @param orderId
	 * @param citycode
	 * @param customid
	 * @return
	 */
	@Path("/7/{orderId}/{customid}/{citycode}")
	@GET
	@Produces("application/json;charset=UTF-8")
	public RestBean getOrderState(@PathParam("orderId") String orderId,
			@PathParam("citycode") String citycode,
			@PathParam("customid") String customid) {
		RestBean r = new RestBean<>();
		long orderid = Long.parseLong(orderId);
		r.setState(orderservice.getOrderState(orderid, customid, citycode));
		return r;
	}

	@Path("/8/{orderId}/{lon}/{lat}/{customid}/{citycode}")
	@GET
	@Produces("application/json;charset=UTF-8")
	public RestBean getOnTaxi(@PathParam("orderId") String orderId,
			@PathParam("lon") String lon,
			@PathParam("lat") String lat,
			@PathParam("citycode") String citycode,
			@PathParam("customid") String customid) {
		RestBean r = new RestBean<>();
		long orderid = Long.parseLong(orderId);
		boolean result=orderservice.passengerGeton(orderid, Double.parseDouble(lon), Double.parseDouble(lat), customid, citycode);
		if(result){
			r.setState(RestBean.SUCESSCODE);
		}else{
			r.setState(RestBean.FAILCODE);
		}
		return r;
	}
	
	@Path("/9/{orderId}/{lon}/{lat}/{customid}/{citycode}")
	@GET
	@Produces("application/json;charset=UTF-8")
	public RestBean upPassegerSite(@PathParam("orderId") String orderId,
			@PathParam("lon") String lon,
			@PathParam("lat") String lat,
			@PathParam("citycode") String citycode,
			@PathParam("customid") String customid) {
		RestBean r = new RestBean<>();
		long orderid = Long.parseLong(orderId);
		boolean result=orderservice.upPassengerSite(orderid, Double.parseDouble(lon), Double.parseDouble(lat), customid, citycode);
		if(result){
			r.setState(RestBean.SUCESSCODE);
		}else{
			r.setState(RestBean.FAILCODE);
		}
		return r;
	}
}
