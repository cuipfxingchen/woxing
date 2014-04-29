package com.hdsx.taxi.woxing.bean.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.ehcache.CacheManager;

import com.google.inject.Singleton;

/**
 * Ehcache管理
 * 
 * @author Steven
 * 
 */
@Singleton
public class CacheManagerUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(CacheManagerUtil.class);

	private static String path = CacheManagerUtil.class
			.getResource("/").getPath()
			+ "ehcache.xml";
//	public static String path;
//	private static String path =Constance.resourcePath;
//	public static String path =System.getProperty("user.dir")+"/ehcache.xml";
	private CacheManager cm;

	static CacheManagerUtil obj;

	public static CacheManagerUtil getInstance() {
		if (obj == null)
			obj = new CacheManagerUtil();
		return obj;
	}

	public CacheManagerUtil() {
		this.cm = CacheManager.create(path);
	}

	public CacheManager getCm() {
		return cm;
	}
}
