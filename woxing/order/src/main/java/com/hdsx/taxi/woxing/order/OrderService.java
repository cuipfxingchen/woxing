package com.hdsx.taxi.woxing.order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.jms.JMSException;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.hdsx.gis.coortransfor.EvilTransform;
import com.hdsx.taxi.woxing.bean.CarInfo;
import com.hdsx.taxi.woxing.bean.Order;
import com.hdsx.taxi.woxing.dao.OrderMapper;
import com.hdsx.taxi.woxing.mqutil.MQService;
import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg0001;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg0003;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg0006;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg0007;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1003;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1005;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1006;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1007;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1010;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1011;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1012;
import com.hdsx.taxi.woxing.mqutil.msgpool.MQMsgPool;
import com.hdsx.taxi.woxing.mqutil.msgpool.ReturnMsgUtil;
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
			logger.debug("订单提交submit(Order) - start"); //$NON-NLS-1$
		}

		try {
			MQMsg0001 msg = new MQMsg0001(order.getCustomid());
			msg.setOrderId(order.getOrderId());
			// msg.setRevesation(order.getReservation() == 1 ? true : false);
			msg.setTakeTaxiType(order.getReservation());
			msg.setGetOnTime(order.getGetOnTime());
			msg.setGetOnPlaceName(order.getGetOnPlaceName());
			msg.setGetOnLat(order.getGetOnLat());
			msg.setGetOnLon(order.getGetOnLon());
			msg.setGetOffPlaceName(order.getGetOffPlaceName());
			msg.setGetOffLat(order.getGetOffLat());
			msg.setGetOffLon(order.getGetOffLon());
			msg.setNotes(order.getNotes() == null ? "" : order.getNotes());
			msg.setNickName(order.getNickName() == null ? "" : order
					.getNickName());
			msg.setSex(order.getSex());
			msg.setUserphone(order.getUseriphone());
			msg.setFirstChoiceCompany(order.getFirstChoiceCompany() == null ? ""
					: order.getFirstChoiceCompany());
			msg.setContractTaxi(order.getContractTaxi());
			msg.setVipMark(order.getVipMark() + "");
			order.setState(Order.STATE_SENDED);
			order.setOrderCreateTime(new Date());
			orderpool.put(order);
			orderMapper.insert(order);
			MQService.getInstance().sendMsg(order.getCitycode(), msg);

			if (logger.isDebugEnabled()) {
				logger.debug("订单提交submit(Order) - end"); //$NON-NLS-1$
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
		if (o != null) {
			this.orderpool.remove(o);
		} else {
			o = orderMapper.getOrderById(orderid);
		}
		if (o != null) {
			o.setState(Order.STATE_CANCEL_BY_DRIVE);
			this.orderMapper.updateOrder(o);

			XMPPBean<HashMap> bean = new XMPPBean<>();
			bean.setMsgid(0x0003);
			HashMap map = new HashMap<>();
			map.put("orderid", orderid);
			map.put("msg", reason);
			bean.setResult(map);
			xmppservice.sendMessage(o.getCustomid(), bean);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 通过订单查询车辆信息 不用
	 * 
	 * @param orderid
	 * @return
	 */
	@Override
	public CarInfo queryCarInfoByOrder(long orderid) {
		// Order o = this.orderpool.getOrder(orderid);
		// if (o != null) {
		// this.orderpool.remove(o);
		// } else {
		// o = orderMapper.getOrderById(orderid);
		// }
		// // TODO.......通过订单查询关联车辆信息
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
		logger.debug("oldorder:" + (order1 == null));
		if (order1 != null) {
			this.orderpool.remove(order1);
			order1.setOrderId(newOrderId);
			order1.setState(Order.STATE_START);
			this.orderpool.put(order1);
			orderMapper.updateOrderId(oldOrderId, newOrderId);
			if(order1.getReservation()==0){
				XMPPBean<HashMap> bean = new XMPPBean<>();
				bean.setMsgid(0x0008);
				HashMap map = new HashMap<>();
				map.put("orderid", newOrderId);
				bean.setResult(map);
				xmppservice.sendMessage(order1.getCustomid(), bean);
			}
		} else {
			logger.error("更新订单号错误【旧" + oldOrderId + "】+【新+" + newOrderId
					+ "】：订单池里面没有旧订单");
		}

		// this.orderpool.updateOrderId(oldOrderId, newOrderId);

	}

	/**
	 * 收到订单成功消息
	 */
	@Override
	public void doSucess(long l, CarInfo c) {

		Order order = this.orderpool.getOrder(l);
		if (order == null) {
			order = orderMapper.getOrderById(l);
		}
		if (order != null) {
			order.setState(Order.STATE_HASCAR);
			order.getResult().setDriver_name(c.getDriverName());
			order.getResult().setDriver_tel(c.getDriverphone());
			order.getResult().setCarNum(c.getLisencenumber());
			orderpool.put(order);
			orderMapper.updateOrder(order);

			HashMap<String, Object> result = new HashMap<>();
			double[] gd=EvilTransform.WGS84ToGCJ02(c.getLon(), c.getLat());
			result.put("orderid", order.getOrderId());
			result.put("carnum", c.getLisencenumber());
			result.put("driver_tel", c.getDriverphone());
			result.put("lon", gd[0]);
			result.put("lat", gd[1]);

			XMPPBean<HashMap> bean = new XMPPBean<>();
			if(order.getReservation()==0){
				bean.setMsgid(0x0001);
			}else{
				bean.setMsgid(0x0009);
			}
			bean.setResult(result);
			this.xmppservice.sendMessage(order.getCustomid(), bean);
		} else {
			logger.error("doSucess()订单池和数据库都没有订单【" + l + "】");
		}

	}

	/**
	 * 处理失败的消息
	 */
	@Override
	public void doFail(long l, String describ, byte code) {
		Order order = this.orderpool.getOrder(l);
		if (order == null) {
			order = orderMapper.getOrderById(l);
		}
		if (order != null) {
			order.setState(code);
			this.orderpool.remove(order);
			orderMapper.deleteOrder(order.getOrderId());
			HashMap map = new HashMap();
			map.put("orderid", l);
			map.put("msg", describ);
			XMPPBean<HashMap> bean = new XMPPBean<>();
			bean.setMsgid(0x0002);
			bean.setResult(map);
			this.xmppservice.sendMessage(order.getCustomid(), bean);
		} else {
			logger.error("doFail()订单池和数据库都没有订单【" + l + "】");
		}

	}

	@Override
	public byte cancelOrderByPassenger(long l, byte reason, String customid) {

		logger.debug("乘客取消订单");
		Order order = this.orderpool.getOrder(l);
		if (order == null) {
			order = orderMapper.getOrderById(l);
		} else {
			this.orderpool.remove(order);
		}
		if (order == null) {
			return 2;
		}
		order.setState(Order.STATE_CANCEL_BY_PASS);
		orderMapper.updateOrder(order);
		MQMsg0003 mqmsg = new MQMsg0003(order.getCustomid());

		mqmsg.setOrderId(order.getOrderId());
		// mqmsg.setCancel("不爽");
		mqmsg.setCausecode(reason);
		mqmsg.setCarNum(order.getResult().getCarNum() == null ? "" : order
				.getResult().getCarNum());
		mqmsg.setPassengerName(order.getNickName());
		mqmsg.setPassengerPhone(order.getUseriphone());

		MQService.getInstance().sendMsg(order.getCitycode(), mqmsg);

		ReturnMsgUtil getreturn = new ReturnMsgUtil();
		MQAbsMsg returnmsg = getreturn.getMsg(order.getCustomid(), 0x1003);
		if (returnmsg == null)
			return 1;
		if (!(returnmsg instanceof MQMsg1003))
			return 1;
		MQMsg1003 rm = (MQMsg1003) returnmsg;
		if (rm.getCancle() == 0) {
			orderMapper.updateOrder(order);
			return 0;
		} else {
			return 1;
		}
	}

	/**
	 * 开始执行预约订单
	 */
	@Override
	public void startReversation(MQMsg1005 msg) {

		long orderid = msg.getOrderid();
		Order order = orderpool.getOrder(msg.getOrderid());
		if (order == null) {
			order = orderMapper.getOrderById(orderid);
		}
		if (order != null) {
			order.getResult().setCarNum(msg.getCarLicensenumber());
			order.setState(Order.STATE_OPERATING);// 预约订单开始执行状态
			this.orderpool.onProduce(order);
			orderMapper.updateOrder(order);
			this.orderpool.putPool(order);
			XMPPBean<HashMap> bean = new XMPPBean<>();
			bean.setMsgid(0x0006);
			double[] gd=EvilTransform.WGS84ToGCJ02(msg.getLon(),msg.getLat());
			HashMap map = new HashMap<>();
			map.put("orderid", msg.getOrderid());
			map.put("carnum", msg.getCarLicensenumber());
			map.put("lat", gd[1]);
			map.put("lon", gd[0]);
			bean.setResult(map);
			xmppservice.sendMessage(order.getCustomid(), bean);
		} else {
			logger.error("startReversation()订单池和数据库都没有订单【" + orderid + "】");
		}

	}

	@Override
	public byte getOrderState(long orderId, String customid, String citycode) {
		// MQMsg0002 mqmsg = new MQMsg0002(customid);
		// mqmsg.setOrderId(orderId);
		// try {
		// MQService.getInstance().sendMsg(citycode, mqmsg);
		// } catch (JMSException e) {
		// e.printStackTrace();
		// //查询订单相关
		// }
		// MQAbsMsg returnmsg = msgpool.getMsg(customid, 0x1002);
		// if (returnmsg == null)
		// return 0;
		// if (!(returnmsg instanceof MQMsg1002))
		// return 0;
		// MQMsg1002 rm = (MQMsg1002) returnmsg;
		// return rm.getState();
		Order order = orderpool.getOrder(orderId);
		if (order == null) {
			order = orderMapper.getOrderById(orderId);
		}
		if (order == null) {
			return -1;
		} else {
			return order.getState();
		}

	}

	@Override
	public void payMoney(MQMsg1006 msg) {
		Order order = orderpool.getOrder(msg.getOrderid());
		if (order == null) {
			order = orderMapper.getOrderById(msg.getOrderid());
		}
		order.setFee(msg.getFee());
		order.setFee2(msg.getFee2());
		order.setState(Order.STATE_FUKUAN);
		orderpool.putPool(order);
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
		if (order == null) {
			order = orderMapper.getOrderById(msg.getOrderid());
		}
		order.setState(Order.STATE_PASSAGER_ON);
		orderpool.putPool(order);
		orderMapper.updateOrder(order);
		XMPPBean<HashMap> bean = new XMPPBean<>();
		bean.setMsgid(0x0005);
		double[] gd=EvilTransform.WGS84ToGCJ02(msg.getLon(),msg.getLat());
		HashMap map = new HashMap<>();
		map.put("orderid", msg.getOrderid());
		map.put("lat", gd[1]);
		map.put("lon", gd[0]);
		bean.setResult(map);
		xmppservice.sendMessage(order.getCustomid(), bean);

	}

	@Override
	public byte passengerGeton(long orderId, double lon, double lat,
			String customid, String citycode) {
		Order order = this.orderpool.getOrder(orderId);
		if (order == null) {
			order = orderMapper.getOrderById(orderId);
		} else {
			this.orderpool.remove(order);
		}
		if (order == null) {
			return 2;
		}
		order.setState(Order.STATE_PASSAGER_ON);
		orderpool.putPool(order);
		orderMapper.updateOrder(order);
		MQMsg1007 msg = new MQMsg1007();
		msg.getHead().setCustomId(order.getCustomid());
		msg.setOrderid(orderId);
		msg.setCarNum(order.getResult().getCarNum() == null ? "" : order
				.getResult().getCarNum());
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		msg.setTime(df.format(new Date()));
		msg.setLon(lon);
		msg.setLat(lat);

		MQService.getInstance().sendMsg(order.getCitycode(), msg);

		ReturnMsgUtil getreturn = new ReturnMsgUtil();
		MQAbsMsg returnmsg = getreturn.getMsg(order.getCustomid(), 0x0007);
		if (returnmsg == null)
			return 1;
		if (!(returnmsg instanceof MQMsg0007))
			return 1;
		MQMsg0007 rm = (MQMsg0007) returnmsg;
		if (rm.getCancle() == 0) {
			orderMapper.updateOrder(order);
			return 0;
		} else {
			return 1;
		}
	}

	@Override
	public boolean upPassengerSite(long orderId, double lon, double lat,
			String customid, String citycode) {
		MQMsg1010 msg = new MQMsg1010();
		msg.getHead().setCustomId(customid);
		msg.setOrderid(orderId);
		msg.setLon(lon);
		msg.setLat(lat);
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMddhhmmss");
		msg.setTime(df.format(new Date()));
		MQService.getInstance().sendMsg(citycode, msg);

		return true;
	}

	@Override
	public boolean backPassegerEstimate(long orderId, byte type, String desc,
			String customid, String citycode) {
		MQMsg1011 msg = new MQMsg1011();
		msg.getHead().setCustomId(customid);
		msg.setOrderid(orderId);
		msg.setType(type);
		msg.setDesc(desc);

		MQService.getInstance().sendMsg(citycode, msg);

		return true;
	}

	@Override
	public boolean payMoney(long orderId, byte type, String desc,
			String customid, String citycode) {
		Order order = this.orderpool.getOrder(orderId);
		if (order == null) {
			order = orderMapper.getOrderById(orderId);
		} else {
			this.orderpool.remove(order);
		}
		order.setState(Order.STATE_OVER);
		orderMapper.updateOrder(order);
		MQMsg0006 msg = new MQMsg0006();
		msg.getHead().setCustomId(customid);
		msg.setOrderId(orderId);
		msg.setCancle(type);
		msg.setExplain(desc);

		MQService.getInstance().sendMsg(citycode, msg);

		return true;
	}

	@Override
	public String getMqCustomid(long orderId) {
		Order order = this.orderpool.getOrder(orderId);
		if (order == null) {
			order = orderMapper.getOrderById(orderId);
		}
		if (order == null) {
			return null;
		} else {
			return order.getCustomid();
		}
	}

	@Override
	public void driverSitePush(MQMsg1012 msg) {

		long orderId = msg.getOrderid();
		XMPPBean<HashMap> bean = new XMPPBean<>();
		bean.setMsgid(0x0007);
		double[] gd=EvilTransform.WGS84ToGCJ02(msg.getLon(),msg.getLat());
		HashMap map = new HashMap<>();
		map.put("orderid", orderId);
		map.put("lat", gd[1]);
		map.put("lon", gd[0]);
		map.put("carNumber", msg.getCarNumber());
		bean.setResult(map);
		String customid = getMqCustomid(orderId);
		xmppservice.sendMessage(customid, bean);

	}

}
