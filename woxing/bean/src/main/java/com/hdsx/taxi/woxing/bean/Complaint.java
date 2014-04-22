package com.hdsx.taxi.woxing.bean;

import java.io.Serializable;

import javax.ws.rs.FormParam;

/**
 * 乘客投诉实体
 * @author cuipengfei
 *
 */
public class Complaint implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5373619257607355901L;
	
	@FormParam("id")
	String id; // id
	@FormParam("orderId")
	String orderId; // 订单号
	@FormParam("type")
	String type; // 投诉类型
	@FormParam("content")
	String content; // 投诉内容
	@FormParam("state")
	String state; // 投诉状态
	@FormParam("passengerMobile")
	String passengerMobile; // 乘客电话
	@FormParam("passengerName")
	String passengerName; // 乘客名字
	@FormParam("complainDate")
	String complainDate; // 投诉时间
	@FormParam("customid")
	private String customid ;//用户id
	@FormParam("citycode")
	private String citycode;//城市代码
	
	public String getCustomid() {
		return customid;
	}

	public void setCustomid(String customid) {
		this.customid = customid;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPassengerMobile() {
		return passengerMobile;
	}

	public void setPassengerMobile(String passengerMobile) {
		this.passengerMobile = passengerMobile;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getComplainDate() {
		return complainDate;
	}

	public void setComplainDate(String complainDate) {
		this.complainDate = complainDate;
	}

}
