package com.hdsx.taxi.woxing.dao;


import org.apache.ibatis.annotations.Insert;

import com.hdsx.taxi.woxing.bean.Complaint;

public interface ComplaintMapper {

	@Insert("insert into taxi.db_complaint(id,db_order_id,db_type,db_content,db_state,db_passengerMobile,db_passengerName,db_complaint_creat_time) values(#{id},#{orderId},#{type},#{content},#{state},#{passengerMobile},#{passengerName},#{complainDate})")
	public int insert(Complaint complaint);
}
