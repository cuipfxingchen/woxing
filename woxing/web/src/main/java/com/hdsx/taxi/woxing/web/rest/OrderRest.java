package com.hdsx.taxi.woxing.web.rest;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.resteasy.annotations.Form;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.hdsx.taxi.woxing.bean.CarInfo;
import com.hdsx.taxi.woxing.bean.Order;
import com.hdsx.taxi.woxing.bean.util.coor.EvilTransform;
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
		RestBean<Long> r = new RestBean<>();
		String success = "成功";
		String fail = "失败";
		boolean operResult = false;
		// *业务逻辑开始
		// ordermapper.insert(order);
		
		//产生订单Id
		long orderId=new Date().getTime();
		order.setOrderId(orderId);
		double[] wg84_create=EvilTransform.GCJ02ToWGS84(order.getCreateLon(), order.getCreateLat());
		double[] wg84_on=EvilTransform.GCJ02ToWGS84(order.getGetOnLon(), order.getGetOnLat());
		double[] wg84_off=EvilTransform.GCJ02ToWGS84(order.getGetOffLon(), order.getGetOffLat());
		order.setGetOnLon(wg84_on[0]);
		order.setGetOnLat(wg84_on[1]);
		order.setGetOffLon(wg84_off[0]);
		order.setGetOffLat(wg84_off[1]);
		order.setCreateLon(wg84_create[0]);
		order.setCreateLat(wg84_create[1]);
		int rtn = orderservice.submit(order);
		if (1 == rtn)
			operResult = true;

		// 业务逻辑结束
		if (operResult) {
			r.setState(RestBean.SUCESSCODE);
			r.setResult(orderId);
			r.setMsg(success);
			logger.info("下单"+success+":"+order.toString());
		} else {
			r.setState(RestBean.FAILCODE);
			r.setMsg(fail);
			logger.info("下单"+fail+":"+order.toString());
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
		RestBean<List<Order>> r = new RestBean<>();
		String success = "成功";
		String fail = "没有订单";
		boolean operResult = false;
		// 业务逻辑开始
		List<Order> rtn = orderservice.getHistoryOrder(customid);
		if (rtn != null) {
			for (Order order : rtn) {
				double[] gd_on=EvilTransform.WGS84ToGCJ02(order.getGetOnLon(), order.getGetOnLat());
				double[] gd_off=EvilTransform.WGS84ToGCJ02(order.getGetOffLon(), order.getGetOffLat());
				order.setGetOnLon(gd_on[0]);
				order.setGetOnLat(gd_on[1]);
				order.setGetOffLon(gd_off[0]);
				order.setGetOffLat(gd_off[1]);
			}
			r.setResult(rtn);
			operResult = true;
		}
		// 业务逻辑结束
		if (operResult) {
			r.setState(RestBean.SUCESSCODE);
			r.setMsg(success);
			logger.info("查询"+customid+"历史订单："+rtn.toString());
		} else {
			r.setState(RestBean.FAILCODE);
			r.setMsg(fail);
			logger.info("查询"+customid+"历史订单："+fail);
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
		
		RestBean<List<Order>> r = new RestBean<>();
		String success = "成功";
		String fail = "失败";
		boolean operResult = false;
		// 业务逻辑开始
		// r.setResult();
		List<Order> list=orderservice.getReservationOrder(customid);
		for (Order order : list) {
			double[] gd_on=EvilTransform.WGS84ToGCJ02(order.getGetOnLon(), order.getGetOnLat());
			double[] gd_off=EvilTransform.WGS84ToGCJ02(order.getGetOffLon(), order.getGetOffLat());
			order.setGetOnLon(gd_on[0]);
			order.setGetOnLat(gd_on[1]);
			order.setGetOffLon(gd_off[0]);
			order.setGetOffLat(gd_off[1]);
		}
		r.setResult(list);

		operResult = true;

		// 业务逻辑结束
		if (operResult) {
			r.setState(RestBean.SUCESSCODE);
			r.setMsg(success);
			logger.info("查询"+customid+"预约订单："+list.toString());
		} else {
			r.setState(RestBean.FAILCODE);
			r.setMsg(fail);
			logger.info("查询"+customid+"预约订单："+fail);
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
	@Path("/4/{orderId}/{reason}/{customid}")
	@GET
	@Produces("application/json;charset=UTF-8")
	public RestBean cancelOrder(@PathParam("orderId") long orderid,
			@PathParam("reason") byte reason,
			@PathParam("customid") String customid) {
		RestBean<Byte> r = new RestBean<>();
		String success = "成功";
		String fail = "失败";
		boolean operResult = false;
		// 业务逻辑开始
		r.setResult(orderservice.cancelOrderByPassenger(orderid, reason,
				customid));
		operResult = true;

		// 业务逻辑结束
		if (operResult) {
			r.setState(RestBean.SUCESSCODE);
			r.setMsg(success);
			logger.info("取消"+customid+"订单："+success);
		} else {
			r.setState(RestBean.FAILCODE);
			r.setMsg(fail);
			logger.info("取消"+customid+"订单："+fail);
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
		CarInfo carInfo =orderservice.queryCarInfoByOrder(orderid);
		double[] gd=EvilTransform.WGS84ToGCJ02(carInfo.getLon(), carInfo.getLat());
		carInfo.setLon(gd[0]);
		carInfo.setLat(gd[1]);
		r.setResult(carInfo);
		operResult = true;

		// 业务逻辑结束
		if (operResult) {
			r.setState(RestBean.SUCESSCODE);
			r.setMsg(success);
			logger.info("查询"+orderid+"订单号对应的车辆："+carInfo.toString());
		} else {
			r.setState(RestBean.FAILCODE);
			r.setMsg(fail);
			logger.info("查询"+orderid+"订单号对应的车辆："+fail);
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
		logger.info("根据订单号"+orderid+"查询订单状态："+r.getState());
		return r;
	}

	/**
	 * 乘客上车
	 * @param orderId
	 * @param lon
	 * @param lat
	 * @param citycode
	 * @param customid
	 * @return
	 */
	@Path("/8/{orderId}/{lon}/{lat}/{customid}/{citycode}")
	@GET
	@Produces("application/json;charset=UTF-8")
	public RestBean getOnTaxi(@PathParam("orderId") String orderId,
			@PathParam("lon") String lon, @PathParam("lat") String lat,
			@PathParam("citycode") String citycode,
			@PathParam("customid") String customid) {
		RestBean<Byte> r = new RestBean<>();
		long orderid = Long.parseLong(orderId);
		double[] wg84=EvilTransform.GCJ02ToWGS84(Double.parseDouble(lon), Double.parseDouble(lat));
		boolean result =true;
		r.setResult(orderservice.passengerGeton(orderid,
				wg84[0], wg84[1], customid,
				citycode));
		if (result) {
			r.setState(RestBean.SUCESSCODE);
			r.setMsg("成功");
			logger.info("乘客："+customid+"主动上车成功状态："+r.getState());
		} else {
			r.setState(RestBean.FAILCODE);
			r.setMsg("失败");
			logger.info("乘客："+customid+"主动上车失败状态："+r.getState());
		}
		return r;
	}

	/**
	 * 乘客位置上传
	 * @param orderId
	 * @param lon
	 * @param lat
	 * @param citycode
	 * @param customid
	 * @return
	 */
	@Path("/9/{orderId}/{lon}/{lat}/{customid}/{citycode}")
	@GET
	@Produces("application/json;charset=UTF-8")
	public RestBean upPassegerSite(@PathParam("orderId") String orderId,
			@PathParam("lon") String lon, @PathParam("lat") String lat,
			@PathParam("citycode") String citycode,
			@PathParam("customid") String customid) {
		RestBean r = new RestBean<>();
		long orderid = Long.parseLong(orderId);
		double[] wg84=EvilTransform.GCJ02ToWGS84(Double.parseDouble(lon), Double.parseDouble(lat));
		boolean result = orderservice.upPassengerSite(orderid,
				wg84[0], wg84[1], customid,
				citycode);
		if (result) {
			r.setState(RestBean.SUCESSCODE);
		} else {
			r.setState(RestBean.FAILCODE);
		}
		return r;
	}
	
	/**
	 * 付款成功
	 * @param orderId
	 * @param type
	 * @param desc
	 * @param citycode
	 * @param customid
	 * @return
	 */
	@Path("/10/{orderId}/{type}/{desc}/{customid}/{citycode}")
	@GET
	@Produces("application/json;charset=UTF-8")
	public RestBean payMoney(@PathParam("orderId") String orderId,
			@PathParam("type") String type, @PathParam("desc") String desc,
			@PathParam("citycode") String citycode,
			@PathParam("customid") String customid) {
		RestBean r = new RestBean<>();
		long orderid = Long.parseLong(orderId);
		boolean result = orderservice.payMoney(orderid, Byte.parseByte(type), desc, customid, citycode);
		if (result) {
			r.setState(RestBean.SUCESSCODE);
			logger.info("乘客："+customid+"付款成功");
		} else {
			r.setState(RestBean.FAILCODE);
			logger.info("乘客："+customid+"付款失败");
		}
		return r;
	}
}
