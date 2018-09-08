package com.lqinggang.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lqinggang.common.util.GenericsUtils;

/**
 * @author LQingGang
 * @time 2018年3月23日 - 上午9:28:15
 */
public class DaoSupport<T> implements BaseDao<T> {
	// 泛型的类型
	@SuppressWarnings("unchecked")
	protected Class<T> entityClass = (Class<T>) GenericsUtils.getGenericType(this.getClass());

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	protected CriteriaQuery<T> getCriteria() {
		return getSession().getCriteriaBuilder().createQuery(entityClass);
	}

	/**
	 * 删除实体对象
	 *
	 * @param ids
	 */
	@Override
	public void delete(Serializable... ids) {
		for (Serializable id : ids) {
			T t = getSession().load(this.entityClass, id);
			getSession().delete(t);
		}
	}

	/**
	 * 利用save()方法保存对象的详细信息
	 */
	@Override
	public void save(T obj) {
		getSession().save(obj);
	}

	@Override
	public void saveOrUpdate(T obj) {
		getSession().saveOrUpdate(obj);
	}

	/**
	 * 利用update()方法修改对象的详细信息
	 */
	@Override
	public void update(T obj) {
		getSession().update(obj);
	}

	/**
	 * 执行更新或删除
	 *
	 * @param hql
	 * @return 更新或删除的数量
	 */
	@Override
	public int executeHql(String hql) {
		@SuppressWarnings("unchecked")
		Query<T> query = getSession().createQuery(hql);
		return query.executeUpdate();
	}

	@Override
	public List<T> queryByHql(String hql) {
		@SuppressWarnings("unchecked")
		Query<T> query = getSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<T> queryTopXByHql(String hql, int x) {
		@SuppressWarnings("unchecked")
		Query<T> query = getSession().createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(x);
		return query.list();
	}

	/**
	 * 获取分页查询中结果集的起始位置
	 *
	 * @param pageNo
	 *            第几页
	 * @param pageSize
	 *            页面显示的记录数
	 * @return 起始位置
	 */
	protected int getFirstResult(int pageNo, int pageSize) {
		int firstResult = (pageNo - 1) * pageSize;
		return firstResult < 0 ? 0 : firstResult;
	}

	/**
	 * 对query中的参数赋值
	 *
	 * @param query
	 * @param queryParams
	 */
	protected void setQueryParams(Query<?> query, Object[] queryParams) {
		if (queryParams != null && queryParams.length > 0) {
			for (int i = 0, k = 0; i < queryParams.length; i++) {
				if (queryParams[i] != null) {
					query.setParameter(k, queryParams[i]);
					k++;
				}
			}
		}
	}

	/**
	 * 创建排序hql语句
	 *
	 * @param orderBy
	 * @return 排序字符串
	 */
	protected String createOrderBy(Map<String, String> orderBy) {
		StringBuffer sb = new StringBuffer("");
		if (orderBy != null && orderBy.size() > 0) {
			sb.append(" order by ");
			for (String key : orderBy.keySet()) {
				sb.append(key).append(" ").append(orderBy.get(key)).append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Object uniqueResult(final String hql, Map<Object, Object> map) {
		@SuppressWarnings("rawtypes")
		Query query = getSession().createQuery(hql);
		if (map != null && map.size() > 0) {
			for (Object iterable_element : map.keySet()) {
				query.setParameter(iterable_element.toString(), map.get(iterable_element));
			}
		}
		return query.uniqueResult();
	}

	/**
	 * 获取指定对象的信息条数
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public long getCount() {
		String hql = "select count(*) from " + GenericsUtils.getGenericName(this.entityClass);
		return (Long) uniqueResult(hql, null);
	}

	@Override
	public long getCount(String hql) {
		return (Long) uniqueResult(" select count(*) " + hql, null);
	}

	@Override
	public long getCount(String hql, Map<Object, Object> map) {
		return (Long) uniqueResult(" select count(*) " + hql, map);
	}

//	@Override
//	public long getCount(String hql, int begin, int end) {
//		return 0;
//	}

	/**
	 * 鲜花分类搜索
	 */
	@Override
	public List<T> classify(String hql, Map<Object, Object> map) {
		@SuppressWarnings("unchecked")
		Query<T> query = getSession().createQuery(hql);
		if (map != null) {
			for (Object iterable_element : map.keySet()) {
				query.setParameter(iterable_element.toString(), map.get(iterable_element));
			}
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listAll(Class<T> entityClass) {
		String hql = " from " + entityClass.getSimpleName();
		Query<T> query = getSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<T> listRange(Class<T> entityClass, long start, long size) {
		String hql = " from " + entityClass.getSimpleName();
		@SuppressWarnings("unchecked")
		Query<T> query = getSession().createQuery(hql);
		query.setFirstResult((int) start);
		query.setMaxResults((int) size);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listRange(String hql, long start, long size) {
		Query<T> query = getSession().createQuery(hql);
		query.setFirstResult((int) start);
		query.setMaxResults((int) size);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listRange(Map<Object, Object> map, String hql, long start, long size) {
		Query<T> query = getSession().createQuery(hql);
		if (map != null && map.size() > 0) {
			for (Object iterable_element : map.keySet()) {
				query.setParameter(iterable_element.toString(), map.get(iterable_element));
			}
		}
		query.setFirstResult((int) start);
		query.setMaxResults((int) size);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryBySql(String sql, Object[] objects) {
		NativeQuery<T> nativeQuery = getSession().createNativeQuery(sql);
		for (int i = 0; i < objects.length; i++) {
			nativeQuery.setParameter(i, objects[i]);
		}
		return nativeQuery.getResultList();
	}

}
