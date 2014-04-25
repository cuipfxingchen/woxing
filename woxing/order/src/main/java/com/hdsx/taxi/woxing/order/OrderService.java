package com.hdsx.taxi.woxing.order;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.hdsx.taxi.woxing.bean.CarInfo;
import com.hdsx.taxi.woxing.bean.Order;
import com.hdsx.taxi.woxing.dao.OrderMapper;
import com.hdsx.taxi.woxing.mqutil.MQService;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg0001;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg0003;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1005;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1006;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1007;
import com.hdsx.taxi.woxing.xmpp.IXMPPService;
import com.hdsx.taxi.woxing.xmpp.XMPPBean;

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
			msg.setUserphone(order.getUseriphone());
			MQService.getInstance().sendMsg(order.getCitycode(), msg);

			orderpool.put(order);
			orderMapper.insert(order);
			return 1;

		} catch (Exception ex) {
			logger.error("提交订单失败:" + ex);
			ex.printStackTrace();
			return 0;
		}

	}

	/**
	 * 查询历史订单
	 * 
	 * @param customid
	 * @return
	 */
	@Override
	public List<Order> getHistoryOrder(String customid) {
		return orderMapper.getHistoryOrderByCustomId(customid);
	}

	/**
	 * 查询预约订单
	 * 
	 * @param customid
	 * @return
	 */
	@Override
	public List<Order> getReservationOrder(String customid) {
		return orderMapper.getReservationOrder(customid);

	}

	/**
	 * 驾驶员取消订单
	 * 
	 * @param orderid
	 * @return
	 */
	@Override
	public boolean cancelOrderByDriver(long orderid, byte reason) {

		Order o = this.orderpool.getOrder(orderid);
		o.setState(Order.STATE_CANCEL_BY_DRIVE);
		this.orderpool.remove(o);

		this.orderMapper.updateOrder(o);

		XMPPBean<HashMap> bean = new XMPPBean<>();
		bean.setMsgid(0x0003);
		HashMap map = new HashMap<>();
		map.put("orderid", orderid);
		map.put("msg", reason);
		bean.setResult(map);

		xmppservice.sendMessage(o.getCustomid(), bean);
		return true;
	}

	/**
	 * 通过订单查询车辆信息
	 * 
	 * @param orderid
	 * @return
	 */
	@Override
	public CarInfo queryCarInfoByOrder(long orderid) {
		Order order = orderMapper.getOrderById(orderid);
		// TODO.......通过订单查询关联车辆信息
		return null;
	}

	/**
	 * 更新订单，包括状态
	 * 
	 * @param order
	 * @return
	 */
	@Override
	public boolean update(Order order) {

		return true;
	}

	/**
	 * 收到订单成功消息
	 */
	@Override
	public void doSucess(long l, CarInfo c) {

		Order order = this.orderpool.getOrder(l);
		order.setState(Order.STATE_HASCAR);
		order.getResult().setDriver_name(c.getDriverName());
		order.getResult().setDriver_tel(c.getDriverphone());
		orderpool.put(order);

		HashMap<String, Object> result = new HashMap<>();

		result.put("orderid", order.getOrderId());
		result.put("carNum", c.getLisencenumber());
		result.put("driver_tel", c.getDriverphone());

		XMPPBean<HashMap> bean = new XMPPBean<>();
		bean.setMsgid(0x0001);
		bean.setResult(result);
		this.xmppservice.sendMessage(order.getCustomid(), bean);
		orderMapper.updateOrder(order);

	}

	/**
	 * 处理失败的消息
	 */
	@Override
	public void doFail(long l, String describ, byte code) {
		Order order = this.orderpool.getOrder(l);
		order.setState(code);
		HashMap map = new HashMap();
		map.put("orderid", l);
		map.put("msg", describ);
		XMPPBean<HashMap> bean = new XMPPBean<>();
		bean.setMsgid(0x0002);
		bean.setResult(map);
		this.xmppservice.sendMessage(order.getCustomid(), bean);
		orderMapper.updateOrder(order);
	}

	/**
	 * 乘客取消订单
	 */
	@Override
	public boolean cancelOrderByPassenger(long l, byte reason) {
		Order order = this.orderpool.getOrder(l);
		if (order == null)
			return true;
		order.setState(Order.STATE_CANCEL_BY_PASS);

		this.orderpool.remove(order);
		MQMsg0003 mqmsg = new MQMsg0003(order.getCustomid());

		mqmsg.setOrderId(order.getOrderId());
		// mqmsg.setCancel(reason);
		mqmsg.setCausecode(reason);
		mqmsg.setPassengerName(order.getNickName());
		mqmsg.setPassengerPhone(order.getUseriphone());
		MQService.getInstance().sendMsg(order.getCitycode(), mqmsg);
		orderMapper.updateOrder(order);

		return true;

	}

	/**
	 * 开始执行预约订单
	 */
	@Override
	public void startReversation(MQMsg1005 msg) {

		long orderid = msg.getOrderid();
		Order o = this.orderpool.getOrder(orderid);
		o.getResult().setCarNum(msg.getCarLicensenumber());
		this.orderpool.onProduce(o);

	}

	/**
	 * 付款
	 */
	@Override
	public void onPay(MQMsg1006 msg) {
		Order o = this.orderpool.getOrder(msg.getOrderid());
		o.setState(Order.STATE_PAYED);
		o.setFee(msg.getFee());
		o.setFee2(msg.getFee2());
		HashMap map = new HashMap();
		map.put("orderid", msg.getOrderid());
		map.put("cost", msg.getFee());
		map.put("fee", msg.getFee2());
		XMPPBean<HashMap> bean = new XMPPBean<>();
		bean.setMsgid(0x0004);
		bean.setResult(map);
		this.xmppservice.sendMessage(o.getCustomid(), bean);
		this.orderpool.put(o);
		this.orderMapper.updateOrder(o);

	}

	@Override
	public void passengerGeton(MQMsg1007 msg) {
		Order o = this.orderpool.getOrder(msg.getOrderid());
		o.setState(Order.STATE_PASSAGER_ON);
		HashMap map = new HashMap();
		map.put("orderid", msg.getOrderid());
		map.put("lat", msg.getLat());
		map.put("lon", msg.getLon());
		XMPPBean<HashMap> bean = new XMPPBean<>();
		bean.setMsgid(0x0005);
		bean.setResult(map);
		this.xmppservice.sendMessage(o.getCustomid(), bean);
		this.orderpool.put(o);
		this.orderMapper.updateOrder(o);
		
	}
}
