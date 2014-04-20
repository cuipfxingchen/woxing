package com.hdsx.taxi.woxing.web.service;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.hdsx.taxi.woxing.bean.CarInfo;
import com.hdsx.taxi.woxing.mqutil.MQService;
import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;
import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg2001;
import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg2002;
import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg3001;
import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg3002;
import com.hdsx.taxi.woxing.mqutil.msgpool.MQMsgPool;

/**
 * 位置相关服务
 * 
 * @author Steven
 * 
 */
@Singleton
public class LocationService {

	MQService ms;
	MQMsgPool msgpool;

	@Inject
	public LocationService(MQMsgPool msgpool) {

		this.msgpool = msgpool;
		this.ms = MQService.getInstance();
	}

	/**
	 * 根据坐标范围寻找车辆
	 * 
	 * @param x
	 * @param y
	 * @param r
	 * @param citycode
	 * @return
	 */
	public List<CarInfo> findCars(double lat, double lon, short r,
			String citycode, String customid) {
		MQMsg2001 msg = new MQMsg2001(customid);
		msg.setLat(lat);
		msg.setLon(lon);
		msg.setRange(r);
		ms.sendMsg(citycode, msg);
		MQAbsMsg returnmsg = msgpool.getMsg(customid, 0x3001);
		if (returnmsg == null)
			return new ArrayList<>();
		if (!returnmsg.getClass().isInstance(MQMsg3001.class))
			return new ArrayList<>();
		MQMsg3001 m = (MQMsg3001) returnmsg;
		return m.getCars();

	}

	/**
	 * 根据订单id查询目标车辆信息
	 * 
	 * @param citycode
	 *            城市代码
	 * @param customid
	 *            用户名
	 * @param orderId
	 *            订单id
	 * @return 车辆信息
	 */
	public CarInfo getCarInfoByCarNum(String citycode, String customid,
			long orderId) {

		// 消息体
		MQMsg2002 msg = new MQMsg2002(customid);
		msg.setOrderid(orderId);
		// 调用发送信息内
		ms.sendMsg(citycode, msg);
		MQAbsMsg returnmsg = msgpool.getMsg(customid, 0x3002);
		if (returnmsg == null)
			return new CarInfo();
		if (!returnmsg.getClass().isInstance(MQMsg3002.class))
			return new CarInfo();
		MQMsg3002 rm = (MQMsg3002) returnmsg;
		CarInfo ci = new CarInfo();
		ci.setId(rm.getCar_number());
		ci.setDriverName(rm.getDriver_name());
		ci.setLisencenumber(rm.getDriver_tel());
		ci.setLat(rm.getLat());
		ci.setLon(rm.getLon());
		ci.setCompany(rm.getCommpany());
		return ci;
	}

}
