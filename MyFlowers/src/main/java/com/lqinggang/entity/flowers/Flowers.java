package com.lqinggang.entity.flowers;

import java.io.Serializable;

import javax.persistence.*;

/**
 * @author LQingGang
 * @time 2018年3月19日 - 下午8:00:53
 */
@Entity
@Table(name = "flowers", schema = "flowers_db", catalog = "")
public class Flowers implements Serializable {
	private static final long serialVersionUID = -3975226207151002516L;
	private int flower_id;
	private String name = "NULL";
	private String keyword = "NULL";
	private String color = "NULL";
	private Integer amount = 1;
	private String image = "static/pages/images/page/logo.png";
	private String origin = "桂林";
	private Integer quantity = 0;
	private Float price = Float.valueOf(0);
	private FlowersCategory category_id;
	private String description = "鲜花描述信息";
	private String content = "static/pages/images/page/logo.png";
	private String content_info;

	@Id
	@Column(name = "flower_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getFlower_id() {
		return flower_id;
	}

	public void setFlower_id(int flower_id) {
		this.flower_id = flower_id;
	}

	@Basic
	@Column(name = "name", nullable = true, length = 512)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	@Column(name = "color", nullable = true, length = 255)
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Basic
	@Column(name = "amount", nullable = true)
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Basic
	@Column(name = "image", nullable = true, length = 512)
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Basic
	@Column(name = "keyword", nullable = true, length = 512)
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Basic
	@Column(name = "origin", nullable = true, length = 64)
	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	@Basic
	@Column(name = "quantity", nullable = true)
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Basic
	@Column(name = "price", nullable = true)
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Basic
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false)
	public FlowersCategory getCategory_id() {
		return category_id;
	}

	public void setCategory_id(FlowersCategory category_id) {
		this.category_id = category_id;
	}

	@Basic
	@Column(name = "description", nullable = true, length = 2048)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Basic
	@Column(name = "content", nullable = true, length = 2048)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Basic
	@Column(name = "content_info", nullable = true, length = 2048)
	public String getContent_info() {
		return content_info;
	}

	public void setContent_info(String content_info) {
		this.content_info = content_info;
	}

	public Flowers() {
	}

	/**
	 * @param flower_id
	 * @param name
	 * @param keyword
	 * @param color
	 * @param amount
	 * @param image
	 * @param origin
	 * @param quantity
	 * @param price
	 * @param category_id
	 * @param description
	 * @param content
	 * @param content_info
	 */
	public Flowers(int flower_id, String name, String keyword, String color, Integer amount, String image,
			String origin, Integer quantity, Float price, FlowersCategory category_id, String description,
			String content, String content_info) {
		super();
		this.flower_id = flower_id;
		this.name = name;
		this.keyword = keyword;
		this.color = color;
		this.amount = amount;
		this.image = image;
		this.origin = origin;
		this.quantity = quantity;
		this.price = price;
		this.category_id = category_id;
		this.description = description;
		this.content = content;
		this.content_info = content_info;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != getClass()) {
			return false;
		}
		Flowers flowers = (Flowers) obj;

		if (category_id != flowers.category_id) {
			return false;
		}
		if ((name != null ? !name.equals(flowers.name) : flowers.name != null)) {
			return false;
		}
		if ((color != null ? !color.equals(flowers.color) : flowers.color != null)) {
			return false;
		}
		if ((amount != null ? !amount.equals(flowers.amount) : flowers.amount != null)) {
			return false;
		}
		if ((image != null ? !image.equals(flowers.image) : flowers.image != null)) {
			return false;
		}
		if ((origin != null ? !origin.equals(flowers.origin) : flowers.origin != null)) {
			return false;
		}
		if ((quantity != null ? !quantity.equals(flowers.quantity) : flowers.quantity != null)) {
			return false;
		}
		if ((price != null ? !price.equals(flowers.price) : flowers.price != null)) {
			return false;
		}
		if ((description != null ? !description.equals(flowers.description) : flowers.description != null)) {
			return false;
		}
		if ((content != null ? !content.equals(flowers.content) : flowers.content != null)) {
			return false;
		}
		if ((content_info != null ? !content_info.equals(flowers.content_info) : flowers.content_info != null)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = category_id.hashCode();
		result = result * 31 + flower_id;
		result = result * 31 + (name != null ? name.hashCode() : 0);
		result = result * 31 + (color != null ? color.hashCode() : 0);
		result = result * 31 + (amount != null ? amount.hashCode() : 0);
		result = result * 31 + (image != null ? image.hashCode() : 0);
		result = result * 31 + (origin != null ? origin.hashCode() : 0);
		result = result * 31 + (quantity != null ? quantity.hashCode() : 0);
		result = result * 31 + (price != null ? price.hashCode() : 0);
		result = result * 31 + (description != null ? description.hashCode() : 0);
		result = result * 31 + (content != null ? content.hashCode() : 0);
		result = result * 31 + (content_info != null ? content_info.hashCode() : 0);
		return result;
	}

}
