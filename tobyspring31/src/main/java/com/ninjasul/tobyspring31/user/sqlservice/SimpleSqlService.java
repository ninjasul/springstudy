package com.ninjasul.tobyspring31.user.sqlservice;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SimpleSqlService implements SqlService {

    @Setter
    @Autowired
    private Map<String, String> sqlMap;

    @Override
    public String getSql(String key) throws SqlRetrievalFailureException {
        String sql = sqlMap.get(key);

        if( sql == null ) {
            throw new SqlRetrievalFailureException(key + "에 대한 SQL을 찾을 수 없습니다.");
        }
        else {
            return sql;
        }

    }
}