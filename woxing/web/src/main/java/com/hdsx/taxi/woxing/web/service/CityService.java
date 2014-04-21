package com.hdsx.taxi.woxing.web.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.hdsx.taxi.woxing.bean.City;
import com.hdsx.taxi.woxing.dao.CityMapper;
/**
 * 城市相关服务
 * @author cuipengfei
 *
 */

@Singleton
public class CityService {
	
	@Inject
	CityMapper cityMapper;
	
	/**
	 * 通过城市编码查询是否有小费
	 * @param code
	 * @return
	 */
	public boolean findCityByCode(String code) {
		// 通过 城市 code 
	   City ct = cityMapper.getCityByCode(code);
	   //判断是否开启小费
	   if(ct != null){
		   if(ct.getTip().equals("1")){
			   return true;
		   }
	   }
		return false;
	}

	/**
	 * 通过城市名称查询是否有小费
	 * @param cityName
	 * @return
	 */
	public boolean findCityByCityName(String cityName) {
		// 通过 城市 code 
		   City ct = cityMapper.getCityByCityName(cityName);
		   //判断是否开启小费
		   if(ct != null){
			   if(ct.getTip().equals("1")){
				   return true;
			   }
		   }
		return false;
	}
}
