package com.ninjasul.tobyspring31.user.sqlservice;

import com.ninjasul.tobyspring31.learningtest.jdk.jaxb.SqlType;
import com.ninjasul.tobyspring31.learningtest.jdk.jaxb.Sqlmap;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Component;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;

@Component
public class OxmSqlReader implements SqlReader {

    @Setter
    @Value("classpath:/sql/sqlmap.xml")
    private Resource sqlmap;

    @Autowired
    private Unmarshaller unmarshaller;

    @Override
    public void read(SqlRegistry sqlRegistry) {
        try {
            Source source = new StreamSource( sqlmap.getInputStream() );
            Sqlmap sqlmap = (Sqlmap)unmarshaller.unmarshal(source);

            for( SqlType sql : sqlmap.getSql() ) {
                sqlRegistry.registerSql(sql.getKey(), sql.getValue());
            }

        }
        catch (IOException e) {
            throw new IllegalArgumentException(sqlmap.getFilename() + "을 가져올 수 없습니다.", e);
        }
    }
}