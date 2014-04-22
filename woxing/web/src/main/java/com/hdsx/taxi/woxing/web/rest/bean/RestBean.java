package com.hdsx.taxi.woxing.web.rest.bean;

public class RestBean<T> {
	public static int SUCESSCODE = 1;
	public static int FAILCODE = 200;
	int state = SUCESSCODE;
	String msg = "";
	T result;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}



}
