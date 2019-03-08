package com.ninjasul.tobyspring31.user.sqlservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Service
@Primary
public class OxmSqlService implements SqlService {

    @Resource(name="oxmSqlReader")
    private SqlReader sqlReader;

    @Autowired
    private SqlRegistry sqlRegistry;

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
}