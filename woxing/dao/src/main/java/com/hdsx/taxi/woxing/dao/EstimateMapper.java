package com.hdsx.taxi.woxing.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hdsx.taxi.woxing.bean.Estimate;

public interface EstimateMapper {
	
	/* 查询评论 */
	@Select("SELECT * FROM db_estimate WHERE orderId=#{orderId}")
	public Estimate getEstimateById(long orderId);

	/* 新建评论 */
	@Insert("INSERT INTO db_estimate(orderId,customid,citycode,score,comment,estimateTime) VALUES (#{orderId},#{customid},#{citycode},#{score},#{comment},#{estimateTime})")
	public int createEstimate(Estimate estimate);

	/* 修改评论 */
	@Update("UPDATE db_estimate SET customid = #{customid},citycode=#{citycode},score = #{score},comment =#{comment},estimateTime =#{estimateTime} WHERE orderId = #{orderId}")
	public int updateEstimate(Estimate Estimate);

	/* 删除评论 */
	@Delete("DELETE FROM db_estimate WHERE orderId = #{orderId}")
	public int deleteEstimate(long estimateId);
}
