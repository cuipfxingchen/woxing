package com.hdsx.taxi.woxing.bean;

import java.io.Serializable;
import java.util.Date;

import javax.ws.rs.FormParam;

/**
 * 意见反馈实体
 * 
 * @author cuipengfei
 * 
 */
public class FeedBack implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -272543732668765285L;

	@FormParam("id")
	private String id;

	@FormParam("customid")
	private String customid;

	@FormParam("citycode")
	private String citycode;

	@FormParam("content")
	private String content;

	@FormParam("time")
	private Date time;

	
	@Override
	public String toString() {
		return "FeedBack [id=" + id + ", customid=" + customid + ", citycode="
				+ citycode + ", content=" + content + ", time=" + time + "]";
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
