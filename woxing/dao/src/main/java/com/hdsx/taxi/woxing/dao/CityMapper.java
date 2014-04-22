package com.hdsx.taxi.woxing.dao;

import org.apache.ibatis.annotations.Select;

import com.hdsx.taxi.woxing.bean.City;

/**
 * 查询城市小费
 * @author cuipengfei
 *
 */
public interface CityMapper {
	
	@Select("SELECT id,name,code,tip,datetime FROM citymoney WHERE code = #{code} limit 1 ")
	City getCityByCode(String code);
	
	@Select("SELECT id,name,code,tip,datetime FROM citymoney WHERE name like %#{cityName}% limit 1")
	City getCityByCityName(String cityName);
}
