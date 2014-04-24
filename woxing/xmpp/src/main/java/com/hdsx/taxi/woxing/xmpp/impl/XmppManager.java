package com.hdsx.taxi.woxing.xmpp.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hdsx.taxi.woxing.xmpp.runnable.JoinRoom;
import com.hdsx.taxi.woxing.xmpp.runnable.XmppConnection;
import com.hdsx.taxi.woxing.xmpp.runnable.XmppLogin;

public class XmppManager {
	private final static Logger logger = LoggerFactory.getLogger(XmppManager.class);
	private static ExecutorService executorService=Executors.newSingleThreadExecutor() ;
	private static List<Runnable> taskList =new ArrayList<Runnable>();
	private static List<Runnable> connectionList;
	private static boolean running=false;
	private static Future<?> futureTask;
	private static XmppManager xmppManager;
	
	public static XmppManager getXmppManager(){
		if(xmppManager==null){
			xmppManager=new XmppManager();
		}
		return xmppManager;
	}
	
	public void connected(){
		connectionList =new ArrayList<Runnable>();
		addConnectionTash(new XmppConnection());
		addConnectionTash(new XmppLogin());
//		addConnectionTash(new JoinRoom());
	}
	
	public  void addConnectionTash(Runnable runnable){
		synchronized (connectionList) {
            if (connectionList.isEmpty() && !running) {
                running = true;
                futureTask=submitTask(runnable);
            } else {
            	connectionList.add(runnable);
            }
        }
	}
	
	
	/**
	 * 执行任务
	 * 
	 */
	public  void runConnectionTask(){
		 synchronized (connectionList) {
	            running = false;
	            futureTask = null;
	            if (!connectionList.isEmpty()) {
	                Runnable runnable = (Runnable) connectionList.get(0);
	                connectionList.remove(0);
	                running = true;
	                futureTask = submitTask(runnable);
	            }
	        }
	}
	
	public  void addTash(Runnable runnable){
		synchronized (taskList) {
            if (taskList.isEmpty() && !running) {
                running = true;
                futureTask=submitTask(runnable);
            } else {
                taskList.add(runnable);
            }
        }
	}
	
	
	/**
	 * 执行任务
	 * 
	 */
	public  void runTask(){
		 synchronized (taskList) {
	            running = false;
	            futureTask = null;
	            if (!taskList.isEmpty()&&connectionList.isEmpty()) {
	                Runnable runnable = (Runnable) taskList.get(0);
	                taskList.remove(0);
	                running = true;
	                futureTask = submitTask(runnable);
	            }
	        }
	}
	/**
	 * 
	 * @param threed
	 */
	private   Future submitTask(Runnable task){
		Future result = null;
		logger.info("线程submitTask判断："+!executorService.isTerminated());
        if (!executorService.isTerminated()
                && !executorService.isShutdown()
                && task != null) {
            result = executorService.submit(task);
        }
        return result;
	}
	
}
