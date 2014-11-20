package com.hdsx.taxi.woxing.web.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.hdsx.taxi.woxing.bean.Complaint;
import com.hdsx.taxi.woxing.bean.FeedBack;
import com.hdsx.taxi.woxing.dao.ComplaintMapper;

/**
 * 投诉相关服务
 * 
 * @author cuipengfei
 * 
 */
@Singleton
public class ComplaintService {

	@Inject
	ComplaintMapper complaintMapper;

	/**
	 * 提交投诉信息
	 * 
	 * @param complaint
	 * @param citycode
	 * @param customid
	 * @return
	 */
	public boolean saveComplain(Complaint complaint) {
		Complaint complaint1 = complaintMapper.getComplaintById(complaint
				.getOrderId());
		if (complaint1 == null) {
			if (complaintMapper.createComplaint(complaint) > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			if (complaintMapper.updateComplaint(complaint) > 0) {
				return true;
			} else {
				return false;
			}
		}

	}

	/**
	 * 意见反馈
	 * 
	 * @param feedBack
	 * @return
	 */
	public boolean saveFeedBack(FeedBack feedBack) {
		if (complaintMapper.addFeedBack(feedBack) > 0) {
			return true;
		}
		return false;
	}
}
