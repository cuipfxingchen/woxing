package com.hdsx.taxi.woxing.dao;


import org.apache.ibatis.annotations.Insert;

import com.hdsx.taxi.woxing.bean.Complaint;
/**
 * 投诉dao
 * @author cuipengfei
 *
 */
public interface ComplaintMapper {

	@Insert("insert into db_complaint(id,orderId,type,content,state,passengerMobile,passengerName,complaint_creat_time,citcode,customid) values(#{id},#{orderId},#{type},#{content},#{state},#{passengerMobile},#{passengerName},#{complainDate},#{citycode},#{customid})")
	public int insert(Complaint complaint);
}
