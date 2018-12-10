package com.ninjasul.spring.domain;


import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Journal {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;

	private String title;
	private Date created;
	private String summary;

	// @Transient 어노테이션은 값을 DB에 저장하지 않음
	@Transient
	private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

	// 무인자 생성자
	Journal() {}

	public Journal(String title, String summary, String date) throws ParseException {
		this.title = title;
		this.summary = summary;
		this.created = format.parse(date);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Override
	public String toString() {
		StringBuilder value = new StringBuilder("JournalEntity(");
		value.append("Id: ");
		value.append(id);
		value.append(", 제목: ");
		value.append(title);
		value.append(", 요약: ");
		value.append(summary);
		value.append(", 일자: ");
		value.append(created);
		value.append(")");
		return value.toString();
	}
}
