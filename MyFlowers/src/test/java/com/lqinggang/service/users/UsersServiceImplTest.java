package com.lqinggang.service.users;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import com.lqinggang.common.util.MD5Util;
import com.lqinggang.dao.users.PersonDao;
import com.lqinggang.entity.users.Contact;
import com.lqinggang.entity.users.Member;
import com.lqinggang.entity.users.Person;
import com.lqinggang.entity.users.Users;

/**
 * @author LQingGang
 * @time 2018年3月22日 - 下午3:21:08
 */
@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = { "classpath*:/applicationContext.xml" })
@Rollback(false)
public class UsersServiceImplTest {

	@Autowired
	private UsersService usersService;

	/**
	 * @throws java.lang.Exception
	 * @return void
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 * @return void
	 */
	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void testaddPersonInfo() {
//		Person person;
//		person = new Person();
//		person.setName("username");
//		person.setPassword(MD5.toMD5("password"));
//		usersService.addPersonInfo(person);
//		System.out.println("Test addPersonInfo is ok!");
//	}
	//
	// @Test
	// public void testaddContactInfo() {
	// Person person;
	// person = new Person();
	// person.setName("TestContact");
	// person.setPassword(MD5.toMD5("password"));
	// usersService.addPersonInfo(person);
	// if (person != null) {
	// Contact contact = new Contact();
	// contact.setAddress("Address");
	// contact.setEmail("Email");
	// contact.setTelephone("Telephone");
	// contact.setContact_id(person);
	// usersService.addContactInfo(contact);
	// }
	// System.out.println("Test addContactInfo is ok!");
	// }
	//
	// @Test
	// public void testaddMember() {
	// Person person;
	// person = new Person();
	// person.setName("TestMember");
	// person.setPassword(MD5.toMD5("password"));
	// usersService.addPersonInfo(person);
	// Member member = new Member();
	// member.setDiscount(new Float(1));
	// member.setExperience(new Integer(0));
	// member.setRank(new Integer(1));
	// System.out.println(person.getPerson_id());
	// member.setPerson_id(person);
	// usersService.addMember(member);
	// System.out.println("Test addMember is ok!");
	// }
	//
	// @Test
	// public void testcreateUsers() {
	// Person person;
	// Date day = new Date();
	// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//
	// person = new Person();
	// person.setName("TestcreateUsers");
	// person.setPassword(MD5.toMD5("password"));
	// usersService.addPersonInfo(person);
	//
	// Users users = new Users();
	// users.setAge(new Integer(11));
	// users.setBirtyday(Timestamp.valueOf(df.format(day)));
	// users.setDescription("Test");
	// users.setGender("男");
	// users.setRegister_date(Timestamp.valueOf(df.format(day)));
	// users.setUser_id(person);
	//
	// usersService.createUsers(users);
	// System.out.println("Test createUsers is ok!");
	// }

	// @Test
	// public void testqueryPersonById() {
	// List<Person> persons = usersService.queryPersonsById(3);
	// if (persons != null && persons.size() > 0) {
	// for (int i = 0; i < persons.size(); i++) {
	// System.out.println(persons.get(i).getName());
	// if (persons.get(i).getName() == "TestcreateUsers") {
	// System.out.println("Test queryPersonsById is ok!");
	// }
	// // TestcreateUsers
	// }
	// }
	// }
	//
	// @Test
	// public void testqueryUsersContactByName() {
	// List<Contact> contacts =
	// usersService.queryUsersContactByName("TestContact");
	// if (contacts != null && contacts.size() > 0) {
	// for (int i = 0; i < contacts.size(); i++) {
	// System.out.println(contacts.get(i).getTelephone());
	// if (contacts.get(i).getTelephone() == "Telephone") {
	// System.out.println("Test queryUsersContactByName is ok!");
	// }
	// // Telephone
	// }
	// }
	// }
	//
	// @Test
	// public void testqueryUsersMemberByName() {
	// List<Person> person = usersService.queryPersonsById(1);
	// if (person != null && person.size() > 0) {
	// for (int i = 0; i < person.size(); i++) {
	// List<Member> members =
	// usersService.queryUsersMemberByName(person.get(i).getName());
	// if (members != null && members.size() > 0) {
	// for (int j = 0; j < members.size(); j++) {
	// System.out.println(members.get(j).getDiscount());
	// if (members.get(j).getDiscount() == 1.0) {
	// System.out.println("Test queryUsersMemberByName is ok!");
	// }
	// // 1.0
	// }
	// }
	// }
	// }
	// }
	//
	// @Test
	// public void testqueryUsersById() {
	// List<Person> persons = usersService.queryPersonsById(3);
	// if (persons != null && persons.size() > 0) {
	// for (int i = 0; i < persons.size(); i++) {
	// List<Users> users =
	// usersService.queryUsersById(persons.get(i).getPerson_id());
	// for (int j = 0; j < users.size(); j++) {
	// System.out.println(users.get(j).getGender());
	// if (users.get(j).getGender() == "男") {
	// System.out.println("Test queryUsersById is ok!");
	// }
	// // 男
	// }
	// }
	// }
	// }
	//
	// @Test
	// public void testqueryUsersByName() {
	// List<Person> person = usersService.queryPersonsById(3);
	// if (person != null && person.size() > 0) {
	// for (int i = 0; i < person.size(); i++) {
	// List<Users> users =
	// usersService.queryUsersByName(person.get(i).getName());
	// if (users != null && users.size() > 0) {
	// System.out.println(users.get(i).getAge());
	// if (users.get(i).getAge() == 11) {
	// System.out.println("Test queryUsersByName is ok!");
	// }
	// // 11
	// }
	// }
	// }
	// }

	// @Test
	// public void updateUsers() {
	// List<Users> users = usersService.queryUsersById(3);
	// if (users != null && users.size() > 0) {
	// for (int i = 0; i < users.size(); i++) {
	// users.get(i).setGender("女");
	// usersService.updateUsers(users.get(i));
	// }
	// }
	// }
	//
	// @Test
	// public void updateUsersContact() {
	// List<Contact> contacts =
	// usersService.queryUsersContactByName("TestContact");
	// if (contacts != null && contacts.size() > 0) {
	// for (int i = 0; i < contacts.size(); i++) {
	// contacts.get(i).setAddress("new address");
	// usersService.updateUsersContact(contacts.get(i));
	// }
	// }
	// }
	//
	// @Test
	// public void updateUsersMember() {
	// List<Member> members = usersService.queryUsersMemberByName("TestMember");
	// if (members != null && members.size() > 0) {
	// for (int i = 0; i < members.size(); i++) {
	// members.get(i).setExperience(1000);
	// ;
	// usersService.updateUsersMember(members.get(i));
	// }
	// }
	// }
	//
	// public void Testdelete() {
	// Person person = new Person();
	// Contact contact = new Contact();
	// Member member = new Member();
	// Users users = new Users();
	// person.setName("Delete");
	// person.setPassword(MD5.toMD5("password"));
	// usersService.addPersonInfo(person);
	// contact.setAddress("address");
	// contact.setEmail("email");
	// contact.setTelephone("telephone");
	// contact.setContact_id(person);
	// usersService.addContactInfo(contact);
	// users.setAge(21);
	// Date day = new Date();
	// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// users.setBirtyday(Timestamp.valueOf(df.format(day)));
	// users.setDescription("description");
	// users.setGender("男");
	// users.setRegister_date(Timestamp.valueOf(df.format(day)));
	// users.setUser_id(person);
	// usersService.createUsers(users);
	// member.setDiscount(new Float(1));
	// member.setExperience(new Integer(0));
	// member.setRank(1);
	// member.setPerson_id(person);
	// usersService.addMember(member);
	// }
	//
	// // @Test
	// // public void deleteUsersById() {
	// // Testdelete();
	// // usersService.deleteUsersById(1);
	// // }
	//
	// @Test
	// public void deleteUsersByName() {
	//// Testdelete();
	// usersService.deleteUsersByName("Delete");
	// }

	@Test
	public void findPersonExists() {
		boolean persons = usersService.findPersonIsExists("username", "password");
		if (persons) {
			System.out.println("exists");
		}System.out.println("not exists");
	}

}
