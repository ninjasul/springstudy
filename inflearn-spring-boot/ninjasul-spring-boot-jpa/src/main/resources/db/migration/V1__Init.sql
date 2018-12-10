drop table if exists account;
drop table if exists hibernate_sequence;
create table account (id bigint not null, password varchar(255), username varchar(255), primary key (id));
create table hibernate_sequence (next_val bigint);
insert into hibernate_sequence values ( 1 );