package com.lqinggang.entity.users;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author LQingGang
 * @time 2018年3月20日 - 上午9:14:55
 */
@Entity
@Table(name = "users", schema = "flowers_db", catalog = "")
public class Users implements Serializable {
	private static final long serialVersionUID = 7906081670772409174L;
	private Person user_id;
	private String gender = "女";
	// @Range(min = 0, max = 130, message = "${users.age.range}")
	private Integer age = 20;
	private Date birtyday = new Date(new java.util.Date().getTime());
	private Timestamp register_date = Timestamp
			.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
	private String description = "未填写描述信息";

	@Id
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "person_id", referencedColumnName = "person_id", nullable = false)
	public Person getUser_id() {
		return user_id;
	}

	public void setUser_id(Person user_id) {
		this.user_id = user_id;
	}

	@Basic
	@Column(name = "gender", nullable = true, length = 2)
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Basic
	@Column(name = "age", nullable = true)
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Basic
	@Column(name = "birtyday", nullable = true)
	public Date getBirtyday() {
		return birtyday;
	}

	public void setBirtyday(Date birtyday) {
		this.birtyday = birtyday;
	}

	@Basic
	@Column(name = "register_date", nullable = true)
	public Timestamp getRegister_date() {
		return register_date;
	}

	public void setRegister_date(Timestamp register_date) {
		this.register_date = register_date;
	}

	@Basic
	@Column(name = "description", nullable = true, length = 5120)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Users user = (Users) obj;
		if (user_id != user.user_id) {
			return false;
		}

		if (gender != null ? !gender.equals(user.gender) : user.gender != null) {
			return false;
		}
		if (age != null ? !age.equals(user.age) : user.age != null) {
			return false;
		}
		if (birtyday != null ? !birtyday.equals(user.birtyday) : user.birtyday != null) {
			return false;
		}
		if (register_date != null ? !register_date.equals(user.register_date) : user.register_date != null) {
			return false;
		}
		if (description != null ? !description.equals(user.description) : user.description != null) {
			return false;
		}

		return true;

	}

	@Override
	public int hashCode() {
		int result = user_id.getPerson_id();
		result = 31 * result + (gender == null ? gender.hashCode() : 0);
		result = 31 * result + (age == null ? age.hashCode() : 0);
		result = 31 * result + (gender == null ? gender.hashCode() : 0);
		result = 31 * result + (gender == null ? gender.hashCode() : 0);
		result = 31 * result + (gender == null ? gender.hashCode() : 0);
		return result;
	}

	public Users() {

	}

	/**
	 * @param user_id
	 * @param gender
	 * @param age
	 * @param birtyday
	 * @param register_date
	 * @param description
	 */
	public Users(Person user_id, String gender, Integer age, Date birtyday, Timestamp register_date,
			String description) {
		this.user_id = user_id;
		this.gender = gender;
		this.age = age;
		this.birtyday = birtyday;
		this.register_date = register_date;
		this.description = description;
	}

}
