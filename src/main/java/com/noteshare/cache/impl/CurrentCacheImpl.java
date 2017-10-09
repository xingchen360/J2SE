package com.noteshare.cache.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.noteshare.cache.CurrentCache;

public class CurrentCacheImpl implements CurrentCache<String, Object> {

	private static final Log logger = LogFactory.getLog(CurrentCacheImpl.class);
	/**
	 * 具体缓存集合，此处模拟了分布式，数据分布在多个map上
	 */
	ConcurrentHashMap<String, Object>[] caches;
	/**
	 * 存储过期时间
	 */
	ConcurrentHashMap<String, Long> expiryCache;
	private ScheduledExecutorService scheduleService;
	/** 默认间隔时间，10分钟 */
	private int expiryInterval = 10;
	/** caches的默认大小 */
	private int moduleSize = 10;

	public CurrentCacheImpl() {
		init();
	}

	public CurrentCacheImpl(int expiryInterval, int moduleSize) {
		this.expiryInterval = expiryInterval;
		this.moduleSize = moduleSize;
		init();
	}

	@SuppressWarnings("unchecked")
	private void init() {
		this.caches = new ConcurrentHashMap[this.moduleSize];
		for (int i = 0; i < moduleSize; i++) {
			caches[i] = new ConcurrentHashMap<String, Object>();
		}
		this.expiryCache = new ConcurrentHashMap<String, Long>();
		this.scheduleService = Executors.newScheduledThreadPool(1);
		this.scheduleService.scheduleAtFixedRate(new CheckOutOfDateSchedule(this.caches, this.expiryCache), 0L,
				this.expiryInterval * 60, TimeUnit.SECONDS);
	}

	/**
	 * @Title: getCache 
	 * @Description: 根据key的hashcode值获取cache对象,实现负载均衡。 
	 * @param key 
	 * @return ConcurrentHashMap<String,Object> @throws
	 */
	private ConcurrentHashMap<String, Object> getCache(String key) {
		long hashCode = key.hashCode();
		if (hashCode < 0L) {
			hashCode = -hashCode;
		}
		int moduleNum = (int) hashCode % this.moduleSize;
		return this.caches[moduleNum];
	}

	public Object put(String key, Object Value) {
		Object result = getCache(key).put(key, Value);
		this.expiryCache.put(key, Long.valueOf(-1L));
		return result;
	}

	public Object put(String key, Object value, Date expiry) {
		Object result = getCache(key).put(key, value);
		this.expiryCache.put(key, Long.valueOf(expiry.getTime()));
		return result;
	}

	public Object put(String key, Object value, int expiry) {
		Object result = getCache(key).put(key, value);
		this.expiryCache.put(key, new Date().getTime() + Long.valueOf(expiry) * 1000);
		return result;
	}

	public Object get(String key) {
		// 检测是否过期
		checkValidate(key);
		return getCache(key).get(key);
	}

	/**
	 * @Title: checkValidate 
	 * @Description: 检测是否过期 ,如果过期则移除该键值对
	 * @param key 需要检测的缓存键值对对应的key 
	 * @throws
	 */
	private void checkValidate(String key) {
		Long expiry = this.expiryCache.get(key);
		if ((expiry != null) && expiry != -1 && new Date(expiry).before(new Date())) {
			getCache(key).remove(key);
			this.expiryCache.remove(key);
		}

	}

	public Object remove(String key) {
		Object result = this.getCache(key).remove(key);
		this.expiryCache.remove(key);
		return result;
	}

	public boolean clear() {
		if (null != this.caches) {
			for (ConcurrentHashMap<String, Object> cache : this.caches) {
				cache.clear();
			}
		}
		if (null != this.expiryCache) {
			this.expiryCache.clear();
		}
		return true;
	}

	public int size() {
		checkAll();
		return this.expiryCache.size();
	}

	/**
	 * @Title: checkAll 
	 * @Description: 检测所有缓存元素有没有已经过期了的 
	 * @throws
	 */
	private void checkAll() {
		Iterator<String> iterator = this.expiryCache.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			checkValidate(key);
		}
	}

	public Set<String> keySet() {
		checkAll();
		return this.expiryCache.keySet();
	}

	public Collection<Object> values() {
		checkAll();
		Collection<Object> values = new ArrayList<Object>();
		for (ConcurrentHashMap<String, Object> cache : this.caches) {
			values.addAll(cache.values());
		}
		return values;
	}

	public boolean containsKey(String key) {
		checkValidate(key);
		return this.expiryCache.containsKey(key);
	}

	public void destroy() {
		try {
			clear();
			if (null != this.scheduleService) {
				this.scheduleService.shutdown();
			}
			this.scheduleService = null;
			if (null != this.expiryCache) {

			}
		} catch (Exception ex) {
			logger.error(ex);
		}
	}

	class CheckOutOfDateSchedule implements Runnable {
		ConcurrentHashMap<String, Object>[] caches;
		ConcurrentHashMap<String, Long> expiryCache;

		public CheckOutOfDateSchedule(ConcurrentHashMap<String, Object>[] caches,
				ConcurrentHashMap<String, Long> expiryCache) {
			this.caches = caches;
			this.expiryCache = expiryCache;
		}

		public void run() {
			check();
		}

		public void check() {
			try {
				for (ConcurrentHashMap<String, Object> cache : this.caches) {
					Iterator<String> keysIterator = cache.keySet().iterator();
					while (keysIterator.hasNext()) {
						String key = keysIterator.next();
						if (this.expiryCache.get(key) == null) {
							continue;
						}
						long time = this.expiryCache.get(key);
						if ((time > 0L) && (new Date(time).before(new Date()))) {
							cache.remove(key);
						}
					}
				}
			} catch (Exception ex) {
				CurrentCacheImpl.logger.info("CurrentCache CheckService is start!");
			}
		}
	}
}
