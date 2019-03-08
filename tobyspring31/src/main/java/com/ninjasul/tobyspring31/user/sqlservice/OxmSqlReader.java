package com.ninjasul.tobyspring31.user.sqlservice;

import com.ninjasul.tobyspring31.learningtest.jdk.jaxb.SqlType;
import com.ninjasul.tobyspring31.learningtest.jdk.jaxb.Sqlmap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;

@Component
public class OxmSqlReader implements SqlReader {

    @Value("/sql/sqlmap.xml")
    private String sqlmapFile;

    @Autowired
    private Unmarshaller unmarshaller;

    @Override
    public void read(SqlRegistry sqlRegistry) {
        try {
            Source source = new StreamSource( getClass().getResourceAsStream(sqlmapFile));
            Sqlmap sqlmap = (Sqlmap)unmarshaller.unmarshal(source);

            for( SqlType sql : sqlmap.getSql() ) {
                sqlRegistry.registerSql(sql.getKey(), sql.getValue());
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}