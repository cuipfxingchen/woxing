/**
 * Project Name:MQMessage
 * File Name:Msg0001.java
 * Package Name:com.hdsx.taxi.message.order
 * Date:2013年7月3日下午4:24:42
 * Copyright (c) 2013, 谢广泉  All Rights Reserved.
 * 
 *
 */

package com.hdsx.taxi.woxing.mqutil.message.order;

import java.util.Date;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;
import com.hdsx.taxi.woxing.mqutil.util.DateFormatUtil;

/**
 * 
 * ClassName: Msg0001
 * 
 * Function: 订单消息实体类
 * 
 * Reason: 订单消息实体类
 * 
 * date: 2013-7-15 上午10:32:23
 * 
 * @author 谢广泉
 */
public class MQMsg0001 extends MQAbsMsg {

	long orderId;
	Date getOnTime; // 用车时间 yyyy-MM-dd HH:mm:ss
	Date lastReplTime; // 最晚回复时间 yyyy-MM-dd HH:mm:ss
	byte contractTaxi; // 是否搜索签约出租车 1：是、0：否
	String vipMark; // VIP
	byte takeTaxiType; // 打车类型 0是普通, 1是等级,2指派
	byte serverLevel; // 1为最高
	String firstChoiceCompany;// 乘客首选公司
	int personCount;// 人数
	double getOnLon; // 上车纬度
	double getOnLat; // 上车经度
	double getOffLon; // 下车经度
	double getOffLat; // 下车纬度
	String getOnPlaceName; // 上车地点名称
	String getOffPlaceName; // 下车地点名称
	String nickName; // 称呼
	byte sex; // 性别:0：女，1，男
	String userphone; // 乘客联系电话
	String notes; // 备注信息
	boolean revesation; // 是否预约

	public MQMsg0001() {
	}

	public MQMsg0001(String customId) {
		super(customId);
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
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

	public double getGetOnLat() {
		return getOnLat;
	}

	public void setGetOnLat(double getOnLat) {
		this.getOnLat = getOnLat;
	}

	public double getGetOnLon() {
		return getOnLon;
	}

	public void setGetOnLon(double getOnLon) {
		this.getOnLon = getOnLon;
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

	public void setGetOffLat(int getOffLat) {
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



	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getVipMark() {
		return vipMark;
	}

	public void setVipMark(String vipMark) {
		this.vipMark = vipMark;
	}

	@Override
	protected short getMessageId() {

		return 0x0001;
	}

	@Override
	protected void decodebody(BytesMessage msg) throws JMSException {
		this.orderId = msg.readLong();
		// this.getOnTime = ConvertorUtil.strToDate();
		this.getOnTime = DateFormatUtil.dateString2JavaUtilDate(msg.readUTF());
		this.lastReplTime = DateFormatUtil.dateString2JavaUtilDate(msg
				.readUTF());
		this.contractTaxi = msg.readByte();
		this.vipMark = msg.readUTF();
		this.takeTaxiType = msg.readByte();
		this.serverLevel = msg.readByte();
		this.firstChoiceCompany = msg.readUTF();
		this.personCount = msg.readInt();
		this.getOnLon = msg.readInt() / 10E6d;
		this.getOnLat = msg.readInt() / 10E6d;
		this.getOffLon = msg.readInt() / 10E6d;
		this.getOffLat = msg.readInt() / 10E6d;
		this.getOnPlaceName = msg.readUTF();
		this.getOffPlaceName = msg.readUTF();
		this.nickName = msg.readUTF();
		this.sex = msg.readByte();
		this.userphone = msg.readUTF();
		this.notes = msg.readUTF();
		this.revesation = msg.readBoolean();
	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {

		msg.writeLong(this.orderId);
		// msg.writeUTF(ConvertorUtil.dateTOString(this.getOnTime));
		// msg.writeUTF(ConvertorUtil.dateTOString(this.lastReplTime));
		msg.writeUTF(DateFormatUtil.date2MySQLDateTimeString(this.getOnTime));
		msg.writeUTF(DateFormatUtil.date2MySQLDateTimeString(this.lastReplTime));
		msg.writeByte(this.contractTaxi);
		msg.writeUTF(this.vipMark);
		msg.writeByte(this.takeTaxiType);
		msg.writeByte(this.serverLevel);
		msg.writeUTF(firstChoiceCompany);
		msg.writeInt(personCount);
		msg.writeInt((int) (this.getOnLon * 10E6));
		msg.writeInt((int) (this.getOnLat * 10E6));
		msg.writeInt((int) (this.getOffLon * 10E6));
		msg.writeInt((int) (this.getOffLat * 10E6));
		msg.writeUTF(this.getOnPlaceName);
		msg.writeUTF(this.getOffPlaceName);
		msg.writeUTF(this.nickName);
		msg.writeByte(this.sex);
		msg.writeUTF(this.userphone);
		msg.writeUTF(this.notes);
		msg.writeBoolean(this.revesation);
		return msg;
	}

	public boolean isRevesation() {
		return revesation;
	}

	public void setRevesation(boolean revesation) {
		this.revesation = revesation;
	}

}
