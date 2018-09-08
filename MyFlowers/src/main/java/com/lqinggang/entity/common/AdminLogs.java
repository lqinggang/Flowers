package com.lqinggang.entity.common;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.lqinggang.entity.users.Person;

/**
 * @author LQingGang
 * @time 2018年4月21日 - 下午9:34:57
 */
@Entity
@Table(name = "logs", schema = "flowers_db", catalog = "")
public class AdminLogs implements Serializable {

	private static final long serialVersionUID = -4737480140517970504L;

	private int logs_id;// 日志编号
	private Person person_id; // 管理员
	private String ip = "127.0.0.1"; // 登录IP
	private Timestamp date = Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	private String content = "NULL";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "logs_id", nullable = false)
	public int getLogs_id() {
		return logs_id;
	}

	public void setLogs_id(int logs_id) {
		this.logs_id = logs_id;
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
	@Column(name = "ipaddress", nullable = false, length = 64)
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Basic
	@Column(name = "date", nullable = false)
	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Basic
	@Column(name = "content", nullable = false, length = 255)
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
		if (obj != null && getClass() == obj.getClass()) {
			return true;
		}
		AdminLogs adminLogs = (AdminLogs) obj;
		if (person_id != adminLogs.person_id) {
			return false;
		}
		if ((ip != null ? !ip.equals(adminLogs.ip) : adminLogs.ip != null)) {
			return false;
		}
		if ((date != null ? !date.equals(adminLogs.date) : adminLogs.date != null)) {
			return false;
		}
		if ((content != null ? !content.equals(adminLogs.content) : adminLogs.content != null)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = person_id.hashCode();
		result = result * 31 + (ip == null ? 0 : ip.hashCode());
		result = result * 31 + (date == null ? 0 : date.hashCode());
		result = result * 31 + (content == null ? 0 : content.hashCode());
		return result;
	}

}
