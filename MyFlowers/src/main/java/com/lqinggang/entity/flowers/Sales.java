package com.lqinggang.entity.flowers;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

import com.lqinggang.entity.users.Person;

/**
 * @author LQingGang
 * @time 2018年3月19日 - 下午9:05:55
 */
@Entity
@Table(name = "sales", schema = "flowers_db", catalog = "")
public class Sales implements Serializable {

	private static final long serialVersionUID = -9099190536835031827L;
	private Flowers sales_id;
	private Person person_id;
	private Timestamp date;
	private Integer amount;

	@Id
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "flower_id", referencedColumnName = "flower_id", nullable = false)
	public Flowers getSales_id() {
		return sales_id;
	}

	public void setSales_id(Flowers sales_id) {
		this.sales_id = sales_id;
	}

	@Basic
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "person_id", referencedColumnName = "person_id", nullable = false)
	public Person getPerson_id() {
		return person_id;
	}

	public void setPerson_id(Person person_id) {
		this.person_id = person_id;
	}

	@Basic
	@Column(name = "date", nullable = true)
	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Basic
	@Column(name = "amount", nullable = false)
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		Sales sales = (Sales) obj;

		if (sales_id != sales.sales_id) {
			return false;
		}
		if (person_id != sales.person_id) {
			return false;
		}
		if ((date != null ? !date.equals(sales.date) : sales.date != null)) {
			return false;
		}
		if ((amount != null ? !amount.equals(sales.amount) : sales.amount != null)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int result = sales_id != null ? sales_id.hashCode() : 0;
		result = 31 * result + (person_id != null ? person_id.hashCode() : 0);
		result = 31 * result + (date != null ? date.hashCode() : 0);
		result = 31 * result + (amount != null ? amount.hashCode() : 0);
		return result;
	}

}
