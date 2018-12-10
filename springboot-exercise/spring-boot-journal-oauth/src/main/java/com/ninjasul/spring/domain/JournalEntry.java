package com.ninjasul.spring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ninjasul.spring.utils.JsonDateSerializer;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="entry")
public class JournalEntry {
    @Id
    /***************************************************************************
     * GenerationType.AUTO 를 사용할 경우 data.sql 파일을 통한 데이터 삽입 시
     * ID 값에 null 이 들어가는 오류가 발생함.
     **************************************************************************/
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Date created;
    private String summary;

    @Transient
    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public JournalEntry(String title, String summary, String date) throws ParseException {
        this.title = title;
        this.summary = summary;
        this.created = format.parse(date);
    }

    JournalEntry() {}

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

    @JsonSerialize(using= JsonDateSerializer.class)
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

    @JsonIgnore
    public String getCreatedAsShort() {
        return format.format(created);
    }

    public String toString() {
        StringBuilder value = new StringBuilder("* JournalEntry(");

        value.append("Id: ");
        value.append(id);
        value.append(", 제목: " );
        value.append(title);
        value.append(", 요약: " );
        value.append(summary);
        value.append(", 작성일자: " );
        value.append(getCreatedAsShort());
        value.append(")");

        return value.toString();
    }
}