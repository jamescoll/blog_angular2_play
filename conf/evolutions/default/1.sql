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

create table blog_post (
  id                            bigserial not null,
  user_id                       integer,
  title                         varchar(255),
  created                       timestamp,
  body                          TEXT,
  constraint pk_blog_post primary key (id)
);


# --- !Downs

drop table if exists blog_comment cascade;

drop table if exists blog_post cascade;

