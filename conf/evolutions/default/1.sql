# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table blog_comment (
  id                            bigserial not null,
  post_id                       integer,
  name                          varchar(255),
  created                       timestamp,
  email                         varchar(255),
  body                          TEXT,
  constraint pk_blog_comment primary key (id)
);

create table blog_log_message (
  id                            bigserial not null,
  timestamp                     timestamp,
  log_level                     varchar(255),
  log_message                   TEXT,
  request_id                    varchar(255),
  client_address                varchar(255),
  constraint pk_blog_log_message primary key (id)
);

create table blog_login_record (
  id                            bigserial not null,
  username                      varchar(255),
  timestamp                     timestamp,
  valid_login                   boolean,
  reason                        varchar(255),
  constraint pk_blog_login_record primary key (id)
);

create table blog_post (
  id                            bigserial not null,
  user_id                       integer,
  title                         varchar(255),
  created                       timestamp,
  body                          TEXT,
  constraint pk_blog_post primary key (id)
);

create table blog_security_role (
  id                            bigint not null,
  name                          varchar(255),
  constraint pk_blog_security_role primary key (id)
);
create sequence blog_security_role_seq;

create table blog_user (
  id                            uuid not null,
  first_name                    varchar(255),
  last_name                     varchar(255),
  username                      varchar(255),
  first_time_user               boolean,
  constraint uq_blog_user_username unique (username),
  constraint pk_blog_user primary key (id)
);

create table blog_user_roles (
  user_id                       uuid not null,
  role_id                       bigint not null,
  constraint pk_blog_user_roles primary key (user_id,role_id)
);

create table blog_userpassword (
  user_id                       uuid not null,
  hashed_password               varchar(255),
  change                        boolean,
  constraint pk_blog_userpassword primary key (user_id)
);

alter table blog_user_roles add constraint fk_blog_user_roles_blog_user foreign key (user_id) references blog_user (id) on delete restrict on update restrict;
create index ix_blog_user_roles_blog_user on blog_user_roles (user_id);

alter table blog_user_roles add constraint fk_blog_user_roles_blog_security_role foreign key (role_id) references blog_security_role (id) on delete restrict on update restrict;
create index ix_blog_user_roles_blog_security_role on blog_user_roles (role_id);


# --- !Downs

alter table if exists blog_user_roles drop constraint if exists fk_blog_user_roles_blog_user;
drop index if exists ix_blog_user_roles_blog_user;

alter table if exists blog_user_roles drop constraint if exists fk_blog_user_roles_blog_security_role;
drop index if exists ix_blog_user_roles_blog_security_role;

drop table if exists blog_comment cascade;

drop table if exists blog_log_message cascade;

drop table if exists blog_login_record cascade;

drop table if exists blog_post cascade;

drop table if exists blog_security_role cascade;
drop sequence if exists blog_security_role_seq;

drop table if exists blog_user cascade;

drop table if exists blog_user_roles cascade;

drop table if exists blog_userpassword cascade;

