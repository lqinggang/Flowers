package com.lqinggang.dao.impl.flowers;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lqinggang.common.dao.DaoSupport;
import com.lqinggang.dao.flowers.FlowersCategoryDao;
import com.lqinggang.entity.flowers.FlowersCategory;

/**
 * @author LQingGang
 * @time 2018年3月22日 - 上午9:11:20
 */
@Repository(value = "flowersCategoryDao")
public class FlowersCategoryDaoImpl extends DaoSupport<FlowersCategory> implements FlowersCategoryDao {

	@Override
	public void addFlowersCategory(FlowersCategory flowersCategory) {
		save(flowersCategory);

	}

	@Override
	public void deleteFlowersCategory(int categoryId) {
		delete(categoryId);

	}

	@Override
	public List<FlowersCategory> queryFlowersCategory(int categoryId) {
		List<FlowersCategory> list = queryByHql(" from FlowersCategory where category_id='" + categoryId + "' ");
		return list;
	}

	@Override
	public void updateFlowersCategory(FlowersCategory flowersCategory) {
		saveOrUpdate(flowersCategory);

	}

	@Override
	public int countFlowersCategory() {
		return (int) getCount();
	}

	@Override
	public List<FlowersCategory> queryFlowersCategory(String categoryname) {

		String hql = " from FlowersCategory where name='" + categoryname + "'";

		List<FlowersCategory> flowersCategories = queryByHql(hql);
		return flowersCategories;
	}

}
