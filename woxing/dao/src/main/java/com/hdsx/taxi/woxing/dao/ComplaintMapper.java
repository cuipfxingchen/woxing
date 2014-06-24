package com.hdsx.taxi.woxing.dao;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hdsx.taxi.woxing.bean.Complaint;
import com.hdsx.taxi.woxing.bean.Estimate;
/**
 * 投诉dao
 * @author cuipengfei
 *
 */
public interface ComplaintMapper {
	

	/* 查询投诉 */
	@Select("SELECT * FROM db_complaint WHERE orderId=#{orderId}")
	public Complaint getComplaintById(long orderId);

	/* 新建投诉 */
	@Insert("insert into db_complaint(id,orderId,type,content,state,passengerMobile,passengerName,complainDate,"
			+ "citycode,customid) values(#{id},#{orderId},#{type},#{content},#{state},#{passengerMobile},#{passengerName},#{complainDate},#{citycode},#{customid})")
	public int createComplaint(Complaint complaint);

	/* 修改投诉 */
	@Update ("UPDATE db_complaint SET type=#{type},content=#{content},state=#{state},"
			+ "passengerMobile=#{passengerMobile},passengerName=#{passengerName},complainDate=#{complainDate},customid=#{customid},citycode=#{citycode} WHERE orderId = #{orderId}")
	public int updateComplaint(Complaint complaint);

	/* 删除投诉 */
	@Delete("DELETE FROM db_complaint WHERE orderId = #{orderId}")
	public int deleteEstimate(long complaintId);
}
