package com.ninjasul.tobyspring31.user.sqlservice;

import com.ninjasul.tobyspring31.learningtest.jdk.jaxb.SqlType;
import com.ninjasul.tobyspring31.learningtest.jdk.jaxb.Sqlmap;
import com.ninjasul.tobyspring31.user.dao.UserDao;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.sql.SQLRecoverableException;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class XmlSqlService implements SqlService, SqlRegistry, SqlReader {

    private Map<String, String> sqlMap = new HashMap<>();

    @Value("/sql/sqlmap.xml")
    private String sqlmapFile;

    @Autowired
    @Setter
    private SqlReader sqlReader;

    @Autowired
    @Setter
    private SqlRegistry sqlRegistry;

    public XmlSqlService() {}

    @PostConstruct
    public void loadSql() {
        sqlReader.read(sqlRegistry);
    }

    @Override
    public String getSql(String key) throws SqlRetrievalFailureException {
        try {
            return sqlRegistry.findSql(key);
        }
        catch( SqlNotFoundException e ) {
            throw new SqlRetrievalFailureException(e);
        }
    }

    @Override
    public void read(SqlRegistry sqlRegistry) {
        String contextPath = Sqlmap.class.getPackage().getName();

        try {
            JAXBContext context = JAXBContext.newInstance(contextPath);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            InputStream is = UserDao.class.getResourceAsStream(sqlmapFile);
            Sqlmap sqlmap = (Sqlmap)unmarshaller.unmarshal(is);

            for( SqlType sql : sqlmap.getSql() ) {
                sqlRegistry.registerSql(sql.getKey(), sql.getValue());
            }

        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registerSql(String key, String sql) {
        sqlMap.put( key, sql );
    }

    @Override
    public String findSql(String key) throws SqlNotFoundException {
        String sql = sqlMap.get(key);

        if( sql == null ) {
            throw new SqlNotFoundException( key + "에 대한 SQL을 찾을 수 없습니다.");
        }
        else {
            return sql;
        }
    }
}