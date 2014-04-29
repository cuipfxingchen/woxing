package com.hdsx.taxi.woxing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hdsx.taxi.woxing.bean.Estimate;
import com.hdsx.taxi.woxing.bean.Order;

public interface OrderMapper {

	
	/* 查询订单 */
	@Select("SELECT * FROM db_order WHERE 1=1 AND orderId=#{orderId}")
	public Order getOrderById(long orderId);
	
	/* 查询历史订单  WHERE 1=1 AND customid=#{customid}*/							
	@Select("SELECT * FROM db_order WHERE 1=1 and customid=#{customid}")
	public List<Order> getHistoryOrderByCustomId(String customid);

	/* 查询预约单*/
	@Select("SELECT * FROM db_order WHERE 1=1 AND orderId=#{orderId}")
	public List<Order> getReservationOrder(String customid);

	/* 新建订单 */
	@Insert("INSERT INTO db_order (citycode,useriphone,paytype,fee,state,"
			+ "orderId,customid,nickName,sex,getOnTime,lastReplTime,contractTaxi,"
			+ "vipMark,reservation,takeTaxiType,serverLevel,firstChoiceCompany,"
			+ "personCount,getOnLon,getOnLat,getOffLon,getOffLat,getOnPlaceName,"
			+ "getOffPlaceName,notes,motorcycleType,anotherCellPhoneNo,specialRequirements,"
			+ "orderCreateTime,cityResponse2CenterTime,taxiResponse2CityTime)"
			+ " VALUES (#{citycode}, #{useriphone}, #{paytype}, #{fee}, #{state},"
			+ " #{orderId}, #{customid}, #{nickName}, #{sex}, #{getOnTime}, #{lastReplTime},"
			+ " #{contractTaxi}, #{vipMark}, #{reservation}, #{takeTaxiType}, #{serverLevel},"
			+ " #{firstChoiceCompany}, #{personCount}, #{getOnLon}, #{getOnLat}, #{getOffLon},"
			+ " #{getOffLat}, #{getOnPlaceName}, #{getOffPlaceName}, #{notes}, #{motorcycleType}, #{anotherCellPhoneNo},"
			+ " #{specialRequirements}, #{orderCreateTime}, #{cityResponse2CenterTime}, #{taxiResponse2CityTime})")
	public int insert(Order order);

	/* 修改订单 */
	@Update("UPDATE db_order SET orderId=#{orderId},citycode=#{citycode},useriphone=#{useriphone},paytype=#{paytype},"
			+ "fee=#{fee},state=#{state},orderId=#{orderId},customid=#{customid},nickName=#{nickName},sex=#{sex},"
			+ "getOnTime=#{getOnTime},lastReplTime=#{lastReplTime},contractTaxi=#{contractTaxi},vipMark=#{vipMark},"
			+ "reservation=#{reservation},takeTaxiType=#{takeTaxiType},serverLevel=#{serverLevel},firstChoiceCompany=#{firstChoiceCompany},"
			+ "personCount=#{personCount},getOnLon=#{getOnLon},getOnLat=#{getOnLat},getOffLon=#{getOffLon},getOffLat=#{getOffLat},"
			+ "getOnPlaceName=#{getOnPlaceName},getOffPlaceName=#{getOffPlaceName},notes=#{notes},motorcycleType=#{motorcycleType},"
			+ "anotherCellPhoneNo=#{anotherCellPhoneNo},specialRequirements=#{specialRequirements},orderCreateTime=#{orderCreateTime},"
			+ "cityResponse2CenterTime=#{cityResponse2CenterTime},taxiResponse2CityTime=#{taxiResponse2CityTime}"
			+ " where orderId=#{orderId}"
			)
	public int updateOrder(Order order);
	
	/*取消订单*/
	@Update("UPDATE db_order SET state=1 where orderId=#{orderId}")
	public int cancelOrder(long orderId);
	
	
	/* 删除订单 */
	@Delete("DELETE FROM db_order WHERE orderId = #{orderId}")
	public int deleteOrder(long orderId);
}
