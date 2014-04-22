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

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;

/**
 * ClassName:Msg0001 Function: 抢车成功 Date: 2013年7月3日 下午4:24:42
 * 
 * @author 谢广泉
 * @see
 */
public class MQMsg1001 extends MQAbsMsg {

	long orderId; // 订单ID
	String taxiId; // 出租车ID 对应Order中taxiId(出租车驾驶员编号)

	@Override
	public String toString() {
		return "MQMsg1001 [orderId=" + orderId + ", taxiId=" + taxiId
				+ ", number=" + number + ", phone=" + phone + ", name=" + name
				+ ", type=" + type + ", color=" + color + ", commpany="
				+ commpany + "]";
	}

	String number; // 抢单车牌号 对应Order中taxiPlateNumber(出租车牌号)
	String phone; // 抢单电话 抢单司机电话 对应Order中taxiDriverCellPhoneNo(出租车司机手机号)
	String name; // 抢单名称 出租车司机姓名 对应Order中taxiDriverName(出租车司机姓名 )
	String type; // 抢单车型 对应Order中motorcycleType(出租车具体型号)
	String color; // 抢单车颜色 对应Order中taxiColor(出租车颜色)
	String commpany; // 出租车公司 对应Order中taxiCompany(出租车所属公司)

	public MQMsg1001() {
		super();
	}

	public MQMsg1001(String customId) {
		super(customId);
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCommpany() {
		return commpany;
	}

	public void setCommpany(String commpany) {
		this.commpany = commpany;
	}

	@Override
	protected short getMessageId() {

		return 0x1001;
	}

	@Override
	protected void decodebody(BytesMessage msg) throws JMSException {
		this.taxiId = msg.readUTF();
		this.orderId = msg.readLong();
		this.number = msg.readUTF();
		this.phone = msg.readUTF();
		this.name = msg.readUTF();
		this.type = msg.readUTF();
		this.color = msg.readUTF();
		this.commpany = msg.readUTF();

	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {

		msg.writeUTF(this.taxiId);
		msg.writeLong(this.orderId);
		msg.writeUTF(this.number);
		msg.writeUTF(this.phone);
		msg.writeUTF(this.name);
		msg.writeUTF(this.type);
		msg.writeUTF(this.color);
		msg.writeUTF(this.commpany);

		return msg;
	}

}
