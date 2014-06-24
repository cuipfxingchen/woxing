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
	@Select("SELECT * FROM db_address WHERE citycode=#{param1} AND customid=#{param2} ")
	public List<Address> getAddressList(String citycode,String customid);

	/* 新建常用地址 */
	@Insert("INSERT INTO db_address(id,title,address,customid,citycode,lon,lat,seqe) VALUES (#{id},#{title},#{address},#{customid},#{citycode},#{lon},#{lat},#{seqe})")
	public int createAddress(Address address);

	/* 查询单个地址 */
	@Select("SELECT * FROM db_address WHERE citycode=#{param1} AND customid=#{param2} AND seqe=#{param3} ")
	public Address getAddressOne(String citycode,String customid,int seqe);
	
	/*更新常用地址*/
	@Update("UPDATE db_address SET title = #{title},address = #{address},customid =#{customid},citycode =#{citycode},lon = #{lon},lat =#{lat},seqe =#{seqe} WHERE id = #{id}")
	public int updateAddress(Address address);

	/* 删除常用地址*/
	@Delete("DELETE FROM db_address WHERE id = #{param1}")
	public int deleteAddress(String id);
}
