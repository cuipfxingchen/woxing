package com.hdsx.taxi.woxing.bean.util;

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
	private static String path = CacheManagerUtil.class.getClassLoader()
			.getResource("/").getPath()
			+ "ehcache.xml";
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
