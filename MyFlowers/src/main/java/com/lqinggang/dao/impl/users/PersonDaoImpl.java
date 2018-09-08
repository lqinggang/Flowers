package com.lqinggang.dao.impl.users;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lqinggang.common.dao.DaoSupport;
import com.lqinggang.dao.users.PersonDao;
import com.lqinggang.entity.users.Person;

/**
 * @author LQingGang
 * @time 2018年3月20日 - 上午9:39:51
 */
@Repository(value = "personDao")
public class PersonDaoImpl extends DaoSupport<Person> implements PersonDao {

	@Override
	public void addPerson(Person person) {
		save(person);
	}

	@Override
	public void deletePersonById(int personId) {
		delete(personId);
	}

	@Override
	public void deletePersonByName(String personName) {
		List<Person> list = queryPersonByName(personName);
		for (int i = 0; i < list.size(); i++) {
			delete(list.get(i).getPerson_id());
		}
	}

	@Override
	public List<Person> queryPersonById(int personId) {
		return queryByHql(" from Person as p where p.person_id='" + personId + "'");
	}

	@Override
	public List<Person> queryPersonByName(String personName) {
		return queryByHql(" from Person as p where p.name='" + personName + "'");
	}

	@Override
	public void updatePerson(Person person) {
		saveOrUpdate(person);
	}

}
