package com.ninjasul.spring.domain;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Journal {
    @Id
    /***************************************************************************
     * GenerationType.AUTO 를 사용할 경우 data.sql 파일을 통한 데이터 삽입 시
     * ID 값에 null 이 들어가는 오류가 발생함.
     **************************************************************************/
    //@GeneratedValue(strategy= GenerationType.AUTO)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Date created;
    private String summary;

    @Transient
    private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

    public Journal(String title, String summary, String date) throws ParseException {
        this.title = title;
        this.summary = summary;
        this.created = format.parse(date);
    }

    Journal() {}

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

    public SimpleDateFormat getFormat() {
        return format;
    }

    public void setFormat(SimpleDateFormat format) {
        this.format = format;
    }

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