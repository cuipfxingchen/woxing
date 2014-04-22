package com.hdsx.taxi.woxing.web.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.hdsx.taxi.woxing.bean.CarInfo;
import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg2001;
import com.hdsx.taxi.woxing.mqutil.msgpool.MQMsgPool;
import com.hdsx.taxi.woxing.web.rest.bean.RestBean;
import com.hdsx.taxi.woxing.web.service.CityService;
import com.hdsx.taxi.woxing.web.service.LocationService;

/**
 * 位置相关的服务
 * 
 * @author Steven
 * 
 */
@Path("/rest/loc")
public class LocationRest {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(LocationRest.class);

	@Inject
	LocationService ls;
	@Inject
	CityService cityService;
	
	/**
	 * 查询周边车辆信息
	 * @param x
	 * @param y
	 * @param r
	 * @param citycode
	 * @param customid
	 * @return
	 */
	@GET
	@Path("/1/{x}/{y}/{r}/{citycode}")
	@Produces("application/json;charset=UTF-8")
	public RestBean getCarByDistance(@PathParam("x") double x,
			@PathParam("y") double y, @PathParam("r") short r,
			@PathParam("citycode") String citycode,
			@PathParam("customid") String customid) {
//		RestBean<List> re = new RestBean<>();
//
//		logger.debug("Customid=:" + customid);
//		List<CarInfo> l = ls.findCars(x, y, r, citycode, customid);
//		// List<CarInfo> l = new ArrayList<>();
//		if (l.size() == 0) {
//			re.setState(200);
//			re.setMsg("没有找到车辆");
//		} else {
//			re.setResult(l);
//		}
//		return re;
		
		RestBean<List> rb = new RestBean<>();
		String success = "成功";
		String fail = "没有找到车辆";
		boolean operResult = false;
		//业务逻辑开始
		logger.debug("Customid=:" + customid);
		List<CarInfo> l = ls.findCars(x, y, r, citycode, customid);
		if(l.size()>0)
		{
			rb.setResult(l);
			operResult = true;
		}	
		//业务逻辑结束
		if (operResult) {
			rb.setState(RestBean.SUCESSCODE);
			rb.setMsg(success);
		} else {
			rb.setState(RestBean.FAILCODE);
			rb.setMsg(fail);
		}
		return rb;
	}

	/**
	 * 根据订单id、城市编码、用户查询对应车辆信息
	 * @param orderId 订单id
	 * @param citycode 城市编码
	 * @param customid 用户名
	 * @return
	 */
	@GET
	@Path("/2/{orderId}/{citycode}")
	@Produces("application/json;charset=UTF-8")
	public RestBean getCarInfoByCarNum(@PathParam("orderId") String orderId,
			@PathParam("citycode") String citycode,
			@PathParam("customid") String customid) {
		RestBean<CarInfo> r = new RestBean<>();
		String success = "成功";
		String fail = "失败";
		boolean operResult = false;
		//业务逻辑开始
		CarInfo c = ls.getCarInfoByCarNum(citycode, customid,Long.parseLong( orderId));
		r.setResult(c);

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
	 * 通过城市代码或者城市名称查询是否有小费
	 * @param ct
	 * @param cn
	 * @return
	 */
	@GET
	@Path("/3/code/name")
	@Produces("text/plain;charset=utf-8")
	public RestBean getCheckTip(@PathParam("code") String ct,
			@PathParam("name") String cn) {
		
		RestBean<String> bean = new RestBean<>();
		// 通过城市编码
		if (ct != null && !"".equals(ct)) {
			if (cityService.findCityByCode(ct)) {
				bean.setState(1);
				bean.setMsg("有小费");
			}else{
				bean.setState(0);
				bean.setMsg("没有小费");
			}
		}else{
			bean.setState(200);
			bean.setMsg("城市代码为空");
		}

		// 通过城市名称
		if (cn != null && !"".equals(cn)) {
			if (cityService.findCityByCityName(cn)) {
				bean.setState(1);
				bean.setMsg("有小费");
			}else{
				bean.setState(0);
				bean.setMsg("没有小费");
			}
		}else{
			bean.setState(200);
			bean.setMsg("城市名称为空");
		}

		return bean;
	}
	
	
	
	@Inject
	MQMsgPool pool;

	@GET
	@Path("/testcache")
	@Produces("application/json;charset=UTF-8")
	public RestBean testCache() {
		MQMsg2001 m = new MQMsg2001("adf");
		m.setLat(2323);
		m.setLon(23423);
		pool.put(m);
		RestBean<MQMsg2001> r = new RestBean<MQMsg2001>();
		r.setResult(m);
		return r;

	}

}
