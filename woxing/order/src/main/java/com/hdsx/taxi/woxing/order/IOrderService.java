package com.hdsx.taxi.woxing.order;

import com.hdsx.taxi.woxing.bean.Order;


/**
 * 订单服务接口
 * @author Steven
 *
 */
public interface IOrderService {

	public int submit(Order order);
}
