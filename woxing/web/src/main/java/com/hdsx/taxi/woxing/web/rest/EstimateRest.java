package com.hdsx.taxi.woxing.web.rest;

import java.util.List;

import org.jboss.resteasy.annotations.Form;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.hdsx.taxi.woxing.bean.Estimate;
import com.hdsx.taxi.woxing.web.rest.bean.RestBean;
import com.hdsx.taxi.woxing.web.service.EstimateService;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * 评价相关服务
 * @author cuipengfei
 *
 */
@Path("/rest/est")
public class EstimateRest {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(EstimateRest.class);
	
	@Inject
	EstimateService estimateService ;

	/* 查询评论 */
	@GET
	@Path("/1/{estimateId}")
	@Produces("application/json;charset=UTF-8")
	public RestBean getEstimateById(@PathParam("estimateId") long estimateId) {
		RestBean<Estimate> re = new RestBean<>();
		Estimate result=estimateService.getEstimateById(estimateId);
		if(result==null){
			re.setState(201);
			re.setMsg("没有找到评论");
		}else{
			re.setResult(result);
			re.setMsg("查询到评论");
		}
		return re;

	}

	/* 新建评论 */
	@POST
	@Path("/2")
	@Produces("application/json;charset=UTF-8")
	public RestBean createEstimate(@Form Estimate est) {
		RestBean<String> re = new RestBean<>();
		Estimate est1=estimateService.getEstimateById(est.getOrderId());
		if(est1==null){
			if(estimateService.createEstimate(est)){
				re.setMsg("创建评论成功");
				logger.info("新建评价成功："+est.toString());
			}else{
				re.setState(201);
				re.setMsg("创建评论失败");
				logger.info("新建评价失败："+est.toString());
			}
		}else{
			if(estimateService.updateEstimate(est)){
				re.setMsg("修改评论成功");
				logger.info("修改评价成功："+est.toString());
			}else{
				re.setState(201);
				re.setMsg("修改评论失败");
				logger.info("修改评价失败："+est.toString());
			}
		}
		
		return re;

	}

	/* 修改评论 */
	@POST
	@Path("/3")
	@Produces("application/json;charset=UTF-8")
	public RestBean updateEstimate(@Form Estimate est) {
		RestBean<Estimate> re = new RestBean<>();
		if(estimateService.updateEstimate(est)){
			re.setMsg("创建评论成功");
		}else{
			re.setState(201);
			re.setMsg("创建评论失败");
		}
		return re;

	}

	/* 删除评论 */
	@GET
	@Path("/4/{estimateId}")
	@Produces("application/json;charset=UTF-8")
	public RestBean deleteEstimate(@PathParam("estimateId") long estimateId) {
		RestBean<String> re = new RestBean<>();
		if(estimateService.deleteEstimate(estimateId)){
			re.setMsg("删除评论成功");
		}else{
			re.setState(201);
			re.setMsg("删除评论失败");
		}
		return re;

	}
}
