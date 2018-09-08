package com.lqinggang.entity.orders;

import java.io.Serializable;
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
 * @time 2018年3月21日 - 下午3:05:25
 */
@Entity
@Table(name = "orders_cancel", schema = "flowers_db", catalog = "")
public class OrdersCancel implements Serializable { // 订单取消状态表
	private static final long serialVersionUID = 6215924546982679218L;
	private Orders order_status_id;
//	private Boolean status; // 无意义
	private Timestamp date = Timestamp
			.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
	private String content = "无"; // 取消原因

	@Id
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false)
	public Orders getOrder_status_id() {
		return order_status_id;
	}

	public void setOrder_status_id(Orders order_status_id) {
		this.order_status_id = order_status_id;
	}

//	@Basic
//	@Column(name = "status", nullable = true)
//	public Boolean getStatus() {
//		return status;
//	}
//
//	public void setStatus(Boolean status) {
//		this.status = status;
//	}

	@Basic
	@Column(name = "date", nullable = true)
	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Basic
	@Column(name = "content", nullable = true, length = 255)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		OrdersCancel orders = (OrdersCancel) obj;

		if (order_status_id != orders.order_status_id) {
			return false;
		}
//		if ((status != null ? !status.equals(orders.status) : orders.status != null)) {
//			return false;
//		}
		if ((date != null ? !date.equals(orders.date) : orders.date != null)) {
			return false;
		}
		if ((content != null ? !content.equals(orders.content) : orders.content != null)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = order_status_id.hashCode();
//		result = 31 * result + (status != null ? status.hashCode() : 0);
		result = 31 * result + (date != null ? date.hashCode() : 0);
		result = 31 * result + (content != null ? content.hashCode() : 0);
		return result;
	}

}

// package com.lqinggang.entity.orders;
//
// import java.io.Serializable;
// import java.sql.Timestamp;
//
// import javax.persistence.Basic;
// import javax.persistence.CascadeType;
// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.OneToOne;
// import javax.persistence.Table;
//
/// **
// * @author LQingGang
// * @time 2018年3月21日 - 下午3:05:25
// */
// @Entity
// @Table(name = "orders_status", schema = "flowers_db", catalog = "")
// public class OrdersStatus implements Serializable {
// private static final long serialVersionUID = 6215924546982679218L;
// private Orders order_status_id;
// private Boolean cancel_status;
// private Timestamp cancel_date;
// private String cancel_renson;
// private Boolean sign_status;
// private Timestamp sign_date;
// private Boolean purchase_status;
// private Timestamp purchase_date;
//
// @Id
// @OneToOne(cascade = CascadeType.ALL)
// @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable =
// false)
// public Orders getOrder_status_id() {
// return order_status_id;
// }
//
// public void setOrder_status_id(Orders order_status_id) {
// this.order_status_id = order_status_id;
// }
//
// @Basic
// @Column(name = "cancel_status", nullable = true)
// public Boolean getCancel_status() {
// return cancel_status;
// }
//
// public void setCancel_status(Boolean cancel_status) {
// this.cancel_status = cancel_status;
// }
//
// @Basic
// @Column(name = "cancel_date", nullable = true)
// public Timestamp getCancel_date() {
// return cancel_date;
// }
//
// public void setCancel_date(Timestamp timestamp) {
// this.cancel_date = timestamp;
// }
//
// @Basic
// @Column(name = "cancel_renson", length = 255, nullable = true)
// public String getCancel_renson() {
// return cancel_renson;
// }
//
// public void setCancel_renson(String cancel_renson) {
// this.cancel_renson = cancel_renson;
// }
//
// @Basic
// @Column(name = "sign_status", nullable = true)
// public Boolean getSign_status() {
// return sign_status;
// }
//
// public void setSign_status(Boolean sign_status) {
// this.sign_status = sign_status;
// }
//
// @Basic
// @Column(name = "sign_date", nullable = true)
// public Timestamp getSign_date() {
// return sign_date;
// }
//
// public void setSign_date(Timestamp sign_date) {
// this.sign_date = sign_date;
// }
//
// @Basic
// @Column(name = "purchase_status", nullable = true)
// public Boolean getPurchase_status() {
// return purchase_status;
// }
//
// public void setPurchase_status(Boolean purchase_status) {
// this.purchase_status = purchase_status;
// }
//
// @Basic
// @Column(name = "purchase_date", nullable = true)
// public Timestamp getPurchase_date() {
// return purchase_date;
// }
//
// public void setPurchase_date(Timestamp purchase_date) {
// this.purchase_date = purchase_date;
// }
//
// @Override
// public boolean equals(Object obj) {
// if (this == obj) {
// return true;
// }
// if (obj == null || getClass() != obj.getClass()) {
// return false;
// }
//
// OrdersStatus orders = (OrdersStatus) obj;
//
// if (order_status_id != orders.order_status_id) {
// return false;
// }
// if ((cancel_status != null ? !cancel_status.equals(orders.cancel_status) :
// orders.cancel_status != null)) {
// return false;
// }
// if ((cancel_date != null ? !cancel_date.equals(orders.cancel_date) :
// orders.cancel_date != null)) {
// return false;
// }
// if ((cancel_date != null ? !cancel_date.equals(orders.cancel_date) :
// orders.cancel_date != null)) {
// return false;
// }
// if ((cancel_renson != null ? !cancel_renson.equals(orders.cancel_renson) :
// orders.cancel_renson != null)) {
// return false;
// }
// if ((sign_status != null ? !sign_status.equals(orders.sign_status) :
// orders.sign_status != null)) {
// return false;
// }
// if ((sign_date != null ? !sign_date.equals(orders.sign_date) :
// orders.sign_date != null)) {
// return false;
// }
// if ((purchase_status != null ?
// !purchase_status.equals(orders.purchase_status)
// : orders.purchase_status != null)) {
// return false;
// }
// if ((purchase_date != null ? !purchase_date.equals(orders.purchase_date) :
// orders.purchase_date != null)) {
// return false;
// }
// return true;
// }
//
// @Override
// public int hashCode() {
// int result = order_status_id.hashCode();
// result = 31 * result + (cancel_status != null ? cancel_status.hashCode() :
// 0);
// result = 31 * result + (cancel_date != null ? cancel_date.hashCode() : 0);
// result = 31 * result + (cancel_renson != null ? cancel_renson.hashCode() :
// 0);
// result = 31 * result + (sign_status != null ? sign_status.hashCode() : 0);
// result = 31 * result + (sign_date != null ? sign_date.hashCode() : 0);
// result = 31 * result + (purchase_status != null ? purchase_status.hashCode()
// : 0);
// result = 31 * result + (purchase_date != null ? purchase_date.hashCode() :
// 0);
// return result;
// }
//
// }
