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
 * @time 2018年3月20日 - 下午1:50:42
 */
@Entity
@Table(name = "contact", schema = "flowers_db", catalog = "")
public class Contact implements Serializable {
	private static final long serialVersionUID = 6760260124804852252L;
	private Person contact_id;

	private String telephone = "00000000";
	// @Email(message = "${users.email}")
	private String email = "email@email.com";
	private String address = "110000,110100,110101,未知";

	@Id
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "person_id", referencedColumnName = "person_id")
	public Person getContact_id() {
		return contact_id;
	}

	public void setContact_id(Person contact_id) {
		this.contact_id = contact_id;
	}

	@Basic
	@Column(name = "telephone", nullable = true, length = 15)
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Basic
	@Column(name = "email", nullable = true, length = 255)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Basic
	@Column(name = "address", nullable = true, length = 512)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Contact contact = (Contact) obj;
		if (contact_id != contact.contact_id) {
			return false;
		}
		if (email != null ? !email.equals(contact.email) : contact.email != null) {
			return false;
		}
		if (address != null ? !address.equals(contact.address) : contact.address != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = contact_id.hashCode();
		result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (address != null ? address.hashCode() : 0);
		return result;
	}

}
