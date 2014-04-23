package com.hdsx.taxi.woxing.order;

import java.util.List;

import com.hdsx.taxi.woxing.bean.CarInfo;
import com.hdsx.taxi.woxing.bean.Order;

/**
 * 订单服务接口
 * 
 * @author Steven
 * 
 */
public interface IOrderService {

	/**
	 * 提交订单
	 * 
	 * @param order
	 * @return
	 */
	public int submit(Order order);

	/**
	 * 查询历史订单
	 * 
	 * @param customid
	 * @return
	 */
	public List<Order> getHistoryOrder(String customid);

	/**
	 * 查询预约订单
	 * 
	 * @param customid
	 * @return
	 */
	public List<Order> getReservationOrder(String customid);

	/**
	 * 取消订单
	 * 
	 * @param orderid
	 * @return
	 */
	public boolean cancelOrder(long orderid);

	/**
	 * 通过订单查询车辆信息
	 * 
	 * @param orderid
	 * @return
	 */
	public CarInfo queryCarInfoByOrder(long orderid);

	/**
	 * 更新订单，包括状态
	 * 
	 * @param order
	 * @return
	 */
	public boolean update(Order order);

	/**
	 * 更新订单信息
	 * @param l  订单号
	 * @param c  车辆信息
	 */
	public void doSucess(long l, CarInfo c);

}
