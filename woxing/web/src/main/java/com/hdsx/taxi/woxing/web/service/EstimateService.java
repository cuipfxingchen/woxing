package com.hdsx.taxi.woxing.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.hdsx.taxi.woxing.bean.Complaint;
import com.hdsx.taxi.woxing.bean.Estimate;
import com.hdsx.taxi.woxing.bean.Order;
import com.hdsx.taxi.woxing.dao.ComplaintMapper;
import com.hdsx.taxi.woxing.dao.EstimateMapper;
import com.hdsx.taxi.woxing.dao.OrderMapper;
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
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(EstimateService.class);

	@Inject
	OrderMapper ordermapper;
	
	@Inject
	EstimateMapper estimateMapper;
	
	
	public Estimate getEstimateById(long orderId) {
		Estimate estimate = estimateMapper.getEstimateById(orderId);
		if (estimate != null) {
//			Gson gson = new Gson();
//			String json = gson.toJson(estimate);
//			return json;
			return estimate;
		} else {
			if (logger.isInfoEnabled()) {
				logger.info("getEstimateById(long) - Estimate estimate={}", "评价查询为空"); //$NON-NLS-1$
			}
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
		Date currentTime = new Date();
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		String estimateTime = format1.format(currentTime);
		est.setEstimateTime(estimateTime);
		if(estimateMapper.createEstimate(est)>0){
			Order order =ordermapper.getOrderById(est.getOrderId());
			order.setState(Order.STATE_OVER);
			ordermapper.updateOrder(order);
			return true;
		}else{
			return false;
		}
	}

	public boolean updateEstimate(Estimate estimate) {
		// TODO Auto-generated method stub
		if(estimateMapper.updateEstimate(estimate)>0){
			return true;
		}else{
			return false;
		}
	}

	public boolean deleteEstimate(long orderId) {

		if(estimateMapper.deleteEstimate(orderId)>0){
			return true;
		}else{
			return false;
		}
	}
}
