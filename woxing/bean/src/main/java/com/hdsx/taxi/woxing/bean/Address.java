package com.hdsx.taxi.woxing.bean;

import java.io.Serializable;

import javax.ws.rs.FormParam;

import org.jboss.resteasy.annotations.Form;

/**
 * 常用地址实体
 * @author cuipengfei
 *
 */
public class Address implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2551605321539607119L;
	/**
	 *  id 
	 */
	@FormParam("id")
	private String id ;
	/**
	 * 地址标题名称
	 */
	@FormParam("title")
	private String title;
	/**
	 *  地址名称
	 */
	@FormParam("address")
	private String address ;
	/**
	 *  用户名称
	 */
	@FormParam("customid")
	private String customid ;
	
	/**
	 * 城市编码
	 */
	@FormParam("citycode")
	private String citycode;
	/**
	 *  纬度
	 */
	@FormParam("lat")
	private String lat ;
	/**
	 *  经度
	 */
	@FormParam("lon")
	private String lon ;
	/**
	 *  排序
	 */
	@FormParam("order")
	private int order ;
	
	public String getCitycode() {
		return citycode;
	}
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getCustomid() {
		return customid;
	}
	public void setCustomid(String customid) {
		this.customid = customid;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}


}
