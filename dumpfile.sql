--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: blog_comment; Type: TABLE; Schema: public; Owner: bloguser; Tablespace: 
--

CREATE TABLE blog_comment (
    id bigint NOT NULL,
    post_id integer,
    name character varying(255),
    created timestamp without time zone,
    email character varying(255),
    body text
);


ALTER TABLE blog_comment OWNER TO bloguser;

--
-- Name: blog_comment_id_seq; Type: SEQUENCE; Schema: public; Owner: bloguser
--

CREATE SEQUENCE blog_comment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE blog_comment_id_seq OWNER TO bloguser;

--
-- Name: blog_comment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: bloguser
--

ALTER SEQUENCE blog_comment_id_seq OWNED BY blog_comment.id;


--
-- Name: blog_post; Type: TABLE; Schema: public; Owner: bloguser; Tablespace: 
--

CREATE TABLE blog_post (
    id bigint NOT NULL,
    user_id integer,
    title character varying(255),
    created timestamp without time zone,
    body text
);


ALTER TABLE blog_post OWNER TO bloguser;

--
-- Name: blog_post_id_seq; Type: SEQUENCE; Schema: public; Owner: bloguser
--

CREATE SEQUENCE blog_post_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE blog_post_id_seq OWNER TO bloguser;

--
-- Name: blog_post_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: bloguser
--

ALTER SEQUENCE blog_post_id_seq OWNED BY blog_post.id;


--
-- Name: play_evolutions; Type: TABLE; Schema: public; Owner: bloguser; Tablespace: 
--

CREATE TABLE play_evolutions (
    id integer NOT NULL,
    hash character varying(255) NOT NULL,
    applied_at timestamp without time zone NOT NULL,
    apply_script text,
    revert_script text,
    state character varying(255),
    last_problem text
);


ALTER TABLE play_evolutions OWNER TO bloguser;

--
-- Name: id; Type: DEFAULT; Schema: public; Owner: bloguser
--

