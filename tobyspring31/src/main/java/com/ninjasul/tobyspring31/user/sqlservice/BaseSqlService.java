package com.ninjasul.tobyspring31.user.sqlservice;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Primary
public class BaseSqlService implements SqlService {

    @Autowired
    @Setter
    protected SqlReader sqlReader;

    @Autowired
    @Setter
    protected SqlRegistry sqlRegistry;

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