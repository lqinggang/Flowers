package com.lqinggang.entity.orders;

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

import com.lqinggang.entity.flowers.Flowers;
import com.lqinggang.entity.users.Person;

/**
 * @author LQingGang
 * @time 2018年3月21日 - 下午2:50:20
 */
@Entity
@Table(name = "orders", schema = "flowers_db", catalog = "")
public class Orders implements Serializable {

	private static final long serialVersionUID = -7720842382869084854L;
	private String order_id;
	private Person person_id;
	private Flowers flower_id;
	private String recipient;
	private String contact;
	private String address;
	private OrderStatusType status_type_id;
	private Float price;
	private Timestamp date;
	private Integer amount;
	private String note = "无";

	@Id
	@Column(name = "order_id", nullable = false)
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	@Basic
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "person_id", referencedColumnName = "person_id", nullable = true)
	public Person getPerson_id() {
		return person_id;
	}

	public void setPerson_id(Person person_id) {
		this.person_id = person_id;
	}

	@Basic
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "status_type_id", referencedColumnName = "status_type_id", nullable = false)
	public OrderStatusType getStatus_type_id() {
		return status_type_id;
	}

	public void setStatus_type_id(OrderStatusType status_type_id) {
		this.status_type_id = status_type_id;
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
	@Column(name = "recipient", nullable = false, length = 64)
	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	@Basic
	@Column(name = "contact", nullable = false, length = 16)
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Basic
	@Column(name = "address", nullable = false, length = 255)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	@Basic
	@Column(name = "note", nullable = true, length = 255)
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Basic
	@Column(name = "price", nullable = false)
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Orders() {

	}

	public Orders(Flowers flower_id, Integer amount) {
		this.flower_id = flower_id;
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

		Orders orders = (Orders) obj;

		if (person_id != orders.person_id) {
			return false;
		}
		if (flower_id != null ? !flower_id.equals(orders.flower_id) : orders.flower_id != null) {
			return false;
		}
		if (status_type_id != null ? !status_type_id.equals(orders.status_type_id) : orders.status_type_id != null) {
			return false;
		}
		if (order_id != null ? !order_id.equals(orders.order_id) : orders.order_id != null) {
			return false;
		}
		if (recipient != null ? !recipient.equals(orders.recipient) : orders.recipient != null) {
			return false;
		}
		if (contact != null ? !contact.equals(orders.contact) : orders.contact != null) {
			return false;
		}
		if (address != null ? !address.equals(orders.address) : orders.address != null) {
			return false;
		}
		if ((date != null ? !date.equals(orders.date) : orders.date != null)) {
			return false;
		}
		if ((amount != null ? !amount.equals(orders.amount) : orders.amount != null)) {
			return false;
		}
		if ((note != null ? !note.equals(orders.note) : orders.note != null)) {
			return false;
		}
		if ((price != null ? !price.equals(orders.price) : orders.price != null)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int result = order_id != null ? order_id.hashCode() : 0;
		result = 31 * result + (person_id != null ? person_id.hashCode() : 0);
		result = 31 * result + (flower_id != null ? flower_id.hashCode() : 0);
		result = 31 * result + (status_type_id != null ? status_type_id.hashCode() : 0);
		result = 31 * result + (recipient != null ? recipient.hashCode() : 0);
		result = 31 * result + (contact != null ? contact.hashCode() : 0);
		result = 31 * result + (address != null ? address.hashCode() : 0);
		result = 31 * result + (date != null ? date.hashCode() : 0);
		result = 31 * result + (amount != null ? amount.hashCode() : 0);
		result = 31 * result + (note != null ? note.hashCode() : 0);
		result = 31 * result + (price != null ? price.hashCode() : 0);
		return result;
	}
}
