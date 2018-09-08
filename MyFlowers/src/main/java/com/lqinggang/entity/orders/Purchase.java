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
 * @time 2018年3月21日 - 下午2:57:34
 */
@Entity
@Table(name = "orders_purchase", schema = "flowers_db", catalog = "")
public class Purchase implements Serializable { // 订单（鲜花）评价表
	private static final long serialVersionUID = 3765574498844949943L;
	private Orders order_id;
	private Integer logistics = 5;
	private Integer commodity = 5;
	private Integer service = 5;
	private Timestamp date = Timestamp
			.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
	private String purchase_content = "此用户没有填写评价信息"; // 评价内容

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
	@Column(name = "logistics", nullable = true)
	public Integer getLogistics() {
		return logistics;
	}

	public void setLogistics(Integer logistics) {
		this.logistics = logistics;
	}

	@Basic
	@Column(name = "commodity", nullable = true)
	public Integer getCommodity() {
		return commodity;
	}

	public void setCommodity(Integer commodity) {
		this.commodity = commodity;
	}

	@Basic
	@Column(name = "service", nullable = true)
	public Integer getService() {
		return service;
	}

	public void setService(Integer service) {
		this.service = service;
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
	@Column(name = "purchase_content", length = 5120, nullable = true)
	public String getPurchase_content() {
		return purchase_content;
	}

	public void setPurchase_content(String purchase_content) {
		this.purchase_content = purchase_content;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Purchase purchase = (Purchase) obj;
		if (order_id != purchase.order_id) {
			return false;
		}
		if ((logistics != null ? !logistics.equals(purchase.logistics) : purchase.logistics != null)) {
			return false;
		}
		if ((commodity != null ? !commodity.equals(purchase.commodity) : purchase.commodity != null)) {
			return false;
		}
		if ((service != null ? !service.equals(purchase.service) : purchase.service != null)) {
			return false;
		}
		if ((date != null ? !date.equals(purchase.date) : purchase.date != null)) {
			return false;
		}
		if ((purchase_content != null ? !purchase_content.equals(purchase.purchase_content)
				: purchase.purchase_content != null)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int result = order_id.hashCode();
		result = result * 31 + (logistics != null ? logistics.hashCode() : 0);
		result = result * 31 + (commodity != null ? commodity.hashCode() : 0);
		result = result * 31 + (service != null ? service.hashCode() : 0);
		result = result * 31 + (date != null ? date.hashCode() : 0);
		result = result * 31 + (purchase_content != null ? purchase_content.hashCode() : 0);
		return result;
	}

}
