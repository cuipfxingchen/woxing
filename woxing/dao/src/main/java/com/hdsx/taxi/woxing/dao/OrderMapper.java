package com.hdsx.taxi.woxing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.hdsx.taxi.woxing.bean.Estimate;
import com.hdsx.taxi.woxing.bean.Order;

public interface OrderMapper {

	
	/* 查询订单 */
	@Results(value = {
			@Result( property = "result.carNum", column = "carNum", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result( property = "result.driver_name", column = "driver_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result( property = "result.driver_tel", column = "driver_tel", javaType = String.class, jdbcType = JdbcType.VARCHAR)
			
	}) 
	@Select("SELECT * FROM db_order WHERE orderId=#{orderId}")
	public Order getOrderById(long orderId);
	
	/* 查询历史订单  WHERE 1=1 AND customid=#{customid}*/
	@Results(value = {
			@Result( property = "result.carNum", column = "carNum", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result( property = "result.driver_name", column = "driver_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result( property = "result.driver_tel", column = "driver_tel", javaType = String.class, jdbcType = JdbcType.VARCHAR)
			
	}) 
	@Select("SELECT * FROM db_order t1 left join db_estimate t2 on t1.orderId=t2.orderId WHERE t1.customid=#{customid} and t1.state not in ('20','21') order by t1.getOnTime desc,t1.orderCreateTime desc")
	public List<Order> getHistoryOrderByCustomId(String customid);

	/* 查询预约单*/
	@Results(value = {
			@Result( property = "result.carNum", column = "carNum", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result( property = "result.driver_name", column = "driver_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result( property = "result.driver_tel", column = "driver_tel", javaType = String.class, jdbcType = JdbcType.VARCHAR)
			
	}) 
	@Select("SELECT * FROM db_order WHERE customid=#{customid} and reservation=1 and state!=100  order by getOnTime desc,orderCreateTime desc")
	public List<Order> getReservationOrder(String customid);

	/* 新建订单 */
	@Insert("INSERT INTO db_order (citycode,useriphone,paytype,fee,state,"
			+ "orderId,customid,nickName,sex,getOnTime,lastReplTime,contractTaxi,"
			+ "vipMark,reservation,takeTaxiType,serverLevel,firstChoiceCompany,"
			+ "personCount,getOnLon,getOnLat,getOffLon,getOffLat,getOnPlaceName,"
			+ "getOffPlaceName,notes,motorcycleType,anotherCellPhoneNo,specialRequirements,"
			+ "orderCreateTime,cityResponse2CenterTime,taxiResponse2CityTime,createLon,createLat)"
			+ " VALUES (#{citycode}, #{useriphone}, #{paytype}, #{fee}, #{state},"
			+ " #{orderId}, #{customid}, #{nickName}, #{sex}, #{getOnTime}, #{lastReplTime},"
			+ " #{contractTaxi}, #{vipMark}, #{reservation}, #{takeTaxiType}, #{serverLevel},"
			+ " #{firstChoiceCompany}, #{personCount}, #{getOnLon}, #{getOnLat}, #{getOffLon},"
			+ " #{getOffLat}, #{getOnPlaceName}, #{getOffPlaceName}, #{notes}, #{motorcycleType}, #{anotherCellPhoneNo},"
			+ " #{specialRequirements}, #{orderCreateTime}, #{cityResponse2CenterTime}, #{taxiResponse2CityTime}, #{createLon}, #{createLat})")
	public int insert(Order order);

	/* 修改订单 */
	@Update("UPDATE db_order SET orderId=#{orderId},citycode=#{citycode},useriphone=#{useriphone},paytype=#{paytype},"
			+ "fee=#{fee},fee2=#{fee2},state=#{state},orderId=#{orderId},customid=#{customid},nickName=#{nickName},sex=#{sex},"
			+ "getOnTime=#{getOnTime},lastReplTime=#{lastReplTime},contractTaxi=#{contractTaxi},vipMark=#{vipMark},"
			+ "reservation=#{reservation},takeTaxiType=#{takeTaxiType},serverLevel=#{serverLevel},firstChoiceCompany=#{firstChoiceCompany},"
			+ "personCount=#{personCount},getOnLon=#{getOnLon},getOnLat=#{getOnLat},getOffLon=#{getOffLon},getOffLat=#{getOffLat},"
			+ "getOnPlaceName=#{getOnPlaceName},getOffPlaceName=#{getOffPlaceName},notes=#{notes},motorcycleType=#{motorcycleType},"
			+ "anotherCellPhoneNo=#{anotherCellPhoneNo},specialRequirements=#{specialRequirements},orderCreateTime=#{orderCreateTime},"
			+ "cityResponse2CenterTime=#{cityResponse2CenterTime},taxiResponse2CityTime=#{taxiResponse2CityTime},carNum=#{result.carNum},"
			+ "driver_name=#{result.driver_name},driver_tel=#{result.driver_tel},finalOnLon=#{finalOnLon},finalOnLat=#{finalOnLat},finalOnTime=#{finalOnTime} "
			+ " where orderId=#{orderId}"
			)
	public int updateOrder(Order order);
	
	/**
	 * 更新订单号
	 * @param oldId
	 * @param newId
	 * @return
	 */
	@Update("update db_order set orderId=#{param2} where orderId=#{param1}")
	public int updateOrderId(long oldId,long newId);
	
	/*取消订单*/
	@Update("UPDATE db_order SET state=1 where orderId=#{orderId}")
	public int cancelOrder(long orderId);
	
	
	/* 删除订单 */
	@Delete("DELETE FROM db_order WHERE orderId = #{orderId}")
	public int deleteOrder(long orderId);
}
