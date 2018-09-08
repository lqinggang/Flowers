package com.lqinggang.entity.orders;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author LQingGang
 * @time 2018年4月10日 - 下午8:16:25
 */
@Entity
@Table(name = "order_status_type", schema = "flowers_db", catalog = "")
public class OrderStatusType implements Serializable {

	private static final long serialVersionUID = -3827811495804519727L;

	private int status_type_id;
	private String status_name;

	@Id
	@Column(name = "status_type_id", nullable = false)
	public int getStatus_type_id() {
		return status_type_id;
	}

	public void setStatus_type_id(int status_type_id) {
		this.status_type_id = status_type_id;
	}

	@Basic
	@Column(name = "status_name", nullable = false, length = 64)
	public String getStatus_name() {
		return status_name;
	}

	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		OrderStatusType orderStatusType = (OrderStatusType) obj;
		if (status_type_id != orderStatusType.status_type_id) {
			return false;
		}
		if (status_name != null ? !status_name.equals(orderStatusType.status_name)
				: orderStatusType.status_name != null) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int result = status_type_id;
		result = result * 31 + (status_name != null ? status_name.hashCode() : 0);
		return result;
	}

}
