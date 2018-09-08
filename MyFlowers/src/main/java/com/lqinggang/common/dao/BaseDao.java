package com.lqinggang.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author LQingGang
 * @time 2018年3月23日 - 上午9:26:26
 */
public interface BaseDao<T> {
	// 基本数据库操作方法
	public void save(T obj);// 保存数据

	public void saveOrUpdate(T obj);// 保存或修改数据

	public void update(T obj);// 修改数据

	public void delete(Serializable... ids);// 删除数据

	public long getCount();// 获取总信息数

	/**
	 * 根据hql语句获取记录数
	 * 
	 * @param hql
	 * @return long
	 */
	public long getCount(String hql);

	/**
	 * 运行hql
	 * 
	 * @param hql
	 * @return int
	 */
	public int executeHql(String hql);

	/**
	 * 根据hql查询
	 * 
	 * @param hql
	 * @return List<T>
	 */
	public List<T> queryByHql(String hql);

	/**
	 * 通过sql查询
	 * 
	 * @param sql
	 * @return List<T>
	 */
	public List<T> queryBySql(String sql, Object[] objects);

	/**
	 * 鲜花分类查找
	 * 
	 * @param hql
	 * @param map
	 * @return List<T>
	 */
	public List<T> classify(String hql, Map<Object, Object> map);

	/**
	 * 列出前x条记录
	 * 
	 * @param hql
	 * @param x
	 * @return List<T>
	 */
	public List<T> queryTopXByHql(String hql, int x);

	/**
	 * 列出所有的信息
	 * 
	 * @param entityClass
	 * @return List<T>
	 */
	public List<T> listAll(Class<T> entityClass);

	/**
	 * 列出实体类entityClass从start开始的size条数据
	 * 
	 * @param entityClass
	 * @param start
	 * @param end
	 * @return List<T>
	 */
	public List<T> listRange(Class<T> entityClass, long start, long size);

	/**
	 * 根据hql语句列出start开始的size条记录
	 * 
	 * @param hql
	 * @param start
	 * @param size
	 * @return List<T>
	 */
	public List<T> listRange(String hql, long start, long size);

	/**
	 * 分类搜索
	 * 
	 * @param map
	 * @param hql
	 * @param start
	 * @param size
	 * @return List<T>
	 */
	public List<T> listRange(Map<Object, Object> map, String hql, long start, long size);

	/**
	 * 分类搜索总记录
	 * 
	 * @param map
	 * @param hql
	 * @return long
	 */
	public long getCount(String hql, Map<Object, Object> map);

//	/**
//	 * @param hql
//	 * @param begin
//	 * @param end
//	 * @return long
//	 */
//	public long getCount(String hql, int begin, int end);

	/**
	 * @param hql
	 * @param map
	 * @return Object
	 */
	public Object uniqueResult(String hql, Map<Object, Object> map);

}
