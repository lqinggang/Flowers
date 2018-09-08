package com.lqinggang.dao.flowers;

import java.util.List;

import com.lqinggang.common.dao.BaseDao;
import com.lqinggang.entity.flowers.FlowersCategory;

/**
 * 鲜花类别
 * 
 * @author LQingGang
 * @time 2018年3月19日 - 下午9:38:44
 */
public interface FlowersCategoryDao extends BaseDao<FlowersCategory> {
	/**
	 * 新增鲜花类型
	 * 
	 * @param flowersCategory
	 *            鲜花类型
	 * @return void
	 */
	public void addFlowersCategory(FlowersCategory flowersCategory);

	/**
	 * 通过鲜花类型ID删除一种鲜花类型
	 * 
	 * @param categoryId
	 *            类型ID
	 * @return void
	 */
	public void deleteFlowersCategory(int categoryId);

	/**
	 * 通过鲜花类型ID查询一种鲜花类型
	 * 
	 * @param categoryId
	 *            鲜花类型ID
	 * @return FlowersCategory 鲜花类型
	 */
	public List<FlowersCategory> queryFlowersCategory(int categoryId);

	/**
	 * 通过鲜花类型名查询一种鲜花类型
	 * 
	 * @param categoryId
	 *            鲜花类型名
	 * @return FlowersCategory 鲜花类型
	 */
	public List<FlowersCategory> queryFlowersCategory(String categoryname);

	/**
	 * 统计鲜花类别数目
	 * 
	 * @return int
	 */
	public int countFlowersCategory();

	/**
	 * 更新鲜花类型
	 * 
	 * @param categoryId
	 *            鲜花类型
	 * @return void
	 */
	public void updateFlowersCategory(FlowersCategory flowersCategory);
}
