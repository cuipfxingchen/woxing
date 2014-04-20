package com.hdsx.taxi.woxing.mqutil.message.location;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import com.hdsx.taxi.woxing.bean.CarInfo;
import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;

/**
 * 单辆车信息返回消息
 * 
 * @author Steven
 * 
 */
public class MQMsg3008 extends MQAbsMsg {

	CarInfo car = new CarInfo();

	public MQMsg3008() {
	}

	public CarInfo getCar() {
		return car;
	}

	public void setCar(CarInfo car) {
		this.car = car;
	}

	public MQMsg3008(String customId) {
		super(customId);
	}

	@Override
	protected short getMessageId() {
		// TODO Auto-generated method stub
		return 0x3008;
	}

	@Override
	protected void decodebody(BytesMessage msg) throws JMSException {
		car.setLisencenumber(msg.readUTF());
		car.setDriverName(msg.readUTF());
		car.setLon(msg.readInt() / 10E6d);
		car.setLat(msg.readInt() / 10E6d);
		car.setCompany(msg.readUTF());

	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {
		msg.writeUTF(car.getLisencenumber());
		msg.writeUTF(car.getDriverName());
		msg.writeInt((int) (car.getLon() * 10E6));
		msg.writeInt((int) (car.getLat() * 10E6));
		msg.writeUTF(car.getCompany());
		return msg;
	}

}