ALTER TABLE ONLY blog_comment ALTER COLUMN id SET DEFAULT nextval('blog_comment_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: bloguser
--

ALTER TABLE ONLY blog_post ALTER COLUMN id SET DEFAULT nextval('blog_post_id_seq'::regclass);


--
-- Data for Name: blog_comment; Type: TABLE DATA; Schema: public; Owner: bloguser
--

COPY blog_comment (id, post_id, name, created, email, body) FROM stdin;
1	1	id labore ex et quam laborum	2017-01-16 11:04:22.840489	Eliseo@gardner.biz	itaque id aut magnam\\npraesentium quia et ea odit et ea voluptas et\\nsapiente quia nihil amet occaecati quia id voluptatem\\nincidunt ea est distinctio odio
2	1	quo vero reiciendis velit similique earum	2017-01-16 11:05:03.374599	Jayne_Kuhic@sydney.com	est natus enim nihil est dolore omnis voluptatem numquam\\net omnis occaecati quod ullam at\\nvoluptatem error expedita pariatur\\nnihil sint nostrum voluptatem reiciendis et
3	1	odio adipisci rerum aut animi	2017-01-16 11:05:54.25841	Nikita@garfield.biz	quia molestiae reprehenderit quasi aspernatur\\naut expedita occaecati aliquam eveniet laudantium\\nomnis quibusdam delectus saepe quia accusamus maiores nam est\\ncum et ducimus et vero voluptates excepturi deleniti ratione
4	2	et fugit eligendi deleniti quidem qui sint nihil autem	2017-01-16 11:06:37.627413	Presley.Mueller@myrl.com	doloribus at sed quis culpa deserunt consectetur qui praesentium\\naccusamus fugiat dicta\\nvoluptatem rerum ut voluptate autem\\nvoluptatem repellendus aspernatur dolorem in
5	3	et deleniti quidem qui sint nihil autem	2017-01-16 11:31:52.491151	Tom.Jones@myrl.com	doloribus at sed quis culpa deserunt consectetur qui praesentium\\naccusamus fugiat dicta\\nvoluptatem rerum ut voluptate autem\\nvoluptatem repellendus aspernatur dolorem in
6	3	voluptatem repellendus aspernatur dolorem in	2017-01-16 11:32:29.154469	Patrick.Moorehouse@rl.com	doloribus at sed quis culpa deserunt consectetur qui praesentium\\naccusamus fugiat dicta\\nvoluptatem rerum ut voluptate autem\\nvoluptatem repellendus aspernatur dolorem in
7	4	dolorem in	2017-01-16 11:32:58.596448	M.lamb@regal.com	voluptatem repellendus aspernatur doloribus at sed quis culpa deserunt consectetur qui praesentium\\naccusamus fugiat dicta\\nvoluptatem rerum ut voluptate autem\\nvoluptatem repellendus aspernatur dolorem in
\.


--
-- Name: blog_comment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: bloguser
--

SELECT pg_catalog.setval('blog_comment_id_seq', 7, true);


--
-- Data for Name: blog_post; Type: TABLE DATA; Schema: public; Owner: bloguser
--

COPY blog_post (id, user_id, title, created, body) FROM stdin;
1	1	sunt aut facere repellat provident occaecati excepturi optio reprehenderit	2017-01-16 10:53:32.160315	quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto
2	1	qui est esse	2017-01-16 10:54:58.751989	suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto
3	1	ea molestias quasi exercitationem repellat qui ipsa sit aut	2017-01-16 10:55:24.368625	recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto
4	2	in quibusdam tempore odit est dolorem	2017-01-16 10:56:04.15992	itaque id aut magnam\\npraesentium quia et ea odit et ea voluptas et\\nsapiente quia nihil amet occaecati quia id voluptatem\\nincidunt ea est distinctio odio
\.


--
-- Name: blog_post_id_seq; Type: SEQUENCE SET; Schema: public; Owner: bloguser
--

SELECT pg_catalog.setval('blog_post_id_seq', 4, true);


--
-- Data for Name: play_evolutions; Type: TABLE DATA; Schema: public; Owner: bloguser
--

COPY play_evolutions (id, hash, applied_at, apply_script, revert_script, state, last_problem) FROM stdin;
1	358ea0d2f8051ff911ea63ca9685fd3b706f92bd	2017-01-16 10:23:51.058	create table blog_comment (\nid                            bigserial not null,\npost_id                       integer,\nname                          varchar(255),\ncreated                       timestamp,\nemail                         varchar(255),\nbody                          TEXT,\nconstraint pk_blog_comment primary key (id)\n);\n\ncreate table blog_post (\nid                            bigserial not null,\nuser_id                       integer,\ntitle                         varchar(255),\ncreated                       timestamp,\nbody                          TEXT,\nconstraint pk_blog_post primary key (id)\n);	drop table if exists blog_comment cascade;\n\ndrop table if exists blog_post cascade;	applied	
\.


--
-- Name: pk_blog_comment; Type: CONSTRAINT; Schema: public; Owner: bloguser; Tablespace: 
--

ALTER TABLE ONLY blog_comment
    ADD CONSTRAINT pk_blog_comment PRIMARY KEY (id);


--
-- Name: pk_blog_post; Type: CONSTRAINT; Schema: public; Owner: bloguser; Tablespace: 
--

ALTER TABLE ONLY blog_post
    ADD CONSTRAINT pk_blog_post PRIMARY KEY (id);


--
-- Name: play_evolutions_pkey; Type: CONSTRAINT; Schema: public; Owner: bloguser; Tablespace: 
--

ALTER TABLE ONLY play_evolutions
    ADD CONSTRAINT play_evolutions_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: jamescoll
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM jamescoll;
GRANT ALL ON SCHEMA public TO jamescoll;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

