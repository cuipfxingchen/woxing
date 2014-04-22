package com.hdsx.taxi.woxing.web.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.hdsx.taxi.woxing.bean.Complaint;
import com.hdsx.taxi.woxing.bean.Estimate;
import com.hdsx.taxi.woxing.dao.ComplaintMapper;
import com.hdsx.taxi.woxing.dao.EstimateMapper;
import com.hdsx.taxi.woxing.mqutil.MQService;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg4001;
import com.hdsx.taxi.woxing.mqutil.msgpool.MQMsgPool;

/**
 * 评价相关服务
 * @author cuipengfei
 *
 */
@Singleton
public class EstimateService {

	MQService ms;
	MQMsgPool msgpool;

	@Inject
	public EstimateService(MQMsgPool msgpool) {
		super();
		this.ms = MQService.getInstance();
		this.msgpool = msgpool;
	}

	@Inject
	EstimateMapper estimateMapper;
	
	
	public String getEstimateById(long estimateId) {

		if (estimateMapper.getEstimateById(estimateId) != null) {
			Estimate estimate = estimateMapper.getEstimateById(estimateId);
			Gson gson = new Gson();
			String json = gson.toJson(estimate);
			return json;
		} else {
			System.err.println("查询的评论编号异常");
			return null;
		}
	}

	/**
	 * 
	 * TODO 创建订单.
	 * 
	 * @see com.hdsx.centerservice.estimate.service.EstimateService#createEstimate(long,
	 *      long, int, java.lang.String)
	 */
	public boolean createEstimate(Estimate est) {
		long estimateId = System.currentTimeMillis();
		est.setEstimateId(estimateId);
		Date currentTime = new Date();
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		String estimateTime = format1.format(currentTime);
		est.setEstimateTime(estimateTime);
		return estimateMapper.createEstimate(est);
	}

	public Estimate updateEstimate(Estimate estimate) {
		// TODO Auto-generated method stub
		return estimateMapper.updateEstimate(estimate);
	}

	public boolean deleteEstimate(long estimateId) {

		return estimateMapper.deleteEstimate(estimateId);
	}
}
