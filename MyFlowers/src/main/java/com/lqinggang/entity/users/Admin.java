package com.lqinggang.entity.users;

import java.io.Serializable;
import java.sql.Timestamp;

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
 * @time 2018年3月21日 - 下午2:40:51
 */
@Entity
@Table(name = "admin", schema = "flowers_db", catalog = "")
public class Admin implements Serializable {
	private static final long serialVersionUID = 6461426401278859839L;
	private Person admin_id;
	// private Integer permission;
	private Timestamp last_login;

	@Id
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "person_id", referencedColumnName = "person_id", nullable = false)
	public Person getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(Person admin_id) {
		this.admin_id = admin_id;
	}

	// @Basic
	// @Column(name = "permission", nullable = true)
	// public Integer getPermission() {
	// return permission;
	// }
	//
	// public void setPermission(Integer permission) {
	// this.permission = permission;
	// }

	@Basic
	@Column(name = "last_login", nullable = true)
	public Timestamp getLast_login() {
		return last_login;
	}

	public void setLast_login(Timestamp last_login) {
		this.last_login = last_login;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj != null && getClass() == obj.getClass()) {
			return true;
		}
		Admin admin = (Admin) obj;
		if (admin_id != admin.admin_id) {
			return false;
		}
		if ((last_login != null ? !last_login.equals(admin.last_login) : admin.last_login != null)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = admin_id.hashCode();
		result = 31 * result + (last_login == null ? 0 : last_login.hashCode());
		return result;
	}

}
