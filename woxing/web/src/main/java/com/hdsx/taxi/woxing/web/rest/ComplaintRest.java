package com.hdsx.taxi.woxing.web.rest;

import java.util.Date;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.hdsx.taxi.woxing.bean.Complaint;
import com.hdsx.taxi.woxing.mqutil.util.DateFormatUtil;
import com.hdsx.taxi.woxing.web.rest.bean.RestBean;
import com.hdsx.taxi.woxing.web.service.ComplaintService;

/**
 * 投诉相关的服务
 * 
 * @author cuipengfei
 * 
 */
@Path("/rest/2/{customid}")
public class ComplaintRest {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(ComplaintRest.class);

	@Inject
	ComplaintService complaintService;

	@POST
	@Path("/1")
	@Produces("application/json;charset=UTF-8")
	public RestBean saveComplain(@FormParam("orderid") String orderId,
			@FormParam("type") String type,
			@FormParam("content") String content,
			@FormParam("mobile") String mobile, @FormParam("name") String name,
			@PathParam("citycode") String citycode,
			@PathParam("customid") String customid) {

		// 填充上 stats 、时间
		Complaint cp = new Complaint();
		cp.setOrderId(orderId);
		cp.setType(type);
		cp.setContent(content);
		cp.setPassengerMobile(mobile);
		cp.setPassengerName(name);

		// 默认数据
		cp.setState("1");
		cp.setComplainDate(DateFormatUtil.date2MySQLDateTimeString(new Date()));
		RestBean bean = new RestBean<>();
		// 业务处理
		if (complaintService.saveComplain(cp, citycode, customid)) {
			bean.setMsg("提交成功");
		} else {
			bean.setState(200);
			bean.setMsg("提交失败");
		}
		return bean;

	}

}
