package com.lqinggang.entity.flowers;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author LQingGang
 * @time 2018年3月19日 - 下午7:43:19
 */
@Entity
@Table(name = "flowers_category", schema = "flowersCategory_db", catalog = "")
public class FlowersCategory implements Serializable {

	private static final long serialVersionUID = 3334558557070032116L;
	private int category_id;
	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id", nullable = false)
	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	@Basic
	@Column(name = "type_name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		FlowersCategory flowersCategory = (FlowersCategory) obj;

		if ((name != null ? !name.equals(flowersCategory.name) : flowersCategory.name != null)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int result = category_id;
		result = result * 31 + (name != null ? name.hashCode() : 0);
		return result;
	}
}
