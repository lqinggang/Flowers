package com.lqinggang.dao.impl.flowers;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lqinggang.common.dao.DaoSupport;
import com.lqinggang.dao.flowers.FlowersDao;
import com.lqinggang.entity.flowers.Flowers;
import com.lqinggang.entity.orders.Purchase;

/**
 * @author LQingGang
 * @time 2018年3月22日 - 上午9:09:48
 */
@Repository(value = "flowersDao")
public class FlowersDaoImpl extends DaoSupport<Flowers> implements FlowersDao {

	@Override
	public void addFlower(Flowers flower) {
		save(flower);
	}

	@Override
	public void deleteFlowerByFlowersIds(int[] flowers_ids) {
		delete(flowers_ids);
	}

	@Override
	public List<Flowers> queryFlowersByFlowersId(int flowers_id) {
		List<Flowers> list = queryByHql(" from Flowers as f where f.flower_id='" + flowers_id + "'");
		return list;
	}

	@Override
	public void updateFlowers(Flowers flowers) {
		update(flowers);

	}

	@Override
	public List<Flowers> findFlowersByFlowersCategoryId(int categoryid) {
		String hql = " from Flowers as f where f.category_id.category_id='" + categoryid + "'";
		List<Flowers> flowers = queryByHql(hql);
		return flowers;
	}

	@Override
	public List<Purchase> queryPurchaseByFlowersId(int flowers_id) {
		// List<Flowers> flowers = queryFlowersByFlowersId(flowers_id);
		// String hql = " from Purchase as p where "
		// List<Purchase> purchaseTypes =
		return null;
	}

	@Override
	public List<Flowers> queryFlowersByFlowersName(String flowers_name) {
		String hql = " from Flowers as f where f.keyword like '%" + flowers_name + "%' ";
		return queryByHql(hql);
	}

	@Override
	public List<Flowers> classify(Map<Object, Object> queryMap) {
		// String hql = " from Flowers as f ";
		// if (queryMap != null && queryMap.size() > 0) {
		// hql += " where ";
		// for (Object iterable_element : queryMap.keySet()) {
		// if (iterable_element.toString().equals("category")) {
		// hql += " f.category_id.name=:" + iterable_element.toString() + " and
		// ";
		// } else {
		// hql += "f." + iterable_element.toString() + "=:" +
		// iterable_element.toString() + " and ";
		// }
		// }
		// hql = hql.substring(0, hql.length() - 4);
		// }
		String hql = getHqlClassify(queryMap);
		return classify(hql, queryMap);
	}

	@Override
	public List<Flowers> listRangeClassify(Map<Object, Object> map, long start, long size) {
		String hql = getHqlClassify(map);
		return listRange(map, hql, start, size);

	}

	@Override
	public long getClassifyCount(Map<Object, Object> map) {
		String hql = getHqlClassify(map);
		return getCount(hql, map);
	}

	@Override
	public List<Flowers> listAllFlowers() {
		return listAll(Flowers.class);
	}

	@Override
	public List<Flowers> listRangeFlowers(int start, int end) {
		return listRange(Flowers.class, start, end);
	}

	@Override
	public List<Flowers> listRangeFlowers(int category_id, long start, long size) {
		String hql = " from Flowers as f where f.category_id.category_id=" + category_id + " ";
		return listRange(hql, start, size);
	}

	@Override
	public long getCategoryFlowersCount(int category_id) {
		String hql = "  from Flowers as f where f.category_id.category_id=" + category_id + " ";
		return getCount(hql);
	}

	@Override
	public long getFlowersCount(String name) {
		String hql = "  from Flowers as f where f.keyword like '%" + name + "%' ";
		return getCount(hql);
	}

	@Override
	public List<Flowers> listFlowersByFlowersName(String flowers_name, long start, long size) {
		String hql = " from Flowers as f where f.keyword like '%" + flowers_name + "%' ";
		return listRange(hql, start, size);
	}

	@Override
	public List<Flowers> listSalesTopFlowers(int top) {
		String hql = " select new Flowers(f.flower_id,f.name,f.keyword,f.color,f.amount,f.image,f.origin,f.quantity,f.price,f.category_id,f.description,f.content,f.content_info) from Flowers as f,Orders as o where  o.status_type_id.status_type_id in (2,3,4) "
				+ " and f.flower_id = o.flower_id.flower_id " + " group by o.flower_id.flower_id "
				+ " order by sum(o.amount) desc ";
		return queryTopXByHql(hql, top);
	}

	/**
	 * 分类搜索时生成hql语句
	 * 
	 * @param map
	 * @return String
	 */
	private String getHqlClassify(Map<Object, Object> map) {
		String hql = " from Flowers as f ";
		if (map != null && map.size() > 0) {
			hql += " where ";
			for (Object iterable_element : map.keySet()) {
				if (iterable_element.toString().equals("category")) {
					hql += " f.category_id.name=:" + iterable_element.toString() + " and ";
				} else {
					hql += "f." + iterable_element.toString() + "=:" + iterable_element.toString() + " and ";
				}
			}
			hql = hql.substring(0, hql.length() - 4);
		}
		return hql;
	}

}
