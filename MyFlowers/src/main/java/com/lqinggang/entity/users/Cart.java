package com.lqinggang.entity.users;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lqinggang.entity.flowers.Flowers;

/**
 * @author LQingGang
 * @time 2018年3月21日 - 下午3:14:50
 */
@Entity
@Table(name = "cart", schema = "flwers_db", catalog = "")
public class Cart implements Serializable {
	
	private static final long serialVersionUID = -1219328474523570547L;
	
	private int cart_id;
	private Person person_id;
	private Flowers flower_id;
	private Date date;
	private Integer amount;

	@Id
	@Column(name = "cart_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	@Basic
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "person_id", referencedColumnName = "person_id", nullable = false)
	public Person getPerson_id() {
		return person_id;
	}

	public void setPerson_id(Person person_id) {
		this.person_id = person_id;
	}

	@Basic
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "flower_id", referencedColumnName = "flower_id", nullable = false)
	public Flowers getFlower_id() {
		return flower_id;
	}

	public void setFlower_id(Flowers flower_id) {
		this.flower_id = flower_id;
	}

	@Basic
	@Column(name = "date", nullable = true)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != getClass()) {
			return false;
		}
		Cart cart = (Cart) obj;

		if (person_id != cart.person_id) {
			return false;
		}
		if (flower_id != cart.flower_id) {
			return false;
		}
		if ((date != null ? !date.equals(cart.date) : cart.date != null)) {
			return false;
		}
		if ((amount != null ? !amount.equals(cart.amount) : cart.amount != null)) {
			return false;
		}
		return true;

	}

	@Override
	public int hashCode() {
		int result = person_id.hashCode();
		result = 31 * result + (flower_id != null ? flower_id.hashCode() : 0);
		result = 31 * result + (date != null ? date.hashCode() : 0);
		result = 31 * result + (amount != null ? amount.hashCode() : 0);
		return result;
	}

}
