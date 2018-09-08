package com.lqinggang.service.impl.users;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lqinggang.common.util.MD5Util;
import com.lqinggang.dao.users.CartDao;
import com.lqinggang.dao.users.ContactDao;
import com.lqinggang.dao.users.MemberDao;
import com.lqinggang.dao.users.PersonDao;
import com.lqinggang.dao.users.UsersDao;
import com.lqinggang.entity.users.Cart;
import com.lqinggang.entity.users.Contact;
import com.lqinggang.entity.users.Member;
import com.lqinggang.entity.users.Person;
import com.lqinggang.entity.users.Users;
import com.lqinggang.service.users.UsersService;

/**
 * @author LQingGang
 * @time 2018年3月20日 - 上午10:28:51
 */
@Transactional
@Service(value = "usersService")
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersDao usersDao;
	@Autowired
	private PersonDao personDao;
	@Autowired
	private ContactDao contactDao;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private CartDao cartDao;

	@Override
	public void createUsers(Users user) {
		// try {
		usersDao.addUsers(user);
		// } catch (Exception e) {
		//
		// e.printStackTrace();
		// }
	}

	@Override
	public void addPersonInfo(Person person) {
		// try {
		personDao.addPerson(person);
		// } catch (Exception e) {
		//
		// e.printStackTrace();
		// }
	}

	@Override
	public void addContactInfo(Contact contact) {
		// try {
		contactDao.addContact(contact);
		// } catch (Exception e) {
		//
		// e.printStackTrace();
		// }
	}

	@Override
	public void addMember(Member member) {
		// try {
		memberDao.addMember(member);
		// } catch (Exception e) {
		//
		// e.printStackTrace();
		// }
	}

	@Override
	public void deleteUsersById(int userId) {
		// try {
		personDao.deletePersonById(userId);
		// } catch (Exception e) {
		//
		// e.printStackTrace();
		// }
	}

	@Override
	public void deleteUsersByName(String userName) {
		// try {
		// List<Person> persons = personDao.queryPerson(userName);
		// for (int i = 0; i < persons.size(); i++) {
		// usersDao.deleteUsersById(persons.get(i).getPerson_id());
		// }
		personDao.deletePersonByName(userName);
		// } catch (Exception e) {
		//
		// e.printStackTrace();
		// }
	}

	@Override
	public List<Person> queryPersonsById(int person_id) {
		// try {
		String hql = " from Person as p where p.person_id='" + person_id + "'";
		List<Person> person = personDao.queryByHql(hql);
		return person;
		// } catch (Exception e) {
		//
		// e.printStackTrace();
		// }
		// return null;
	}

	@Override
	public List<Person> queryPersonsByName(String person_name) {
		// try {
		String hql = " from Person as p where p.name='" + person_name + "'";
		List<Person> person;
		person = personDao.queryByHql(hql);
		return person;
		// } catch (Exception e) {
		//
		// e.printStackTrace();
		// }
		// return null;
	}

	@Override
	public List<Users> queryUsersById(int userId) {
		// try {
		List<Person> persons = personDao.queryPersonById(userId);
		if (persons != null && persons.size() > 0) {
			for (int i = 0; i < persons.size();) {
				return usersDao.queryUsersById(persons.get(i).getPerson_id());
			}
		}
		// } catch (Exception e) {
		//
		// e.printStackTrace();
		// }
		return null;
	}

	@Override
	public List<Users> queryUsersByName(String userName) {
		// try {
		List<Person> persons = queryPersonsByName(userName);
		if (persons != null && persons.size() > 0) {
			for (int i = 0; i < persons.size();) {
				return usersDao.queryUsersById(persons.get(i).getPerson_id());
			}
		}
		// } catch (Exception e) {
		//
		// e.printStackTrace();
		// }
		return new ArrayList<Users>();
	}

	@Override
	public List<Contact> queryUsersContactByName(String userName) {
		// try {
		// 根据用户名查找用户
		List<Person> person = queryPersonsByName(userName);
		if (person != null && person.size() > 0) {
			for (int i = 0; i < person.size(); i++) {
				// 根据用户名ID获取对应的联系方式
				List<Contact> contacts = contactDao.queryContact(person.get(i).getPerson_id());
				if (contacts != null && contacts.size() > 0) {
					return contacts;
				}
			}
		}
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		return new ArrayList<Contact>();

	}

	@Override
	public List<Member> queryUsersMemberByName(String userName) {
		// try {
		// 根据用户名查找用户
		List<Person> persons = personDao.queryPersonByName(userName);
		if (persons != null && persons.size() > 0) {
			for (int i = 0; i < persons.size();) {
				// 获取用户名对应的ID
				int person_id = persons.get(i).getPerson_id();
				// 根据用户名对应的ID查找会员信息
				return memberDao.queryMember(person_id);
			}
		}
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		return new ArrayList<Member>();
	}

	@Override
	public void updateUsers(Users users) {
		// try {
		usersDao.saveOrUpdate(users);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	@Override
	public void updateUsersContact(Contact contact) {
		// try {
		contactDao.updateContact(contact);
		// } catch (Exception e) {
		//
		// e.printStackTrace();
		// }

	}

	@Override
	public void updateUsersMember(Member member) {
		// try {
		memberDao.updateMember(member);
		// } catch (Exception e) {
		//
		// e.printStackTrace();
		// }

	}

	@Override
	public void updateUsersPassword(Person person) {
		personDao.updatePerson(person);
	}

	@Override
	public boolean findPersonIsExists(String name, String password) {
		List<Person> person = personDao.queryPersonByName(name);
		if (person != null && person.size() > 0) {
			for (int i = 0; i < person.size(); i++) {
				if (MD5Util.toMD5(password).equals(person.get(i).getPassword())) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void addCart(Cart cart) {
		/*
		 * List<Cart> carts = null; if (cart != null) { carts =
		 * cartDao.queryCart(cart.getPerson_id().getName(),
		 * cart.getFlower_id().getFlower_id()); } if (carts == null) {
		 */
		cartDao.addCart(cart);
		/*
		 * } else { cart.setAmount(carts.get(0).getAmount() + 1);
		 * cartDao.saveOrUpdate(cart); }
		 */
	}

	@Override
	public void deleteCart(String name, int flower_id) {
		cartDao.deleteCart(name, flower_id);
	}

	@Override
	public List<Cart> listUsersCart(String username) {
		return cartDao.listCartsByPerson(username);
	}

	@Override
	public List<Cart> queryCarts(String name, int flower_id) {
		return cartDao.queryCart(name, flower_id);
	}

	@Override
	public void updateUsersCart(Cart cart) {
		// try {
		cartDao.update(cart);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	@Override
	public void deleteCart(int cart_id) {
		cartDao.deleteCart(cart_id);
	}

	@Override
	public List<Users> listUsers() {
		return usersDao.listAllUsers();
	}

	@Override
	public List<Contact> listContacts() {
		return contactDao.listAllContacts();
	}

	@Override
	public List<Users> listRangeUsers(long start, long size) {
		return usersDao.listRange(Users.class, start, size);
	}

	@Override
	public List<Contact> listRangeUsersContacts(long start, long size) {
		return contactDao.listRange(Contact.class, start, size);
	}

	@Override
	public List<Member> listRangeMember(long start, long size) {
		return memberDao.listRange(Member.class, start, size);
	}

	@Override
	public long getUsersCount() {
		return usersDao.getCount();
	}

	@Override
	public List<Cart> listRangeUsersCart(String username, long start, long size) {
		return cartDao.listCartsByPerson(username, start, size);
	}

	@Override
	public long getUsersCartCount(String name) {
		return cartDao.getUsersCartCount(name);
	}

}
