package com.hdsx.taxi.woxing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.hdsx.taxi.woxing.bean.Complaint;
import com.hdsx.taxi.woxing.bean.Estimate;
import com.hdsx.taxi.woxing.bean.Order;

public interface EstimateMapper {
	
	/* 查询评论 */
	public Estimate getEstimateById(long estimateId);

	/* 新建评论 */
	@Insert("insert into orderaa (id,operationid,name) values(#{id},#{operationid},#{name})")
	public boolean createEstimate(Estimate estimate);

	/* 修改评论 */
	public Estimate updateEstimate(long estimateId, long orderId, int score,
			String comment);

	/* 删除评论 */
	public boolean deleteEstimate(long estimateId);
}
