/**
 * Project Name:ActiveMQUtil
 * File Name:HistoryOrderResult.java
 * Package Name:com.hdsx.taxi.util.beans.orders
 * Date:2013年9月3日上午10:14:05
 * Copyright (c) 2013, Kevinyarm All Rights Reserved.
 * Blog http://www.cnblogs.com/Kevinyarm/
 *
 */

package com.hdsx.taxi.woxing.bean;

/**
 * ClassName:HistoryOrderResult
 * 
 * Function: TODO Order订单对象针对历史订单封装返回对象.
 * 
 * Date: 2013年9月3日 上午10:14:05
 * 
 * @author Kevin
 * @see
 */
public class HistoryOrderResult {
	public HistoryOrderResult() {

	}

	public String getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}

	public String getGetOnPlaceName() {
		return getOnPlaceName;
	}

	public void setGetOnPlaceName(String getOnPlaceName) {
		this.getOnPlaceName = getOnPlaceName;
	}

	public String getGetOffPlaceName() {
		return getOffPlaceName;
	}

	public void setGetOffPlaceName(String getOffPlaceName) {
		this.getOffPlaceName = getOffPlaceName;
	}

	public String getGetOnLon() {
		return getOnLon;
	}

	public void setGetOnLon(String getOnLon) {
		this.getOnLon = getOnLon;
	}

	public String getGetOnLat() {
		return getOnLat;
	}

	public void setGetOnLat(String getOnLat) {
		this.getOnLat = getOnLat;
	}

	public String getGetOffLon() {
		return getOffLon;
	}

	public void setGetOffLon(String getOffLon) {
		this.getOffLon = getOffLon;
	}

	public String getGetOffLat() {
		return getOffLat;
	}

	public void setGetOffLat(String getOffLat) {
		this.getOffLat = getOffLat;
	}

	public String getGetOnTime() {
		return getOnTime;
	}

	public void setGetOnTime(String getOnTime) {
		this.getOnTime = getOnTime;
	}

	public String getTaxiCompany() {
		return taxiCompany;
	}

	public void setTaxiCompany(String taxiCompany) {
		this.taxiCompany = taxiCompany;
	}

	public String getTaxiDriverName() {
		return taxiDriverName;
	}

	public void setTaxiDriverName(String taxiDriverName) {
		this.taxiDriverName = taxiDriverName;
	}

	public String getTaxiPlateNumber() {
		return taxiPlateNumber;
	}

	public void setTaxiPlateNumber(String taxiPlateNumber) {
		this.taxiPlateNumber = taxiPlateNumber;
	}

	public String getTaxiId() {
		return taxiId;
	}

	public void setTaxiId(String taxiId) {
		this.taxiId = taxiId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getSucceedMark() {
		return succeedMark;
	}

	public void setSucceedMark(String succeedMark) {
		this.succeedMark = succeedMark;
	}

	// 订单生成时间
	private String currentTime;
	// 出发地点
	private String getOnPlaceName;
	// 目的地
	private String getOffPlaceName;
	// 上 车地点经度
	private String getOnLon;
	// 上 车地点纬度
	private String getOnLat;
	// 下车地点经度
	private String getOffLon;
	// 下车地点纬度
	private String getOffLat;;
	// 用车时间 [订车时间]
	private String getOnTime;
	// 出租车公司
	private String taxiCompany;
	// 司机姓名
	private String taxiDriverName;
	// 车牌号
	private String taxiPlateNumber;
	// (隐藏)出租车id
	private String taxiId;
	// (隐藏)订单id
	private long orderId;
	// 成功/失败标记
	private String succeedMark;

}
