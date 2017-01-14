# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table login_info (
  id                            bigserial not null,
  name                          varchar(255),
  staff_member                  boolean,
  date                          timestamp,
  constraint pk_login_info primary key (id)
);


# --- !Downs

drop table if exists login_info cascade;

