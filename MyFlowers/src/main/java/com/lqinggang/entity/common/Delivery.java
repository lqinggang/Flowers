package com.lqinggang.entity.common;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lqinggang.entity.orders.Orders;
import com.lqinggang.entity.users.Distribution;

/**
 * @author LQingGang
 * @time 2018年3月21日 - 下午3:20:39
 */
@Entity
@Table(name = "delivery", schema = "flowers_db", catalog = "")
public class Delivery implements Serializable {
	private static final long serialVersionUID = -8326530647504359152L;

	private Orders order_id;
	private Distribution person_id;
	/* private String way; */
	private Timestamp date;
	private String send_add;
	private String send_desc;
	private String note;

	@Id
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false)
	public Orders getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Orders order_id) {
		this.order_id = order_id;
	}

	@Basic
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "person_id", referencedColumnName = "person_id", nullable = false)
	public Distribution getPerson_id() {
		return person_id;
	}

	public void setPerson_id(Distribution person_id) {
		this.person_id = person_id;
	}

	/*
	 * @Basic
	 * 
	 * @Column(name = "way", nullable = true) public String getWay() { return
	 * way; }
	 * 
	 * public void setWay(String way) { this.way = way; }
	 */

	@Basic
	@Column(name = "date", nullable = true)
	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Basic
	@Column(name = "send_add", length = 1023, nullable = false)
	public String getSend_add() {
		return send_add;
	}

	public void setSend_add(String send_add) {
		this.send_add = send_add;
	}

	@Basic
	@Column(name = "send_desc", length = 1023, nullable = false)
	public String getSend_desc() {
		return send_desc;
	}

	public void setSend_desc(String send_desc) {
		this.send_desc = send_desc;
	}

	@Basic
	@Column(name = "note", length = 2560, nullable = true)
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != getClass()) {
			return false;
		}
		Delivery delivery = (Delivery) obj;

		if (person_id != delivery.person_id) {
			return false;
		}
		if (order_id != delivery.order_id) {
			return false;
		}
		/*
		 * if ((way != null ? !way.equals(delivery.way) : delivery.way != null))
		 * { return false; }
		 */
		if ((date != null ? !date.equals(delivery.date) : delivery.date != null)) {
			return false;
		}
		if ((send_add != null ? !send_add.equals(delivery.send_add) : delivery.send_add != null)) {
			return false;
		}
		if ((send_desc != null ? !send_desc.equals(delivery.send_desc) : delivery.send_desc != null)) {
			return false;
		}
		if ((note != null ? !note.equals(delivery.note) : delivery.note != null)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = order_id.hashCode();
		result = result * 31 + (person_id != null ? person_id.hashCode() : 0);
		// result = result * 31 + (way != null ? way.hashCode() : 0);
		result = result * 31 + (date != null ? date.hashCode() : 0);
		result = result * 31 + (send_add != null ? send_add.hashCode() : 0);
		result = result * 31 + (send_desc != null ? send_desc.hashCode() : 0);
		result = result * 31 + (note != null ? note.hashCode() : 0);
		return result;
	}

}
