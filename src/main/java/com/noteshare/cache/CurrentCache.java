package com.noteshare.cache;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

public interface CurrentCache<K, V> {
	/**
	  * @Title: put 
	  * @Description: 往缓存中放入值
	  * @param paramK key
	  * @param paramV value
	  * @return V 返回缓存数据
	  * @throws
	 */
	public V put(K paramK, V paramV);
	/**
	  * @Title: put 
	  * @Description: 往缓存中放入值并指定过期日期
	  * @param paramK key
	  * @param paramV value
	  * @param paramDate 过期时间
	  * @return V 返回缓存数据
	  * @throws
	 */
	public V put(K paramK, V paramV, Date paramDate);
	/**
	  * @Title: put 
	  * @Description: 往缓存中放入值并指定在多少秒之后过期
	  * @param paramK key
	  * @param paramV value
	  * @param paramInt 有效时间 单位秒
	  * @return V 返回缓存数据
	  * @throws
	 */
	public V put(K paramK, V paramV, int paramInt);
	/**
	  * @Title: get 
	  * @Description: 根据key从缓存中获取value 
	  * @param paramK key
	  * @return V 返回获取到的缓存数据
	  * @throws
	 */
	public V get(K paramK);
	/**
	  * @Title: remove 
	  * @Description: 根据给定的key从缓存中删除对应的值 
	  * @param paramK key
	  * @return V 返回删除的缓存数据
	  * @throws
	 */
	public V remove(K paramK);
	/**
	  * @Title: clear 
	  * @Description: 移除缓存中的所有数据 
	  * @return boolean 是否移除成功
	  * @throws
	 */
	public boolean clear();
	/**
	  * @Title: size 
	  * @Description: 获取缓存数据大小 
	  * @return int 返回缓存中缓存的数据个数
	  * @throws
	 */
	public int size();
	/**
	  * @Title: keySet 
	  * @Description: 获取缓存中的key集合 
	  * @return Set<K> 返回缓存中的key的集合
	  * @throws
	 */
	public Set<K> keySet();
	/**
	  * @Title: values 
	  * @Description: 获取缓存中缓存的值的集合 
	  * @return Collection<V> 返回缓存中所有数据的集合
	  * @throws
	 */
	public Collection<V> values();
	/**
	  * @Title: containsKey 
	  * @Description: 判断缓存中是否存在该key
	  * @param paramK key
	  * @return boolean 
	  * @throws
	 */
	public boolean containsKey(K paramK);
	/**
	  * @Title: destroy 
	  * @Description: 释放缓存对象 
	  * @throws
	 */
	public void destroy();
}
