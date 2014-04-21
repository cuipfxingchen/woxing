package com.hdsx.taxi.woxing.order;

import java.util.List;

import com.google.inject.Singleton;
import com.hdsx.taxi.woxing.bean.CarInfo;
import com.hdsx.taxi.woxing.bean.Order;

/**
 * 订单服务实现类
 * @author Steven
 *
 */
@Singleton
public class OrderService implements IOrderService {

	@Override
	public int submit(Order order) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Order> getHistoryOrder(String customid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getReservationOrder(String customid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean cancelOrder(long orderid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CarInfo queryCarInfoByOrder(long orderid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Order order) {
		// TODO Auto-generated method stub
		return false;
	}

}
