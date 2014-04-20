package com.hdsx.taxi.woxing.mqutil.message.location;

import java.util.ArrayList;
import java.util.List;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import com.hdsx.taxi.woxing.bean.Station;
import com.hdsx.taxi.woxing.bean.TaxiStation;
import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;

/*******************************************************************************
 * <b>类名:MQMsg3006</b> <br/>
 * 功能：返加扬招站<br/>
 * 日期: 2013年9月14日<br/>
 * 
 * @author 谢广泉 xiegqooo@gmail.com
 * @version 1.0.0
 * 
 ******************************************************************************/
public class MQMsg3006 extends MQAbsMsg {
	
	List<Station> rl ;
	private List<TaxiStation> rlf; 
	
	public MQMsg3006() {	}
	public MQMsg3006(String customId) {
		super(customId);
	}

	@Override
	protected short getMessageId() {
		return 0x3006;
	}
	
	@Override
	protected void decodebody(BytesMessage msg) throws JMSException {
		int count  = msg.readInt();
		if(count >0 ){
		rl  = new ArrayList<Station>();
			for (int i = 0; i < count; i++) {
				Station ts = new Station();
				ts.setId(msg.readUTF());
				ts.setNumber(msg.readUTF());
				ts.setDistrict(msg.readUTF());
				ts.setRoadname(msg.readUTF());
				ts.setPosition(msg.readUTF());
				ts.setStopnum(msg.readDouble());
				ts.setServicetype(msg.readUTF());
				ts.setX(msg.readDouble());
				ts.setY(msg.readDouble());
				rl.add(ts);
			}
		}
	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {
		msg.writeInt(rlf.size());
		for (TaxiStation ts : rlf) {
			msg.writeUTF(ts.getId());
			msg.writeUTF(ts.getNumber());
			msg.writeUTF(ts.getDistrict());
			msg.writeUTF(ts.getRoadname());
			msg.writeUTF(ts.getPosition());
			msg.writeDouble(ts.getStopnum());
			msg.writeUTF(ts.getStoptype());
			msg.writeDouble(ts.getPoint().getX());
			msg.writeDouble(ts.getPoint().getY());
		}
		return msg;
	}
	public List<Station> getRl() {
		return rl;
	}
	public void setRl(List<TaxiStation> rl) {
		this.rlf = rl;
	}

}
