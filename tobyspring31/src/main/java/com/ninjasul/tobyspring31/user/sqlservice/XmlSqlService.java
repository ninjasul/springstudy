package com.ninjasul.tobyspring31.user.sqlservice;

import com.ninjasul.tobyspring31.learningtest.jdk.jaxb.SqlType;
import com.ninjasul.tobyspring31.learningtest.jdk.jaxb.Sqlmap;
import com.ninjasul.tobyspring31.user.dao.UserDao;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
@Primary
public class XmlSqlService implements SqlService {

    private Map<String, String> sqlMap = new HashMap<>();

    @Setter
    @Value("/sql/sqlmap.xml")
    private String sqlmapFile;

    public XmlSqlService() {}

    @PostConstruct
    public void loadSql() {
        String contextPath = Sqlmap.class.getPackage().getName();

        try {
            JAXBContext context = JAXBContext.newInstance(contextPath);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            InputStream is = UserDao.class.getResourceAsStream(sqlmapFile);
            Sqlmap sqlmap = (Sqlmap)unmarshaller.unmarshal(is);

            for( SqlType sql : sqlmap.getSql() ) {
                sqlMap.put( sql.getKey(), sql.getValue() );
            }

        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getSql(String key) throws SqlRetrievalFailureException {

        String sql = sqlMap.get(key);

        if( sql == null ) {
            throw new SqlRetrievalFailureException(key + "를 이용해서 SQL을 찾을 수 없습니다.");
        }
        else
            return sql;
    }
}