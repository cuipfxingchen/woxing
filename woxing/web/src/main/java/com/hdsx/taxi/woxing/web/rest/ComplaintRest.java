package com.hdsx.taxi.woxing.web.rest;

import java.util.Date;
import java.util.UUID;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.resteasy.annotations.Form;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.hdsx.taxi.woxing.bean.Complaint;
import com.hdsx.taxi.woxing.bean.FeedBack;
import com.hdsx.taxi.woxing.mqutil.util.DateFormatUtil;
import com.hdsx.taxi.woxing.web.rest.bean.RestBean;
import com.hdsx.taxi.woxing.web.service.ComplaintService;

/**
 * 投诉相关的服务
 * 
 * @author cuipengfei
 * 
 */
@Path("/rest/com")
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
	 * 
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
			logger.info("投诉成功："+cp.toString());
		} else {
			bean.setState(200);
			bean.setMsg("提交失败");
			logger.info("投诉失败："+cp.toString());
		}
		return bean;

	}

	@POST
	@Path("/2")
	@Produces("application/json;charset=UTF-8")
	public RestBean saveFeedBack(@Form FeedBack feedBack) {

		RestBean bean = new RestBean<>();
		String s = UUID.randomUUID().toString();
		feedBack.setId(s.substring(0, 8) + s.substring(9, 13)
				+ s.substring(14, 18) + s.substring(19, 23) + s.substring(24));
		feedBack.setTime(new Date());
		// 业务处理
		if (complaintService.saveFeedBack(feedBack)) {
			bean.setMsg("提交成功");
			logger.info("意见反馈成功："+feedBack.toString());
		} else {
			bean.setState(200);
			bean.setMsg("提交失败");
			logger.info("意见反馈失败："+feedBack.toString());
		}
		return bean;

	}

}
