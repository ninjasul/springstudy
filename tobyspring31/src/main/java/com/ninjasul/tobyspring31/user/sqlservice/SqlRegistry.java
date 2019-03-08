package com.ninjasul.tobyspring31.user.sqlservice;

public interface SqlRegistry {

    void registerSql( String key, String sql );

    String findSql( String key ) throws SqlNotFoundException;
}