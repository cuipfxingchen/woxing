package com.hdsx.taxi.woxing.xmpp.util;

import java.io.File;

import javax.swing.JPopupMenu.Separator;


public class InitApp {
	
	/**
	 * 系统根目录
	 */
//	public static final String ROOT_PATH = System.getProperty("user.dir");
	public static final String ROOT_PATH = System.getProperty("user.dir");
	
	/**
	 * 配置文件地址
	 */
	private static final String configFileName="/xmpp.properties";
	public static final int SERVER_PORT = Integer.parseInt(Config.getInstance(configFileName).getProperty("SERVER_PORT"));// 服务端口 可以在openfire上设置
	public static final String SERVER_HOST =Config.getInstance(configFileName).getProperty("SERVER_HOST");// 你openfire服务器所在的ip211.101.37.252
	public static final String SERVER_NAME =Config.getInstance(configFileName).getProperty("SERVER_NAME");// 设置openfire时的服务器名cuipengfei-pc
	public static final String USERNAME =Config.getInstance(configFileName).getProperty("USERNAME");
	public static final String PW =Config.getInstance(configFileName).getProperty("PW");
	public static final String ROOM =Config.getInstance(configFileName).getProperty("ROOM");
	public static final int PINGINTERVAL = Integer.parseInt(Config.getInstance(configFileName).getProperty("PINGINTERVAL"));//心跳包间隔时间毫秒

}
