package com.lqinggang.dao.impl.users;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lqinggang.common.dao.DaoSupport;
import com.lqinggang.dao.users.ContactDao;
import com.lqinggang.entity.users.Contact;

/**
 * @author LQingGang
 * @time 2018年3月20日 - 下午2:01:14
 */
@Repository(value = "contactDao")
public class ContactDaoImpl extends DaoSupport<Contact> implements ContactDao {

	@Override
	public void addContact(Contact contact) {
		save(contact);
	}

	@Override
	public void deleteContact(int contact_id) {
		delete(contact_id);
	}

	@Override
	public List<Contact> queryContact(int contact_id) {
		List<Contact> contacts = queryByHql(" from Contact as c where c.contact_id.person_id='" + contact_id + "'");
		if (contacts != null && contacts.size() > 0) {
			return contacts;
		}
		return null;
	}

	@Override
	public void updateContact(Contact contact) {
		saveOrUpdate(contact);

	}

	@Override
	public List<Contact> listAllContacts() {
		return listAll(Contact.class);
	}

}
