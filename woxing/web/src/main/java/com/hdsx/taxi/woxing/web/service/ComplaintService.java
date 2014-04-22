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

	@Inject
	ComplaintMapper complaintMapper;
	
	
	/**
	 * 提交投诉信息
	 * @param complaint
	 * @param citycode
	 * @param customid
	 * @return
	 */
	public boolean saveComplain(Complaint complaint) {
		if(complaintMapper.insert(complaint)>0){
			return true;
		}else{
			return false;
		}
	}
}
