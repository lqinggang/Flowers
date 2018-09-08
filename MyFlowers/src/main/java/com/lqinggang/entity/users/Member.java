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
 * @time 2018年3月21日 - 下午2:46:06
 */
@Entity
@Table(name = "member", schema = "flowers_db", catalog = "")
public class Member implements Serializable {
	private static final long serialVersionUID = 462297253775967230L;
	private Person person_id;
	private Integer rank = 1;
	private Integer experience = 0;
	private Float discount = Float.valueOf(1);

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
	@Column(name = "rank", nullable = true)
	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Basic
	@Column(name = "experience", nullable = true)
	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	@Basic
	@Column(name = "discount", nullable = true)
	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		Member member = (Member) obj;

		if (member.person_id != person_id) {
			return false;
		}

		if (rank != null ? !rank.equals(member.rank) : member.rank != null) {
			return false;
		}
		if (experience != null ? !experience.equals(member.experience) : member.experience != null) {
			return false;
		}
		if (discount != null ? !discount.equals(member.discount) : member.discount != null) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int result = person_id.getPerson_id();
		result = 31 * result + (rank != null ? rank.hashCode() : 0);
		result = 31 * result + (experience != null ? experience.hashCode() : 0);
		result = 31 * result + (discount != null ? discount.hashCode() : 0);
		return result;
	}
}
