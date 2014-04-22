/**
 * Project Name:ActiveMQUtil
 * File Name:Estimate.java
 * Package Name:com.hdsx.taxi.util.beans.estimate
 * Date:2013年8月29日上午2:16:51
 * Copyright (c) 2013, Kevinyarm All Rights Reserved.
 * Blog http://www.cnblogs.com/Kevinyarm/
 *
 */

package com.hdsx.taxi.woxing.bean;

import java.io.Serializable;

import javax.ws.rs.FormParam;

/**
 * ClassName:Estimate
 * 
 * Function: 评价对象.
 * 
 * Reason: TODO ADD REASON.
 * 
 * Date: 2013年8月29日 上午2:16:51
 * 
 * @author Kevin
 * @see
 */
public class Estimate implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3297840773127067101L;
	public Estimate() {

	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}


	public String getCustomid() {
		return customid;
	}

	public void setCustomid(String customid) {
		this.customid = customid;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getEstimateTime() {
		return estimateTime;
	}

	public void setEstimateTime(String estimateTime) {
		this.estimateTime = estimateTime;
	}

	/* 评价对应订单ID */
	@FormParam("orderId")
	private long orderId;
	/* 评价用户id */
	@FormParam("customid")
	private String customid;
	@FormParam("citycode")
	private String citycode;//城市代码
	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	/* id的用户对订单的评分(对出租车司机的评分) */
	@FormParam("score")
	private int score;
	/* 对订单的评论 */
	@FormParam("comment")
	private String comment;
	/* 评价生成时间 */
	@FormParam("estimateTime")
	private String estimateTime;

}
