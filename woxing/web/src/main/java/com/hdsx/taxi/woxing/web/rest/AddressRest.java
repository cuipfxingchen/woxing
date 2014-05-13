package com.hdsx.taxi.woxing.web.rest;

import java.util.List;

import org.jboss.resteasy.annotations.Form;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.hdsx.taxi.woxing.bean.Address;
import com.hdsx.taxi.woxing.web.rest.bean.RestBean;
import com.hdsx.taxi.woxing.web.service.AddressService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * 常用地址服务
 * 
 * @author cuipengfei
 * 
 */
@Path("/rest/ads")
public class AddressRest {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(AddressRest.class);

	
	@Inject
	AddressService addressService;
	
	/**
	 * 创建常用地址
	 * @param address
	 * @return
	 */
	@POST
	@Path("/1")
	@Produces("application/json;charset=UTF-8")
	public RestBean addAddressRest(@Form Address address) {
		RestBean restBean = new RestBean<>();
		Address ad=addressService.getAddressOne(address.getCitycode(),address.getCustomid(), address.getSeqe());
		if(ad==null){
			if(addressService.createAddress(address)){
				restBean.setMsg("创建成功");
			}else{
				restBean.setState(201);
				restBean.setMsg("创建失败");
			}
		}else{
			address.setId(ad.getId());
			if(addressService.updateAddress(address)){
				restBean.setMsg("更新成功");
			}else{
				restBean.setState(201);
				restBean.setMsg("更新失败");
			}
		}
		
		return restBean;
	}


	/**
	 * 查询常用地址
	 * @param customid
	 * @param citycode
	 * @return
	 */
	@GET
	@Path("/3/{citycode}/{customid}")
	@Produces("application/json;charset=UTF-8")
	public RestBean<List<Address>> getAddAddressRest(
			@PathParam("customid") String customid,
			@PathParam("citycode") String citycode) {
		RestBean<List<Address>> restBean = new RestBean<>();
		List<Address> list;
		list=addressService.getAddressList(citycode, customid);
		if(list!=null){
			restBean.setResult(list);
			restBean.setMsg("查询成功");
		}else{
			restBean.setState(201);
			restBean.setMsg("查询失败");
		}
		return restBean;
	}

	/**
	 * 删除常用地址
	 * @param id
	 * @return
	 */
	@GET
	@Path("/4/{id}")
	@Produces("application/json;charset=UTF-8")
	public RestBean deleteAddAddressRest(@PathParam("id") String id) {
		RestBean restBean = new RestBean<>();
		if(addressService.deleteaddress(id)){
			restBean.setMsg("删除成功");
		}else{
			restBean.setState(201);
			restBean.setMsg("删除失败");
		}
		return restBean;
	}

}
