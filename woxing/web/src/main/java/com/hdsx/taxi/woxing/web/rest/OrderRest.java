package com.hdsx.taxi.woxing.web.rest;

import java.util.List;

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

	@Inject
	OrderMapper ordermapper;
	@Inject
	IOrderService orderservice;

	/**
	 * 提交订单
	 * @param order
	 * @return
	 */
	@Path("/1")
	@POST
	@Produces("application/json;charset=UTF-8")
	public RestBean submit(@Form Order order) {
		RestBean<Integer> r = new RestBean<>();
		String success="成功";
		String fail = "失败";
		boolean operResult=true;
		//*业务逻辑开始
		r.setResult(orderservice.submit(order));
		
		
		//业务逻辑结束
		if(operResult){
			r.setState(RestBean.SUCESSCODE);
			r.setMsg(success);
		}else{
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
	@Path("/2/customid")
	@GET
	@Produces("application/json;charset=UTF-8")
	public RestBean getHistoryOrder(@PathParam("customid") String customid){
		RestBean<List<Order>> r = new RestBean<>();
		String success = "成功";
		String fail = "失败";
		boolean operResult = false;
		//业务逻辑开始
		r.setResult(orderservice.getHistoryOrder(customid));
		operResult = true;

		//业务逻辑结束
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
	 * 查询预约订单
	 * 
	 * @param customid
	 * @return
	 */
	@Path("/3/customid")
	@GET
	@Produces("application/json;charset=UTF-8")
	public RestBean getReservationOrder(@PathParam("customid") String customid){
		RestBean<List<Order>> r = new RestBean<>();
		String success = "成功";
		String fail = "失败";
		boolean operResult = false;
		//业务逻辑开始
		//r.setResult();
		r.setResult(orderservice.getReservationOrder(customid));

		operResult = true;

		//业务逻辑结束
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
	 * 取消订单
	 * 
	 * @param orderid
	 * @return
	 */
	@Path("/4/orderid")
	@GET
	@Produces("application/json;charset=UTF-8")
	public RestBean cancelOrder(@PathParam("orderid") long orderid){
		RestBean<Boolean> r = new RestBean<>();
		String success = "成功";
		String fail = "失败";
		boolean operResult = false;
		//业务逻辑开始
		r.setResult(new Boolean(orderservice.cancelOrder(orderid) ));
		operResult = true;

		//业务逻辑结束
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
	@Path("/5/orderid")
	@GET
	@Produces("application/json;charset=UTF-8")
	public RestBean queryCarInfoByOrder(@PathParam("orderid") long orderid){
		RestBean<CarInfo> r = new RestBean<>();
		String success = "成功";
		String fail = "失败";
		boolean operResult = false;
		//业务逻辑开始
		r.setResult( orderservice.queryCarInfoByOrder(orderid));
		operResult = true;

		//业务逻辑结束
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
	 * @param order
	 * @return
	 */
	@Path("/6")
	@GET
	@Produces("application/json;charset=UTF-8")
	public RestBean update(@Form Order order){		
		RestBean<Boolean> r = new RestBean<>();
		String success = "成功";
		String fail = "失败";
		boolean operResult = false;
		//业务逻辑开始
		r.setResult(new Boolean(orderservice.update(order)));
		operResult = true;

		//业务逻辑结束
		if (operResult) {
			r.setState(RestBean.SUCESSCODE);
			r.setMsg(success);
		} else {
			r.setState(RestBean.FAILCODE);
			r.setMsg(fail);
		}
		return r;
		
	}

}
