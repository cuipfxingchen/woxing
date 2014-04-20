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
public class Estimate {
	public Estimate() {

	}

	public long getEstimateId() {
		return estimateId;
	}

	public void setEstimateId(long estimateId) {
		this.estimateId = estimateId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	/* 评价对象ID */
	private long estimateId;
	/* 评价对应订单ID */
	private long orderId;
	/* 评价用户id */
	private long userId;
	/* id的用户对订单的评分(对出租车司机的评分) */
	private int score;
	/* 对订单的评论 */
	private String comment;
	/* 评价生成时间 */
	private String estimateTime;

}
