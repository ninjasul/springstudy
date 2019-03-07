package com.ninjasul.tobyspring31.learningtest.jdk.jaxb;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Log4j2
public class JaxbTest {

    @Test
    public void readSqlMap() throws JAXBException {

        String contextPath = Sqlmap.class.getPackage().getName();
        JAXBContext context = JAXBContext.newInstance(contextPath);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        InputStream inputStream = getClass().getResourceAsStream("/jaxb/sqlmap.xml");

        Sqlmap sqlmap = (Sqlmap) unmarshaller.unmarshal(inputStream);

        List<SqlType> sqlList = sqlmap.getSql();

        assertEquals(3, sqlList.size());
        assertEquals("add", sqlList.get(0).getKey());
        assertEquals("insert", sqlList.get(0).getValue());
        assertEquals("get", sqlList.get(1).getKey());
        assertEquals("select", sqlList.get(1).getValue());
        assertEquals("delete", sqlList.get(2).getKey());
        assertEquals("delete", sqlList.get(2).getValue());
    }
}