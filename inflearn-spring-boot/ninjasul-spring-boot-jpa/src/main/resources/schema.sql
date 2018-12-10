-- migration 툴(flyway)을 사용 함에 따라 아래 스키마 생성 쿼리는 사용하지 않음.

-- drop table if exists hibernate_sequence;
-- create table account (id bigint not null, password varchar(255), username varchar(255), primary key (id));
-- create table hibernate_sequence (next_val bigint);
-- insert into hibernate_sequence values ( 1 );
