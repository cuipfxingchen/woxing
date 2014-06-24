package com.hdsx.taxi.woxing.bean;

import java.io.Serializable;
import java.util.Date;

import javax.ws.rs.FormParam;

/**
 * 订单实体
 * 
 * @author Steven
 * 
 */

public class Order implements Serializable {
	private static final long serialVersionUID = -5167741453339721565L;

	/**
	 * 订单已发送
	 */
	public static byte STATE_SENDED = -1;
	/**
	 * 更新完订单号订单开始
	 */
	public static byte STATE_START=0;
	
	
	/**
	 * 已经找到车
	 */
	public static byte STATE_HASCAR = 1;
	/**
	 * 找不到车
	 */
	public static byte STATE_NOCAR = 2;
	
	/**
	 * 无车抢单
	 */
	public static byte STATE_NOCARCall = 2;
	
	/**
	 * 乘客已经上车
	 */
	public static byte STATE_PASSAGER_ON = 3;

	/**
	 * 乘客已支付
	 */
	public static byte STATE_PAYED = 4;

	/**
	 * 预约订单还未开始执行
	 */
	public static byte STATE_WAITE_FOR_OPERATE = 11;

	/**
	 * 预约订单正在执行
	 */
	public static byte STATE_OPERATING = 12;
	
	/**
	 * 乘客取消订单
	 */
	public static byte STATE_CANCEL_BY_PASS = 20;
	
	/**
	 * 驾驶员取消订单
	 */
	public static byte STATE_CANCEL_BY_DRIVE = 21;

	/**
	 * 待付款状态
	 */
	public static byte STATE_FUKUAN=30;
	
	/**
	 * 订单完成
	 */
	public static byte STATE_OVER=100;
	
	public String getUseriphone() {
		return useriphone;
	}

