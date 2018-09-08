package com.lqinggang.dao.users;

import java.util.List;

import com.lqinggang.common.dao.BaseDao;
import com.lqinggang.entity.users.Contact;

/**
 * 联系方式
 * 
 * @author LQingGang
 * @time 2018年3月20日 - 下午1:56:07
 */
public interface ContactDao extends BaseDao<Contact> {

	/**
	 * 新增联系方式
	 * 
	 * @param contact
	 * @return void
	 */
	public void addContact(Contact contact);

	/**
	 * 通过用户ID删除联系方式
	 * 
	 * @param contact_id
	 * @return void
	 */
	public void deleteContact(int contact_id);

	/**
	 * 通过用户ID查找联系方式
	 * 
	 * @param contact_id
	 * @return Contact
	 */
	public List<Contact> queryContact(int contact_id);

	/**
	 * 列出所有的联系方式
	 * 
	 * @return
	 * @return List<Contact>
	 */
	public List<Contact> listAllContacts();

	/**
	 * 修改用户联系方式
	 * 
	 * @param contact
	 * @return void
	 */
	public void updateContact(Contact contact);

}
