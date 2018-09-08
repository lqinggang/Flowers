package com.lqinggang.entity.users;

import java.io.Serializable;

import javax.persistence.*;

/**
 * @author LQingGang
 * @time 2018年3月20日 - 上午9:04:53
 */
@Entity
@Table(name = "person", schema = "flowers_db", catalog = "")
public class Person implements Serializable {
	private static final long serialVersionUID = -459215628221780612L;

	private int person_id;
//	@NotEmpty(message = "${person.name.empty}")
	private String name;
//	@NotEmpty(message = "${person.password.empty}")
//	@Range(min = 6, max = 16, message = "${person.password.range}")
	private String password;

	public Person() {
	}

	@Id
	@Column(name = "person_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getPerson_id() {
		return person_id;
	}

	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}

	@Basic
	@Column(name = "name", nullable = false, length = 24, unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	@Column(name = "password", nullable = false, length = 255)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Person(String name, String password) {
		this.name = name;
		this.password = password;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Person person = (Person) obj;
		if (person_id != person.person_id) {
			return false;
		}
		if (name != null ? !name.equals(person.name) : person.name != null) {
			return false;
		}
		if (password != null ? !password.equals(person.password) : person.password != null) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int result = person_id;
		result = result * 31 + (name != null ? name.hashCode() : 0);
		result = result * 31 + (password != null ? password.hashCode() : 0);
		return result;
	}

}
