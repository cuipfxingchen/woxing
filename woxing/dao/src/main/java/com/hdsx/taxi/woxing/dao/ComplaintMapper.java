package com.hdsx.taxi.woxing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.hdsx.taxi.woxing.bean.Complaint;
import com.hdsx.taxi.woxing.bean.Order;

public interface ComplaintMapper {
	@Select("select * from orderaa")
	public List<Complaint> getlist();

	@Insert("insert into orderaa (id,operationid,name) values(#{id},#{operationid},#{name})")
	public int insert(Complaint complaint);
}
