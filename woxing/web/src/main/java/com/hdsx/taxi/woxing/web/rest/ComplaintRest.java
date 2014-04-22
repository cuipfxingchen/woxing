package com.hdsx.taxi.woxing.web.rest;

import java.util.Date;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.resteasy.annotations.Form;
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
@Path("/rest/com/{customid}")
public class ComplaintRest {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(ComplaintRest.class);

	@Inject
	ComplaintService complaintService;

	
	
	/**
	 * 保存投诉信息
	 * @param orderId
	 * @param type
	 * @param content
	 * @param mobile
	 * @param name
	 * @param citycode
	 * @param customid
	 * @return
	 */
	@POST
	@Path("/1")
	@Produces("application/json;charset=UTF-8")
	public RestBean saveComplain(@Form Complaint cp) {

		// 默认数据
		cp.setState("1");
		cp.setComplainDate(DateFormatUtil.date2MySQLDateTimeString(new Date()));
		RestBean bean = new RestBean<>();
		// 业务处理
		if (complaintService.saveComplain(cp)) {
			bean.setMsg("提交成功");
		} else {
			bean.setState(200);
			bean.setMsg("提交失败");
		}
		return bean;

	}

}
