package com.hdsx.taxi.woxing.web.rest;

import java.util.List;

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
@Path("/rest/3/{customid}")
public class EstimateRest {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(EstimateRest.class);
	
	@Inject
	EstimateService estimateService ;

	/* 查询评论 */
	@GET
	@Path("/getEstimate/estimateId")
	@Produces("application/json;charset=UTF-8")
	public RestBean getEstimateById(@PathParam("estimateId") long estimateId) {
		RestBean<String> re = new RestBean<>();
		String result=estimateService.getEstimateById(estimateId);
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
	@Path("/createEstimate/orderId/userId/score/comment")
	@Produces("application/json;charset=UTF-8")
	public RestBean createEstimate(@FormParam("orderId") long orderId,
			@FormParam("userId") long userId, @FormParam("score") int score,
			@FormParam("comment") String comment) {
		RestBean<String> re = new RestBean<>();
		if(estimateService.createEstimate(orderId, userId, score, comment)){
			re.setMsg("创建评论成功");
		}else{
			re.setState(201);
			re.setMsg("创建评论失败");
		}
		return re;

	}

	/* 修改评论 */
	@GET
	@Path("/updateEstimate/estimateId/orderId/score/comment")
	@Produces("application/json;charset=UTF-8")
	public RestBean updateEstimate(@PathParam("estimateId") long estimateId,
			@PathParam("orderId") long orderId,
			@PathParam("score") int score,
			@PathParam("comment") String comment) {
		RestBean<Estimate> re = new RestBean<>();
		Estimate estimate=estimateService.updateEstimate(estimateId, orderId, score,
				comment);
		if(estimate!=null){
			re.setMsg("创建评论成功");
			re.setResult(estimate);
		}else{
			re.setState(201);
			re.setMsg("创建评论失败");
		}
		return re;

	}

	/* 删除评论 */
	@GET
	@Path("/deleteEstimate/estimateId")
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
