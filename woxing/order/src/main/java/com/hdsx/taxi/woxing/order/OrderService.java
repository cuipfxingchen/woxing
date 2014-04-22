package com.hdsx.taxi.woxing.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.hdsx.taxi.woxing.bean.CarInfo;
import com.hdsx.taxi.woxing.bean.Order;
import com.hdsx.taxi.woxing.dao.OrderMapper;
import com.hdsx.taxi.woxing.mqutil.MQService;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg0001;
import com.hdsx.taxi.woxing.xmpp.IXMPPService;

/**
 * 订单服务实现类
 * 
 * @author Steven
 * 
 */
@Singleton
public class OrderService implements IOrderService {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(OrderService.class);

	IXMPPService xmppservice;
	OrderMapper orderMapper;
	OrderPool orderpool;

	@Inject
	public OrderService(IXMPPService xmpp, OrderMapper om, OrderPool pool) {
		this.xmppservice = xmpp;
		this.orderMapper = om;
		this.orderpool = pool;
	}

	@Override
	public int submit(Order order) {
		try {

			MQMsg0001 msg = new MQMsg0001(order.getCustomid());
			msg.setRevesation(order.isReservation());
			msg.setGetOnTime(order.getGetOnTime());
			msg.setGetOnPlaceName(order.getGetOnPlaceName());
			msg.setGetOnLat(order.getGetOnLat());
			msg.setGetOnLon(order.getGetOnLon());
			msg.setGetOffPlaceName(order.getGetOffPlaceName());
			msg.setGetOffLat(order.getGetOffLat());
			msg.setGetOffLon(order.getGetOffLon());
			msg.setNotes(order.getNotes());

			msg.setNickName(order.getNickName());
			msg.setSex(order.getSex());
			// TODO 用户电话赋值 msg.setUserphone(order.getUserId());

			MQService.getInstance().sendMsg(order.getCitycode(), msg);
			orderpool.put(order);
			return 0;
		} catch (Exception ex) {
			logger.error("提交订单失败:" + ex);
			return 1;
		}

	}


	

	/**
	 * 查询历史订单
	 * 
	 * @param customid
	 * @return
	 */
	@Override
	public List<Order> getHistoryOrder(String customid){
		return orderMapper.getHistoryOrderByCustomId(customid);
	}

	/**
	 * 查询预约订单
	 * 
	 * @param customid
	 * @return
	 */
	@Override
	public List<Order> getReservationOrder(String customid){
		return orderMapper.getReservationOrder(customid);
		
	}

	/**
	 * 取消订单
	 * 
	 * @param orderid
	 * @return
	 */
	@Override
	public boolean cancelOrder(long orderid){
		
		return true;
	}

	/**
	 * 通过订单查询车辆信息
	 * 
	 * @param orderid
	 * @return
	 */
	@Override
	public CarInfo queryCarInfoByOrder(long orderid){
		Order order = orderMapper.getOrderById(orderid);
		//TBD.......通过订单查询关联车辆信息
		return null;
	}

	
	/**
	 * 更新订单，包括状态
	 * @param order
	 * @return
	 */
	@Override
	public boolean update(Order order){
		
		return true;
	}

}
