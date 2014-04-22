package com.hdsx.taxi.woxing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hdsx.taxi.woxing.bean.Address;
import com.hdsx.taxi.woxing.bean.City;
import com.hdsx.taxi.woxing.bean.Estimate;

/**
 * 常用地址dao
 * @author cuipengfei
 *
 */
public interface AddressMapper {
	
	/* 查询常用地址 */
	@Select("SELECT * FROM db_address WHERE 1=1 AND citycode=#{citycode} AND customid=#{customid} ")
	public List<Address> getAddressList(String citycode,String customid);

	/* 新建常用地址 */
	@Insert("INSERT INTO db_address(id,title,address,customid,citycode,lon,lat,order) VALUES (#{id},#{title},#{address},#{customid},#{citycode},#{lon},#{lat},#{order})")
	public int createAddress(Address address);

	/*更新常用地址*/
	@Update("UPDATE db_address SET title = #{title},address = #{address},customid =#{customid},citycode =#{citycode},lon = #{lon},lat =#{lat},order =#{order} WHERE id = #{id}")
	public int updateAddress(Address address);

	/* 删除常用地址*/
	@Delete("DELETE FROM db_address WHERE id = #{id}")
	public int deleteAddress(String id);
}
