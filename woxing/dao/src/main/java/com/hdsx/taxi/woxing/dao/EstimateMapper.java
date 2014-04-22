package com.hdsx.taxi.woxing.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hdsx.taxi.woxing.bean.Estimate;

public interface EstimateMapper {
	
	/* 查询评论 */
	@Select("SELECT * FROM db_estimate WHERE 1=1 AND id=#{estimateId}")
	public Estimate getEstimateById(long estimateId);

	/* 新建评论 */
	@Insert("INSERT INTO db_estimate(id,db_order_id,db_user_id,db_score,db_comment,db_estimate_time) VALUES (#{estimateId},#{orderId},#{userId},#{score},#{comment},#{estimateTime})")
	public boolean createEstimate(Estimate estimate);

	/* 修改评论 */
	@Update("UPDATE db_estimate SET db_user_id = #{userId},db_score = #{score},db_comment =#{comment},db_estimate_time =#{estimateTime} WHERE db_order_id = #{orderId}")
	public Estimate updateEstimate(Estimate Estimate);

	/* 删除评论 */
	@Delete("DELETE FROM db_estimate WHERE id = #{estimateId}")
	public boolean deleteEstimate(long estimateId);
}
