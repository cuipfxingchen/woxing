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
import com.hdsx.taxi.woxing.bean.Index;
import com.hdsx.taxi.woxing.bean.Station;
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
	 * 
	 * @param x
	 * @param y
	 * @param r
	 * @param citycode
	 * @param customid
	 * @return
	 */
	@GET
	@Path("/1/{x}/{y}/{r}/{citycode}/{customid}")
	@Produces("application/json;charset=UTF-8")
	public RestBean getCarByDistance(@PathParam("x") double x,
			@PathParam("y") double y, @PathParam("r") short r,
			@PathParam("citycode") String citycode,
			@PathParam("customid") String customid) {
		// RestBean<List> re = new RestBean<>();
		//
		// logger.debug("Customid=:" + customid);
		// List<CarInfo> l = ls.findCars(x, y, r, citycode, customid);
		// // List<CarInfo> l = new ArrayList<>();
		// if (l.size() == 0) {
		// re.setState(200);
		// re.setMsg("没有找到车辆");
		// } else {
		// re.setResult(l);
		// }
		// return re;

		RestBean<List> rb = new RestBean<>();
		String success = "成功";
		String fail = "没有找到车辆";
		boolean operResult = false;
		// 业务逻辑开始
		logger.debug("Customid=:" + customid);
		List<CarInfo> l = ls.findCars(x, y, r, citycode, customid);
		if (l.size() > 0) {
			rb.setResult(l);
			operResult = true;
		}
		// 业务逻辑结束
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
	 * 
	 * @param orderId
	 *            订单id
	 * @param citycode
	 *            城市编码
	 * @param customid
	 *            用户名
	 * @return
	 */
	@GET
	@Path("/2/{orderId}/{citycode}/{customid}")
	@Produces("application/json;charset=UTF-8")
	public RestBean getCarInfoByCarNum(@PathParam("orderId") String orderId,
			@PathParam("citycode") String citycode,
			@PathParam("customid") String customid) {
		RestBean<CarInfo> r = new RestBean<>();
		String success = "成功";
		String fail = "失败";
		boolean operResult = false;
		// 业务逻辑开始
		CarInfo c = ls.getCarInfoByCarNum(citycode, customid,
				Long.parseLong(orderId));
		r.setResult(c);

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
	 * 通过城市代码或者城市名称查询是否有小费
	 * 
	 * @param ct
	 * @param cn
	 * @return
	 */
	@GET
	@Path("/3/{code}/{name}")
	@Produces("application/json;charset=utf-8")
	public RestBean getCheckTip(@PathParam("code") String ct,
			@PathParam("name") String cn) {

		RestBean<String> bean = new RestBean<>();
		// 通过城市编码
		if (ct != null && !"".equals(ct)) {
			if (cityService.findCityByCode(ct)) {
				bean.setState(1);
				bean.setMsg("有小费");
			} else {
				bean.setState(0);
				bean.setMsg("没有小费");
			}
		} else {
			bean.setState(RestBean.FAILCODE);
			bean.setMsg("城市代码为空");
		}

		// 通过城市名称
		if (cn != null && !"".equals(cn)) {
			if (cityService.findCityByCityName(cn)) {
				bean.setState(1);
				bean.setMsg("有小费");
			} else {
				bean.setState(0);
				bean.setMsg("没有小费");
			}
		} else {
			bean.setState(RestBean.FAILCODE);
			bean.setMsg("城市名称为空");
		}

		return bean;
	}

	/**
	 * 查询打车指数
	 * 
	 * @param x
	 * @param y
	 * @param citycode
	 * @param customid
	 * @return
	 */
	@GET
	@Path("/4/{x}/{y}/{citycode}/{customid}")
	@Produces("application/json;charset=utf-8")
	public RestBean getCurLocationIndex(@PathParam("x") String x,
			@PathParam("y") String y, @PathParam("citycode") String citycode,
			@PathParam("customid") String customid) {
		RestBean<Integer> bean = new RestBean<>();
		double lat = 0.0, lon = 0.0;
		try {
			lon = Double.parseDouble(x);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			bean.setState(RestBean.FAILCODE);
			bean.setMsg("经度解析错误");
		}
		try {
			lat = Double.parseDouble(y);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			bean.setState(RestBean.FAILCODE);
			bean.setMsg("纬度解析错误");
		}

		int rd = ls.getCurLocationIndex(lon, lat, citycode, customid);
		bean.setResult(rd);
		return bean;
	}

	/**
	 * 查询打车热点区域
	 * 
	 * @param x
	 * @param y
	 * @param dx
	 * @param dy
	 * @param token
	 * @return
	 */
	@GET
	@Path("/5/{x}/{y}/{dx}/{dy}/{citycode}/{customid}")
	@Produces("application/json;charset=utf-8")
	public RestBean<List<Index>> getTaxiIndex(@PathParam("x") double x,
			@PathParam("y") double y, @PathParam("dx") double dx,
			@PathParam("dy") double dy, @PathParam("citycode") String citycode,
			@PathParam("customid") String customid) {
		List<Index> list = ls.getTaxiIndex(x, y, dx, dy, citycode, customid);
		RestBean<List<Index>> bean = new RestBean<>();
		if (list != null && list.size() > 0) {
			bean.setMsg("查询成功");
			bean.setResult(list);
		} else {
			bean.setState(RestBean.FAILCODE);
			bean.setMsg("查询失败");
		}

		return bean;
	}

	/**
	 * 查询周边扬招站
	 * 
	 * @param x
	 * @param y
	 * @param r
	 * @param citycode
	 * @param customid
	 * @return
	 */
	@GET
	@Path("/6/{x}/{y}/{r}/{citycode}/{customid}")
	@Produces("application/json;charset=utf-8")
	public RestBean<List<Station>> getStation(@PathParam("x") String x,
			@PathParam("y") String y, @PathParam("r") String r,
			@PathParam("citycode") String citycode,
			@PathParam("customid") String customid) {

		// 调用内容
		double lat = 0.0;
		double lon = 0.0;
		int ir = 500;

		RestBean<List<Station>> bean = new RestBean<>();
		// 转化
		try {
			lat = Double.parseDouble(y);
		} catch (Exception e) {
			e.printStackTrace();
			bean.setState(RestBean.FAILCODE);
			bean.setMsg("纬度转换异常");
		}

		try {
			lon = Double.parseDouble(x);
		} catch (Exception e) {
			e.printStackTrace();
			bean.setState(RestBean.FAILCODE);
			bean.setMsg("经度转换异常");
		}

		// 进行 int 转换
		try {
			ir = Integer.parseInt(r);
		} catch (Exception e) {
			e.printStackTrace();
			bean.setState(RestBean.FAILCODE);
			bean.setMsg("距离转换异常");
		}

		List<Station> list = ls.getStation(lat, lon, ir, citycode, customid);

		if (list != null && list.size() > 0) {
			bean.setMsg("查询成功");
			bean.setResult(list);
		} else {
			bean.setState(RestBean.FAILCODE);
			bean.setMsg("查询失败");
		}

		return bean;
	}

	/**
	 * 通过区域查询扬招站
	 * 
	 * @param x
	 * @param dx
	 * @param y
	 * @param dy
	 * @param citycode
	 * @param customid
	 * @return
	 */
	@GET
	@Path("/7/{x}/{y}/{dx}/{dy}/{citycode}/{customid}")
	@Produces("application/json;charset=utf-8")
	public RestBean<List<Station>> getStationArea(@PathParam("x") String x,
			@PathParam("y") String y, @PathParam("dx") String dx,
			@PathParam("dy") String dy, @PathParam("citycode") String citycode,
			@PathParam("customid") String customid) {
		double lat = 0.0, xlon = 0.0, lon = 0.0, ylat = 0.0;
		RestBean<List<Station>> bean = new RestBean<>();
		try {
			lon = Double.parseDouble(x);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			bean.setState(RestBean.FAILCODE);
			bean.setMsg("经度转换异常");
		}
		try {
			lat = Double.parseDouble(y);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			bean.setState(RestBean.FAILCODE);
			bean.setMsg("纬度转换异常");
		}
		try {
			xlon = Double.parseDouble(dx);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			bean.setState(RestBean.FAILCODE);
			bean.setMsg("经度dx转换异常");
		}
		try {
			ylat = Double.parseDouble(dy);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			bean.setState(RestBean.FAILCODE);
			bean.setMsg("纬度dy转换异常");
		}
		List<Station> list = ls.getStationArea(lon, lat, xlon, ylat, citycode, customid);

		if (list != null && list.size() > 0) {
			bean.setMsg("查询成功");
			bean.setResult(list);
		} else {
			bean.setState(RestBean.FAILCODE);
			bean.setMsg("查询失败");
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
