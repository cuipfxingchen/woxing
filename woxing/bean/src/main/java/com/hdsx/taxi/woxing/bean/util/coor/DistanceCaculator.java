package com.hdsx.taxi.woxing.bean.util.coor;

public class DistanceCaculator {

	public static double distance(double x1, double y1, double x2, double y2) {

		double num1 = Math.PI / 180;
		double num2 = 1 / num1;
		x1 = x1 * num1;
		y1 = y1 * num1;
		x2 = x2 * num1;
		y2 = y2 * num1;
		return ((111120 * num2) * Math.acos((Math.sin(y1) * Math.sin(y2))
				+ ((Math.cos(y1) * Math.cos(y2)) * Math.cos(x2 - x1))));
	}

}
