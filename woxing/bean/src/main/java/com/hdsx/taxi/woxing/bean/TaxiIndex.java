package com.hdsx.taxi.woxing.bean;

import java.util.ArrayList;
import java.util.List;

import com.hdsx.taxi.woxing.bean.util.coor.CoordinateCodec;
import com.vividsolutions.jts.geom.Polygon;

/**
 * 打车指数
 * 
 * @author Steven
 * 
 */
public class TaxiIndex {

	public static final byte MAX_LEVEL = 5;
	byte index = 0;// 打车指数
	int waittime;// 预计等待时间

	public int getWaittime() {
		return waittime;
	}

	public void setWaittime(int waittime) {
		this.waittime = waittime;
	}

	List<Polygon> zones = new ArrayList<Polygon>(); // 打车区域
	String zonestring;

	public String getZonestring() {
		return zonestring;
	}

	public void setZonestring(String zonestring) {
		this.zonestring = zonestring;
	}

	public byte getIndex() {
		return index;
	}

	public void setIndex(byte index) {
		this.index = index;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("TaxiIndex [index=").append(index);
		sb.append(" Waittime=").append(this.waittime);

		for (Polygon p : this.zones) {
			if (p != null)
				sb.append(" ").append(p.toString());
		}
		sb.append("]");
		return sb.toString();
	}

	public List<Polygon> getPolygons() {
		return this.zones;
	}

	public String getZones() {
		StringBuilder sb = new StringBuilder();
		for (Polygon p : this.zones) {
			sb.append("polygon:").append(CoordinateCodec.encodeCoors(p));
		}
		return sb.toString();
	}

	public void addPolygon(Polygon poly) {
		this.zones.add(poly);

	}

	public void addAllPolygon(List<Polygon> list) {
		this.zones.addAll(list);
	}

}
