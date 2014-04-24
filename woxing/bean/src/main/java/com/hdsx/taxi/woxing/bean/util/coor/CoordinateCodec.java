package com.hdsx.taxi.woxing.bean.util.coor;

import java.util.ArrayList;
import java.util.List;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Polygon;

/**
 * 坐标编解码工具
 * 
 * @author Steven
 * 
 */
public class CoordinateCodec {

	private static final int BLOCK_INT_BYTES = 5;
	private static final int BLOCK_MASK = 0x1F;
	private static final int ENCODE_ADDING_CONSTANT = 63;
	private static final int ENCODE_EXTERNAL_CONSTANT = 0x20;

	private static String encodeGeoPoint(int latitudeE6, int longitudeE6) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(encodeNumE5(intE6toE5(latitudeE6)));
		buffer.append(encodeNumE5(intE6toE5(longitudeE6)));
		return buffer.toString().trim();
	}

	private static String encodeNumE5(int numE5) {
		int pointInt = numE5 << 1;
		pointInt = (numE5 < 0) ? ~pointInt : pointInt;

		int block = 0;
		StringBuffer buffer = new StringBuffer();
		while ((pointInt >>> BLOCK_INT_BYTES) > 0) {
			block = pointInt & BLOCK_MASK;
			block |= ENCODE_EXTERNAL_CONSTANT;
			block += ENCODE_ADDING_CONSTANT;
			buffer.append((char) block);
			pointInt >>>= BLOCK_INT_BYTES;
		}
		buffer.append((char) (pointInt + ENCODE_ADDING_CONSTANT));
		return buffer.toString().trim();
	}

	public static String encodeCoors(Coordinate[] cors) {
		StringBuffer buffer = new StringBuffer();
		if (cors != null) {
			int lastLatE6 = 0;
			int lastLng = 0;

			int length = cors.length;
			for (int i = 0; i < length; ++i) {
				Coordinate cor = cors[i];

				int xE4 = doubletoIntE6(cor.x), yE4 = doubletoIntE6(cor.y);
				buffer.append(encodeGeoPoint(xE4 - lastLatE6, yE4 - lastLng));
				lastLatE6 = xE4;
				lastLng = yE4;
			}
		}
		return buffer.toString().trim();
	}

	@SuppressWarnings("unused")
	private static int doubletoIntE4(double value) {
		int numE4 = (int) Math.floor(value * 10E4);
		int numE5 = (int) Math.floor(value * 10E5);
		if (numE5 % 10 > 4) {
			numE4 += 1;
		}
		return numE4;
	}

	@SuppressWarnings("unused")
	private static int doubletoIntE5(double value) {
		int numE4 = (int) Math.floor(value * 10E5);
		int numE5 = (int) Math.floor(value * 10E6);
		if (numE5 % 10 > 4) {
			numE4 += 1;
		}
		return numE4;
	}

	private static int doubletoIntE6(double value) {
		int numE4 = (int) Math.floor(value * 10E6);
		int numE5 = (int) Math.floor(value * 10E7);
		if (numE5 % 10 > 4) {
			numE4 += 1;
		}
		return numE4;
	}

	private static int intE6toE5(int numE6) {
		int result = numE6 / 10;
		if (numE6 % 10 > 4) {
			++result;
		}
		return result;
	}

	/**
	 * 对polygon进行坐标编码
	 * 
	 * @param polygon
	 * @return
	 */
	public static String encodeCoors(Polygon polygon) {

		return encodeCoors(polygon.getCoordinates());
	}

	public static List<Coordinate> decodeCoors(String encoded) {

		List<Coordinate> list = new ArrayList<>();
		int index = 0, len = encoded.length();
		int lat = 0, lng = 0;

		while (index < len) {
			int b, shift = 0, result = 0;
			do {
				b = encoded.charAt(index++) - ENCODE_ADDING_CONSTANT;
				result |= (b & 0x1f) << shift;
				shift += 5;
			} while (b >= ENCODE_EXTERNAL_CONSTANT);
			int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			lat += dlat;

			shift = 0;
			result = 0;
			do {
				b = encoded.charAt(index++) - ENCODE_ADDING_CONSTANT;
				result |= (b & BLOCK_MASK) << shift;
				shift += BLOCK_INT_BYTES;
			} while (b >= ENCODE_EXTERNAL_CONSTANT);
			int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			lng += dlng;

			Coordinate c = new Coordinate(lat, lng);
			list.add(c);
		}
		return list;
	}

	/**
	 * 将int的坐标转为float，int表示1/10000分，float表示度
	 * 
	 * @param v
	 * @return
	 */
	public static float Coor2Float(int v) {

		return v / 60f / 10000f;
	}

	/**
	 * 将float的坐标转为int
	 * int表示1/10000分，float表示度
	 * @param v
	 * @return
	 */
	public static int Coor2UInt(float v) {
		double a = v * 60 * 10000;
		return (int) a;
	}

}
