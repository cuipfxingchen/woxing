package com.hdsx.taxi.woxing.web.service;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.hdsx.taxi.woxing.bean.Address;
import com.hdsx.taxi.woxing.dao.AddressMapper;
/**
 * 常用地址相关服务
 * @author cuipengfei
 *
 */
@Singleton
public class AddressService {
	
	@Inject
	AddressMapper addressMapper;
	
	/**
	 * 创建常用地址
	 * @param address
	 * @return
	 */
	public boolean createAddress(Address address){
		if(addressMapper.createAddress(address)>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 根据用户名和序号查找地址
	 * @param citycode
	 * @param customid
	 * @param order
	 * @return
	 */
	public Address getAddressOne(String citycode,String customid,int order){
		return addressMapper.getAddressOne(citycode, customid, order);
	}
	
	
	/**
	 * 更新常用地址
	 * @param address
	 * @return
	 */
	public boolean updateAddress(Address address){
		if(addressMapper.updateAddress(address)>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 删除常用地址
	 * @param id
	 * @return
	 */
	public boolean deleteaddress(String id){
		if(addressMapper.deleteAddress(id)>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 根据城市编码和用户名查询常用地址集合
	 * @param citycode
	 * @param customid
	 * @return
	 */
	public List<Address> getAddressList(String citycode,String customid){
		return addressMapper.getAddressList(citycode, customid);
	}
	
}
