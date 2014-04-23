package com.hdsx.taxi.woxing.web.service;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.hdsx.taxi.woxing.bean.CarInfo;
import com.hdsx.taxi.woxing.bean.Index;
import com.hdsx.taxi.woxing.bean.Station;
import com.hdsx.taxi.woxing.mqutil.MQService;
import com.hdsx.taxi.woxing.mqutil.message.MQAbsMsg;
import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg2001;
import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg2002;
import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg2004;
import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg2005;
import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg2006;
import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg2007;
import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg3001;
import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg3002;
import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg3004;
import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg3005;
import com.hdsx.taxi.woxing.mqutil.message.location.MQMsg3006;
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

	/**
	 * 查询打车指数
	 * @param x 经度
	 * @param y 纬度
	 * @param citycode
	 * @param customid
	 * @return
	 */
	public int getCurLocationIndex(double x, double y,String citycode ,String customid) {
		// 消息体
		MQMsg2004 msg = new MQMsg2004(customid);
		msg.setLat(y);
		msg.setLon(x);

		// 调用发送信息内
		ms.sendMsg(citycode, msg);

		MQAbsMsg returnmsg = msgpool.getMsg(customid, 0x3004);
		if (returnmsg == null)
			return 0;
		if (!returnmsg.getClass().isInstance(MQMsg3004.class))
			return 0;
		MQMsg3004 rmsg = (MQMsg3004)returnmsg;

		if (rmsg != null) {
			return rmsg.getResult();
		} else {
			return 0;
		}
	}

	/**
	 * 查询打车热点区域
	 * @param x
	 * @param y
	 * @param dx
	 * @param dy
	 * @param citycode
	 * @param customid
	 * @return
	 */
	public List<Index> getTaxiIndex(double x, double y, double dx,
			double dy,String citycode, String customid) {
		// 消息体
		MQMsg2005 msg = new MQMsg2005(customid);
		msg.setLat(y);
		msg.setLon(x);
		msg.setDlat(dy);
		msg.setDlon(dx);

		// 调用发送信息内
		ms.sendMsg(citycode, msg);
		MQAbsMsg returnmsg = msgpool.getMsg(customid, 0x3005);
		if (returnmsg == null)
			return null;
		if (!returnmsg.getClass().isInstance(MQMsg3005.class))
			return null;
		MQMsg3005 rmsg = (MQMsg3005)returnmsg;

		if (rmsg != null) {
			return rmsg.getLs();
		} else {
			return null;
		}
	}

	/**
	 * 查询周边扬招站
	 * @param lat
	 * @param lon
	 * @param distance
	 * @param citycode
	 * @param customid
	 * @return
	 */
	public List<Station> getStation(double lat, double lon,
			int distance,String citycode, String customid) {
		// 消息体
		MQMsg2006 msg = new MQMsg2006(customid);
		msg.setLat(lat);
		msg.setLon(lon);
		msg.setRange((short) distance);

		// 调用发送信息内
		ms.sendMsg(citycode, msg);
		MQAbsMsg returnmsg = msgpool.getMsg(customid, 0x3006);
		if (returnmsg == null)
			return null;
		if (!returnmsg.getClass().isInstance(MQMsg3006.class))
			return null;
		MQMsg3006 rmsg = (MQMsg3006)returnmsg;

		if (rmsg != null) {
			return rmsg.getRl();
		} else {
			return null;
		}

	}

	/**
	 * 通过区域查询扬招站
	 * @param xlon
	 * @param ylat
	 * @param xdlon
	 * @param ydlat
	 * @param citycode
	 * @param customid
	 * @return
	 */
	public List<Station> getStationArea(double xlon,double ylat, double xdlon,
			 double ydlat,String citycode, String customid) {
		// 消息体
		MQMsg2007 msg = new MQMsg2007(customid);
		msg.setXlon(xlon);
		msg.setYlat(ylat);
		msg.setXdlon(xdlon);
		msg.setYdlat(ydlat);
		// 调用发送信息内
		ms.sendMsg(citycode, msg);
		MQAbsMsg returnmsg = msgpool.getMsg(customid, 0x3006);
		if (returnmsg == null)
			return null;
		if (!returnmsg.getClass().isInstance(MQMsg3006.class))
			return null;
		MQMsg3006 rmsg = (MQMsg3006)returnmsg;

		if (rmsg != null) {
			return rmsg.getRl();
		} else {
			return null;
		}

	}
}
