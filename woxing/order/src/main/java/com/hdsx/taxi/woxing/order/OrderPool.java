package com.hdsx.taxi.woxing.order;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.hdsx.taxi.woxing.bean.Order;
import com.hdsx.taxi.woxing.bean.util.CacheManagerUtil;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1001;

/**
 * 订单池
 * 
 * @author Steven
 * 
 */
@Singleton
public class OrderPool {

	private static final String ORDERPOOL_CACHE_NAME = "orderpoolcache";
	private static final String CUSTOMPOOL_CACHE_NAME = "customordermapperpoolcache";
	Ehcache pool;
	Ehcache custompool;

	@Inject
	public OrderPool(CacheManagerUtil cm) {
		this.pool = cm.getCm().getEhcache(ORDERPOOL_CACHE_NAME);
		this.custompool = cm.getCm().getEhcache(CUSTOMPOOL_CACHE_NAME);
	}

	/**
	 * 通过订单号获取订单
	 * 
	 * @param orderid
	 * @return
	 */
	public Order getOrder(long orderid) {

		Element e = this.pool.get(orderid);
		if (e != null)
			return (Order) e.getObjectValue();
		return null;
	}

	/**
	 * 通过customid查询当前订单
	 * 
	 * @param customid
	 * @return
	 */
	public Order getCurOrder(String customid) {

		Element e = custompool.get(customid);
		if (e != null) {
			CutomOrderMapper mapper = (CutomOrderMapper) e.getObjectValue();

			Element ele = pool.get(mapper.getCurOrderid());
			if (ele != null)
				return (Order) ele.getObjectValue();

		}
		return null;
	}

	/**
	 * 根据customid查询预约订单
	 * 
	 * @param customid
	 * @return
	 */
	public List<Order> getReservationOrder(String customid) {
		Element e = custompool.get(customid);
		if (e != null) {
			CutomOrderMapper mapper = (CutomOrderMapper) e.getObjectValue();

			List<Order> list = new ArrayList<>();

			for (long oid : mapper.getReorderlist()) {
				Element ele = pool.get(oid);

				list.add((Order) ele.getObjectValue());
			}
			return list;
		}
		return null;
	}

	/**
	 * 增加订单
	 * 
	 * @param order
	 */
	public void put(Order order) {
		Element e = new Element(order.getOrderId(), order);
		Element customelement = custompool.get(order.getCustomid());
		if (customelement == null) {
			CutomOrderMapper mapper = new CutomOrderMapper();
			mapper.setCustomid(order.getCustomid());
			if (order.getReservation()==1) {
				mapper.getReorderlist().remove(order.getOrderId());
				mapper.getReorderlist().add(order.getOrderId());
			} else {
				mapper.setCurOrderid(order.getOrderId());
			}
			customelement = new Element(order.getCustomid(), mapper);

		} else {
			CutomOrderMapper mapper = (CutomOrderMapper) customelement
					.getObjectValue();
			if (order.getReservation()==1) {
				mapper.getReorderlist().remove(order.getOrderId());
				mapper.getReorderlist().add(order.getOrderId());
			} else {
				mapper.setCurOrderid(order.getOrderId());
			}
		}
		this.custompool.put(customelement);
		this.pool.put(e);
	}
	
	public void putPool (Order order) {
		Element e = new Element(order.getOrderId(), order);
		this.pool.put(e);
	}

	public void update(MQMsg1001 msg) {
		long orderid = msg.getOrderId();
		Element e = pool.get(orderid);
		if (e == null)
			return;
		Order o = (Order) e.getObjectValue();
		o.getResult().setCar_company(msg.getCommpany());

		// TODO 完善Order属性更新

		pool.put(e);

		// TODO 将Order对象存入数据库

	}

	/**
	 * 从订单池中移除订单对象
	 * 
	 * @param o
	 */
	public void remove(Order o) {
		this.pool.remove(o.getOrderId());

		Element e = this.custompool.get(o.getCustomid());
		if (e == null) {
			return;
		}

		CutomOrderMapper om = (CutomOrderMapper) e.getObjectValue();
		if (om.getCurOrderid() == o.getOrderId()) {
			om.setCurOrderid(0);
		} else {

			for (int i = 0; i < om.getReorderlist().size(); i++) {
				if (o.getOrderId() == om.getReorderlist().get(i)) {
					om.getReorderlist().remove(i);
				}
			}

			this.custompool.put(e);
		}

	}

	/**
	 * 开始执行预约订单
	 * 
	 * @param o
	 */
	public void onProduce(Order o) {

		this.put(o);
		Element e = this.custompool.get(o.getCustomid());
		if (e == null)
			return;
		CutomOrderMapper cm = (CutomOrderMapper) e.getObjectValue();
		cm.setCurOrderid(o.getOrderId());
		cm.getReorderlist().remove(o.getOrderId());

	}

	/**
	 * 更新订单号
	 * 
	 * @param oldid
	 * @param newid
	 */
	public void updateOrderId(long oldid, long newid) {
		Order o = this.getOrder(oldid);
		o.setOrderId(newid);
		this.pool.remove(oldid);
		this.pool.put(new Element(o.getOrderId(), o));

		Element e = this.custompool.get(o.getCustomid());
		if (e != null) {
			CutomOrderMapper m = (CutomOrderMapper) e.getObjectValue();
			if (m.getCurOrderid() == oldid) {
				m.setCurOrderid(newid);
			} else {
				m.getReorderlist().remove(oldid);
				m.getReorderlist().add(newid);
			}
			this.custompool.put(new Element(o.getCustomid(), m));

		}

	}

	/**
	 * 乘客Id和订单信息的关联
	 * 
	 * @author Steven
	 * 
	 */
	class CutomOrderMapper {
		String customid; // 乘客id
		long curOrderid; // 当前订单id
		List<Long> reorderlist = new ArrayList<Long>(); // 预约订单id

		public String getCustomid() {
			return customid;
		}

		public void setCustomid(String customid) {
			this.customid = customid;
		}

		public long getCurOrderid() {
			return curOrderid;
		}

		public void setCurOrderid(long curOrderid) {
			this.curOrderid = curOrderid;
		}

		public List<Long> getReorderlist() {
			return reorderlist;
		}

		public void setReorderlist(List<Long> reorderlist) {
			this.reorderlist = reorderlist;
		}

	}

}