	public void setUseriphone(String useriphone) {
		this.useriphone = useriphone;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public OrderResult getResult() {
		if (result == null)
			result = new OrderResult();
		return result;
	}

	public void setResult(OrderResult result) {
		this.result = result;
	}

	public byte getPaytype() {
		return paytype;
	}

	public void setPaytype(byte paytype) {
		this.paytype = paytype;
	}

	public short getFee() {
		return fee;
	}

	public void setFee(short fee) {
		this.fee = fee;
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public byte getSex() {
		return sex;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}

	public Date getGetOnTime() {
		return getOnTime;
	}

	public void setGetOnTime(Date getOnTime) {
		this.getOnTime = getOnTime;
	}

	public Date getLastReplTime() {
		return lastReplTime;
	}

	public void setLastReplTime(Date lastReplTime) {
		this.lastReplTime = lastReplTime;
	}
	

	public byte getContractTaxi() {
		return contractTaxi;
	}

	public void setContractTaxi(byte contractTaxi) {
		this.contractTaxi = contractTaxi;
	}

	public byte getVipMark() {
		return vipMark;
	}

	public void setVipMark(byte vipMark) {
		this.vipMark = vipMark;
	}

	public byte getReservation() {
		return reservation;
	}

	public void setReservation(byte reservation) {
		this.reservation = reservation;
	}

	public byte getTakeTaxiType() {
		return takeTaxiType;
	}

	public void setTakeTaxiType(byte takeTaxiType) {
		this.takeTaxiType = takeTaxiType;
	}

	public byte getServerLevel() {
		return serverLevel;
	}

	public void setServerLevel(byte serverLevel) {
		this.serverLevel = serverLevel;
	}

	public String getFirstChoiceCompany() {
		return firstChoiceCompany;
	}

	public void setFirstChoiceCompany(String firstChoiceCompany) {
		this.firstChoiceCompany = firstChoiceCompany;
	}

	public int getPersonCount() {
		return personCount;
	}

	public void setPersonCount(int personCount) {
		this.personCount = personCount;
	}

	public double getGetOnLon() {
		return getOnLon;
	}

	public void setGetOnLon(double getOnLon) {
		this.getOnLon = getOnLon;
	}

	public double getGetOnLat() {
		return getOnLat;
	}

	public void setGetOnLat(double getOnLat) {
		this.getOnLat = getOnLat;
	}

	public double getGetOffLon() {
		return getOffLon;
	}

	public void setGetOffLon(double getOffLon) {
		this.getOffLon = getOffLon;
	}

	public double getGetOffLat() {
		return getOffLat;
	}

	public void setGetOffLat(double getOffLat) {
		this.getOffLat = getOffLat;
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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getMotorcycleType() {
		return motorcycleType;
	}

	public void setMotorcycleType(String motorcycleType) {
		this.motorcycleType = motorcycleType;
	}

	public String getAnotherCellPhoneNo() {
		return anotherCellPhoneNo;
	}

	public void setAnotherCellPhoneNo(String anotherCellPhoneNo) {
		this.anotherCellPhoneNo = anotherCellPhoneNo;
	}

	public String getSpecialRequirements() {
		return specialRequirements;
	}

	public void setSpecialRequirements(String specialRequirements) {
		this.specialRequirements = specialRequirements;
	}

	public Date getOrderCreateTime() {
		return orderCreateTime;
	}

	public void setOrderCreateTime(Date orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public String getCityResponse2CenterTime() {
		return cityResponse2CenterTime;
	}

	public void setCityResponse2CenterTime(String cityResponse2CenterTime) {
		this.cityResponse2CenterTime = cityResponse2CenterTime;
	}

	public String getTaxiResponse2CityTime() {
		return taxiResponse2CityTime;
	}

	public void setTaxiResponse2CityTime(String taxiResponse2CityTime) {
		this.taxiResponse2CityTime = taxiResponse2CityTime;
	}

	/**
	 * 返回结果集对象
	 */

	private OrderResult result = new OrderResult();

	/**
	 * 城市编码
	 */
	@FormParam("citycode")
	private String citycode;
	/**
	 * 用户电话号码
	 */
	@FormParam("useriphone")
	private String useriphone;

	/**
	 * 支付类型
	 */
	@FormParam("paytype")
	private byte paytype;

	/**
	 * 打车费用
	 */
	@FormParam("fee")
	private short fee;
	
	
	/**
	 * 电招费
	 */
	private short fee2;

	/**
	 * 订单状态
	 */
	@FormParam("state")
	private byte state = -1;

	/**
	 * Order的Object主键ID
	 * 
	 * 在此次环境下,使用即时毫秒数作为订单id录入数据库
	 */
	@FormParam("orderId")
	private long orderId;
	/*--------------------用户表部分--------------------*/
	/**
	 * 用户id
	 * 
	 * (用户手机号)
	 */
	@FormParam("customid")
	private String customid;

	/**
	 * 用户昵称
	 */
	@FormParam("nickName")
	private String nickName;

	/**
	 * 用户性别
	 * 
	 * 取值
	 * 
	 * 0为男
	 * 
	 * 1为女
	 */
	@FormParam("sex")
	private byte sex;

	/**
	 * 出发时间 yyyy-MM-dd HH:mm:ss
	 */
	@FormParam("getOnTime")
	private Date getOnTime;

	/**
	 * 最晚回复时间
	 * 
	 * yyyy-MM-dd HH:mm:ss
	 */
	@FormParam("lastReplTime")
	private Date lastReplTime;

	/**
	 * 是否搜索签约出租车
	 * 1:是，0：否
	 */
	@FormParam("contractTaxi")
	private byte contractTaxi;

	/**
	 * vip标记
	 * 1:是，0：否
	 */
	@FormParam("vipMark")
	private byte vipMark;

	/**
	 * 是否预约标记
	 * 1:是，0：否
	 */
	@FormParam("reservation")
	private byte reservation;
	/**
	 * 打车类型 0是普通, 1是等级,2指派
	 */
	@FormParam("takeTaxiType")
	private byte takeTaxiType;

	/**
	 * 服务等级
	 * 
	 * 1为最高
	 */
	@FormParam("serverLevel")
	private byte serverLevel;

	/**
	 * 乘客首选公司
	 */
	@FormParam("firstChoiceCompany")
	private String firstChoiceCompany;

	/**
	 * 人数
	 */
	@FormParam("personCount")
	private int personCount;

	/**
	 * 上车地点经度信息
	 */
	@FormParam("getOnLon")
	private double getOnLon;

	/**
	 * 上车地点纬度信息
	 */
	@FormParam("getOnLat")
	private double getOnLat;

	/**
	 * 下车地点经度信息
	 */
	@FormParam("getOffLon")
	private double getOffLon;

	/**
	 * 下车地点纬度信息
	 */
	@FormParam("getOffLat")
	private double getOffLat;

	/**
	 * 上车地点名称
	 */
	@FormParam("getOnPlaceName")
	private String getOnPlaceName;

	/**
	 * 下车地点名称
	 */
	@FormParam("getOffPlaceName")
	private String getOffPlaceName;

	/**
	 * 备注
	 */
	@FormParam("notes")
	private String notes;

	/**
	 * 车型要求
	 */
	@FormParam("motorcycleType")
	private String motorcycleType;

	/**
	 * 备用电话
	 */
	@FormParam("anotherCellPhoneNo")
	private String anotherCellPhoneNo;

	/**
	 * 特殊要求
	 */
	@FormParam("specialRequirements")
	private String specialRequirements;

	/**
	 * 订单生成时间
	 */
	@FormParam("orderCreateTime")
	private Date orderCreateTime;

	/**
	 * 下单地点经度
	 */
	@FormParam("createLon")
	private double createLon;
	
	/**
	 * 下单地点纬度
	 */
	@FormParam("createLat")
	private double createLat;
	
	/**
	 * 实际上车地点经度
	 */
	@FormParam("finalOnLon")
	private double finalOnLon;
	
	/**
	 * 实际上车地点纬度
	 */
	@FormParam("finalOnLat")
	private double finalOnLat;
	
	/**
	 * 上车时间
	 */
	@FormParam("finalOnTime")
	private Date finalOnTime;
	
	/**
	 * [城市级响应到中心时间]
	 * 
	 * String类型时间格式的字符串yyyy-MM-dd HH:mm:ss
	 */
	@FormParam("cityResponse2CenterTime")
	private String cityResponse2CenterTime;

	/**
	 * [出租车响应到城市级时间]
	 * 
	 * String类型时间格式的字符串yyyy-MM-dd HH:mm:ss
	 */
	@FormParam("taxiResponse2CityTime")
	private String taxiResponse2CityTime;

	/* id的用户对订单的评分(对出租车司机的评分) */
	@FormParam("score")
	private int score;
	/* 对订单的评论 */
	@FormParam("comment")
	private String comment;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Order [result=" + result + ", citycode=" + citycode
				+ ", useriphone=" + useriphone + ", paytype=" + paytype
				+ ", fee=" + fee + ", state=" + state + ", orderId=" + orderId
				+ ", customid=" + customid + ", nickName=" + nickName
				+ ", sex=" + sex + ", getOnTime=" + getOnTime
				+ ", lastReplTime=" + lastReplTime + ", contractTaxi="
				+ contractTaxi + ", vipMark=" + vipMark + ", reservation="
				+ reservation + ", takeTaxiType=" + takeTaxiType
				+ ", serverLevel=" + serverLevel + ", firstChoiceCompany="
				+ firstChoiceCompany + ", personCount=" + personCount
				+ ", getOnLon=" + getOnLon + ", getOnLat=" + getOnLat
				+ ", getOffLon=" + getOffLon + ", getOffLat=" + getOffLat
				+ ", getOnPlaceName=" + getOnPlaceName + ", getOffPlaceName="
				+ getOffPlaceName + ", notes=" + notes + ", motorcycleType="
				+ motorcycleType + ", anotherCellPhoneNo=" + anotherCellPhoneNo
				+ ", specialRequirements=" + specialRequirements
				+ ", orderCreateTime=" + orderCreateTime
				+ ", cityResponse2CenterTime=" + cityResponse2CenterTime
				+ ", taxiResponse2CityTime=" + taxiResponse2CityTime + "]";
	}

	public short getFee2() {
		return fee2;
	}

	public void setFee2(short fee2) {
		this.fee2 = fee2;
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

	public double getCreateLon() {
		return createLon;
	}

	public void setCreateLon(double createLon) {
		this.createLon = createLon;
	}

	public double getCreateLat() {
		return createLat;
	}

	public void setCreateLat(double createLat) {
		this.createLat = createLat;
	}

	public double getFinalOnLon() {
		return finalOnLon;
	}

	public void setFinalOnLon(double finalOnLon) {
		this.finalOnLon = finalOnLon;
	}

	public double getFinalOnLat() {
		return finalOnLat;
	}

	public void setFinalOnLat(double finalOnLat) {
		this.finalOnLat = finalOnLat;
	}

	public Date getFinalOnTime() {
		return finalOnTime;
	}

	public void setFinalOnTime(Date finalOnTime) {
		this.finalOnTime = finalOnTime;
	}
	
	
	

}
