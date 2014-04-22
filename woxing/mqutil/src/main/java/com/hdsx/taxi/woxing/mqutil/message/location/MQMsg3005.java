package com.hdsx.taxi.woxing.mqutil.message.location;

import java.util.ArrayList;
import java.util.List;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import com.hdsx.taxi.woxing.bean.Index;
import com.hdsx.taxi.woxing.bean.TaxiIndex;
import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;

/*******************************************************************************
 * <b>类名:MQMsg3005</b> <br/>
 * 功能：...<br/>
 * 日期: 2013年9月13日<br/>
 * 
 * @author 谢广泉 xiegqooo@gmail.com
 * @version 1.0.0
 * 
 ******************************************************************************/
public class MQMsg3005 extends MQAbsMsg {

	List<Index> ls;
	private List<TaxiIndex> list;

	public MQMsg3005() {
		super();
	}

	public MQMsg3005(String customId) {
		super(customId);
	}

	@Override
	protected short getMessageId() {
		return 0x3005;
	}

	@Override
	protected void decodebody(BytesMessage msg) throws JMSException {
		int count = msg.readInt();
		this.ls = new ArrayList<Index>();
		if (count > 0) {
			for (int i = 0; count > i; i++) {
				Index ti = new Index();
				ti.setIndex(msg.readByte());
				ti.setWaittime(msg.readInt());
				ti.setZones(msg.readUTF());
				this.ls.add(ti);
			}
		}
	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {
		msg.writeInt(this.list.size());
		for (TaxiIndex ti : list) {
			msg.writeByte(ti.getIndex());
			msg.writeInt(ti.getWaittime());
			msg.writeUTF(ti.getZones());
		}
		return msg;
	}

	public void setList(List<TaxiIndex> list) {
		this.list = list;
	}

	public List<Index> getLs() {
		return this.ls;
	}

}
