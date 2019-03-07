package com.ninjasul.tobyspring31.user.sqlservice;

public interface SqlService {
    String getSql(String key) throws SqlRetrievalFailureException;
}