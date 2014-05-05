package com.hdsx.taxi.woxing.order;

import java.util.Date;
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
import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;
import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg3002;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg0001;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg0002;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg0003;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1002;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1003;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1005;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1006;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1007;
import com.hdsx.taxi.woxing.mqutil.msgpool.MQMsgPool;
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
	MQMsgPool msgpool;

	@Inject
	public OrderService(IXMPPService xmpp, OrderMapper om, OrderPool pool,
			MQMsgPool msgpool) {
		this.xmppservice = xmpp;
		this.orderMapper = om;
		this.orderpool = pool;
		this.msgpool = msgpool;
	}

	@Override
	public int submit(Order order) {
		if (logger.isDebugEnabled()) {
			logger.debug("submit(Order) - start"); //$NON-NLS-1$
		}

		try {
			order.setOrderId(new Date().getTime());
			MQMsg0001 msg = new MQMsg0001(order.getCustomid());
			msg.setOrderId(order.getOrderId());
			msg.setRevesation(order.getReservation() == 1 ? true : false);
			msg.setGetOnTime(order.getGetOnTime());
			msg.setGetOnPlaceName(order.getGetOnPlaceName());
			msg.setGetOnLat(order.getGetOnLat());
			msg.setGetOnLon(order.getGetOnLon());
			msg.setGetOffPlaceName(order.getGetOffPlaceName());
			msg.setGetOffLat(order.getGetOffLat());
			msg.setGetOffLon(order.getGetOffLon());
			msg.setNotes(order.getNotes());

			if (logger.isInfoEnabled()) {
				logger.info("submit(Order)" + order.getNickName()); //$NON-NLS-1$
			}

			msg.setNickName(order.getNickName());
			msg.setSex(order.getSex());
			msg.setUserphone(order.getUseriphone());
			msg.setFirstChoiceCompany(order.getFirstChoiceCompany());
			msg.setContractTaxi(order.getContractTaxi());
			msg.setVipMark(order.getVipMark() + "");

			orderpool.put(order);
			orderMapper.insert(order);
			logger.info("mq发送开始");
			MQService.getInstance().sendMsg(order.getCitycode(), msg);
			logger.info("mq发送结束");
			

			if (logger.isDebugEnabled()) {
				logger.debug("submit(Order) - end"); //$NON-NLS-1$
			}
			return 1;

		} catch (Exception ex) {
			logger.error("提交订单失败:" + ex);
			ex.printStackTrace();

			if (logger.isDebugEnabled()) {
				logger.debug("submit(Order) - end"); //$NON-NLS-1$
			}
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
	 * 更新订单号
	 * 
	 * @param newOrderId
	 * @param oldOrderId
	 */
	@Override
	public void update(long newOrderId, long oldOrderId) {
		Order order1 = this.orderpool.getOrder(oldOrderId);
		logger.debug("oldorder:"+(order1==null));
		this.orderpool.remove(order1);
		order1.setOrderId(newOrderId);
		order1.setState(Order.STATE_SENDED);
		this.orderpool.put(order1);
		orderMapper.updateOrderId(oldOrderId, newOrderId);
//		this.orderpool.updateOrderId(oldOrderId, newOrderId);
		
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
		orderMapper.updateOrder(order);

		HashMap<String, Object> result = new HashMap<>();

		result.put("orderid", order.getOrderId());
		result.put("carnum", c.getLisencenumber());
		result.put("driver_tel", c.getDriverphone());

		XMPPBean<HashMap> bean = new XMPPBean<>();
		bean.setMsgid(0x0001);
		bean.setResult(result);
		this.xmppservice.sendMessage(order.getCustomid(), bean);

	}

	/**
	 * 处理失败的消息
	 */
	@Override
	public void doFail(long l, String describ, byte code) {
		Order order = this.orderpool.getOrder(l);
		order.setState(code);
		this.orderpool.remove(order);
		orderMapper.updateOrder(order);
		HashMap map = new HashMap();
		map.put("orderid", l);
		map.put("msg", describ);
		XMPPBean<HashMap> bean = new XMPPBean<>();
		bean.setMsgid(0x0002);
		bean.setResult(map);
		this.xmppservice.sendMessage(order.getCustomid(), bean);
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
		MQAbsMsg returnmsg = msgpool.getMsg(order.getCustomid(), 0x1003);
		if (returnmsg == null)
			return false;
		if (!returnmsg.getClass().isInstance(MQMsg1003.class))
			return false;
		MQMsg1003 rm = (MQMsg1003) returnmsg;
		if (rm.getCancle() == 0) {
			orderMapper.updateOrder(order);
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 开始执行预约订单
	 */
	@Override
	public void startReversation(MQMsg1005 msg) {

		long orderid = msg.getOrderid();
		Order o = this.orderpool.getOrder(orderid);
		o.getResult().setCarNum(msg.getCarLicensenumber());
		o.setState(Order.STATE_OPERATING);// 预约订单开始执行状态
		this.orderpool.onProduce(o);

		XMPPBean<HashMap> bean = new XMPPBean<>();
		bean.setMsgid(0x0006);
		HashMap map = new HashMap<>();
		map.put("orderid", msg.getOrderid());
		map.put("carnum", msg.getCarLicensenumber());
		map.put("lat", msg.getLat());
		map.put("lon", msg.getLon());
		bean.setResult(map);
		xmppservice.sendMessage(o.getCustomid(), bean);

	}

	@Override
	public byte getOrderState(long orderId, String customid, String citycode) {
		MQMsg0002 mqmsg = new MQMsg0002(customid);
		mqmsg.setOrderId(orderId);
		MQService.getInstance().sendMsg(citycode, mqmsg);
		MQAbsMsg returnmsg = msgpool.getMsg(customid, 0x1002);
		if (returnmsg == null)
			return 0;
		if (!returnmsg.getClass().isInstance(MQMsg1002.class))
			return 0;
		MQMsg1002 rm = (MQMsg1002) returnmsg;
		return rm.getState();
	}

	@Override
	public void payMoney(MQMsg1006 msg) {
		Order order = orderpool.getOrder(msg.getOrderid());
		order.setState(Order.STATE_FUKUAN);
		orderpool.put(order);
		orderMapper.updateOrder(order);
		XMPPBean<HashMap> bean = new XMPPBean<>();
		bean.setMsgid(0x0004);
		HashMap map = new HashMap<>();
		map.put("orderid", msg.getOrderid());
		map.put("cost", msg.getFee());
		map.put("fee", msg.getFee2());
		bean.setResult(map);
		xmppservice.sendMessage(order.getCustomid(), bean);
	}

	@Override
	public void passengerGeton(MQMsg1007 msg) {
		Order order = orderpool.getOrder(msg.getOrderid());
		orderMapper.updateOrder(order);
		XMPPBean<HashMap> bean = new XMPPBean<>();
		bean.setMsgid(0x0005);
		HashMap map = new HashMap<>();
		map.put("orderid", msg.getOrderid());
		map.put("lon", msg.getLon());
		map.put("lat", msg.getLat());	
		bean.setResult(map);
		xmppservice.sendMessage(order.getCustomid(), bean);

	}

}
