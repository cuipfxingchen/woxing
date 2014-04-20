package com.hdsx.taxi.woxing.mqutil.message.order;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;

/**
 * 提交投訴
 * 
 * @author Steven
 * 
 */
public class MQMsg4001 extends MQAbsMsg {

	public MQMsg4001() {
		super();
	}

	public MQMsg4001(String customId) {
		super(customId);

	}

	long id;
	byte type;
	String contents;
	String pass_tel;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getPass_tel() {
		return pass_tel;
	}

	public void setPass_tel(String pass_tel) {
		this.pass_tel = pass_tel;
	}

	public String getPass_name() {
		return pass_name;
	}

	public void setPass_name(String pass_name) {
		this.pass_name = pass_name;
	}

	String pass_name;

	@Override
	protected short getMessageId() {

		return 0x4001;
	}

	@Override
	protected void decodebody(BytesMessage msg) throws JMSException {
		this.id = msg.readLong();
		this.type = msg.readByte();
		this.contents = msg.readUTF();
		this.pass_tel = msg.readUTF();
		this.pass_name = msg.readUTF();

	}

	@Override
	protected BytesMessage encodebody(BytesMessage msg) throws JMSException {
		msg.writeLong(this.id);
		msg.writeByte(type);
		msg.writeUTF(contents);
		msg.writeUTF(pass_tel);
		msg.writeUTF(pass_name);
		return msg;
	}

}
