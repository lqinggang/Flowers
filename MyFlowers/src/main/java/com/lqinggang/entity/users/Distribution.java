package com.lqinggang.entity.users;

import java.io.Serializable;

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
 * @time 2018年3月21日 - 下午2:36:33
 */
@Entity
@Table(name = "distribution", schema = "flowers_db", catalog = "")
public class Distribution implements Serializable {
	private static final long serialVersionUID = -6371242946672972508L;
	private Person person_id;
	private String status;

	@Id
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "person_id", referencedColumnName = "person_id", nullable = false)
	public Person getPerson_id() {
		return person_id;
	}

	public void setPerson_id(Person person_id) {
		this.person_id = person_id;
	}

	@Basic
	@Column(name = "status", nullable = true, length = 24)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Distribution distribution = (Distribution) obj;
		if (person_id != distribution.person_id) {
			return false;
		}
		if (status != null ? !status.equals(distribution.status) : distribution.status != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = person_id.hashCode();
		result = result * 31 + (status != null ? status.hashCode() : 0);
		return result;
	}

}
