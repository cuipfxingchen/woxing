package com.hdsx.taxi.woxing.order;

import java.util.List;

import com.hdsx.taxi.woxing.bean.CarInfo;
import com.hdsx.taxi.woxing.bean.Order;
import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1005;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1006;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1007;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1009;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1010;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1012;
import com.hdsx.taxi.woxing.mqutil.message.order.MQMsg1013;


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
	 * 告诉乘客订单通知到司机的数量
	 * @param msg
	 */
	public void noticeDriverCount(MQMsg1013 msg);
	
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
	 * @param b 
	 * @return
	 */
	public boolean cancelOrderByDriver(long orderid, byte b);

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
	 * 更新订单号
	 * @param newOrderId
	 * @param oldOrderId
	 */
	public void update(long newOrderId,long oldOrderId);
	
	/**
	 * 更新订单信息
	 * 
	 * @param l
	 *            订单号
	 * @param c
	 *            车辆信息
	 */
	public void doSucess(long l, CarInfo c);

	/**
	 * 处理失败订单
	 * 
	 * @param orderid
	 *            订单号
	 * @param describtion
	 *            失败原因
	 * @param code
	 *            失败编码
	 */
	void doFail(long l, String describtion, byte code);
	
	/**
	 * 取消订单
	 * @param l
	 * @param reason
	 * @return  0：取消成功,2： 找不到订单   1：取消失败
	 */
	byte cancelOrderByPassenger(long l,byte reason,String customid);

	/**
	 * 开始执行预约订单
	 * @param msg
	 */
	public void startReversation(MQMsg1005 msg);
	
	/**
	 * 查询订单状态
	 * @param orderId
	 */
	byte getOrderState(long orderId,String customid,String citycode);


	/**
	 * 电召平台推送乘客已上车
	 * @param msg
	 */
	public void passengerGeton(MQMsg1007 msg);

	/**
	 * 主动请求乘客已上车
	 * @param msg
	 */
	public byte passengerGeton(long orderId,double lon,double lat,String customid,String citycode);

	/**
	 * 付款通知
	 * @param msg
	 */
	void payMoney(MQMsg1006 msg);
	

	/**
	 * 乘客位置上傳
	 * @param msg
	 */
	boolean upPassengerSite(long orderId, double lon, double lat,
			String customid, String citycode);

	
	/**
	 * 乘客反饋評價
	 * @param orderId
	 * @param type
	 * @param desc
	 * @param customid
	 * @param citycode
	 * @return
	 */
	boolean backPassegerEstimate(long orderId, byte type, String desc,
			String customid, String citycode);
	
	
	/**
	 * 付款成功结果提交
	 * @param orderId
	 * @param type
	 * @param desc
	 * @param customid
	 * @param citycode
	 * @return
	 */
	boolean payMoney(long orderId,byte type,String desc,String customid,String citycode);
	
	/**
	 * 根据订单id查询用户
	 * @param orderId
	 * @return
	 */
	String getMqCustomid(long orderId);
	
	/**
	 * 向乘客推送司机位置信息
	 * @param msg
	 */
	void driverSitePush(MQMsg1012 msg);
}
