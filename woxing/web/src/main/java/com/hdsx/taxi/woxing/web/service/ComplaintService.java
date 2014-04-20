package com.hdsx.taxi.woxing.web.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.hdsx.taxi.woxing.bean.Complaint;
import com.hdsx.taxi.woxing.dao.ComplaintMapper;
import com.hdsx.taxi.woxing.mqutil.MQService;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg4001;
import com.hdsx.taxi.woxing.mqutil.msgpool.MQMsgPool;

/**
 * 投诉相关服务
 * @author cuipengfei
 *
 */
@Singleton
public class ComplaintService {

	MQService ms;
	MQMsgPool msgpool;

	@Inject
	public ComplaintService(MQMsgPool msgpool) {
		super();
		this.ms = MQService.getInstance();
		this.msgpool = msgpool;
	}

	@Inject
	ComplaintMapper complaintMapper;
	
	
	/**
	 * 提交投诉信息
	 * @param complaint
	 * @param citycode
	 * @param customid
	 * @return
	 */
	public boolean saveComplain(Complaint complaint,String citycode,String customid) {
		try {
			complaintMapper.insert(complaint);
			
			//   封装内容
			MQMsg4001 msg = new MQMsg4001(customid);
			msg.setContents(complaint.getContent());
			msg.setPass_name(complaint.getPassengerName());
			msg.setPass_tel(complaint.getPassengerMobile());
			msg.setType((byte) 1); 
			msg.setId(Long.parseLong(complaint.getOrderId()));
			
			// 发送信息
			ms.sendMsg(citycode, msg);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
