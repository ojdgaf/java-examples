--
-- PostgreSQL database dump
--

-- Dumped from database version 11.1 (Debian 11.1-1.pgdg90+1)
-- Dumped by pg_dump version 11.1 (Debian 11.1-1.pgdg90+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: university; Type: DATABASE; Schema: -; Owner: root
--

CREATE DATABASE university WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.utf8' LC_CTYPE = 'en_US.utf8';


ALTER DATABASE university OWNER TO root;

\connect university

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: courses_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.courses_id_seq
    START WITH 6
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.courses_id_seq OWNER TO root;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: courses; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.courses (
    id integer DEFAULT nextval('public.courses_id_seq'::regclass) NOT NULL,
    name character varying(50) NOT NULL
);


ALTER TABLE public.courses OWNER TO root;

--
-- Name: faculties_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.faculties_id_seq
    START WITH 5
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.faculties_id_seq OWNER TO root;

--
-- Name: faculties; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.faculties (
    id integer DEFAULT nextval('public.faculties_id_seq'::regclass) NOT NULL,
    name character varying(100) NOT NULL
);


ALTER TABLE public.faculties OWNER TO root;

--
-- Name: phones_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.phones_id_seq
    START WITH 4
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.phones_id_seq OWNER TO root;

--
-- Name: phones; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.phones (
    id integer DEFAULT nextval('public.phones_id_seq'::regclass) NOT NULL,
    student_id integer NOT NULL,
    number character varying(15) NOT NULL
);


ALTER TABLE public.phones OWNER TO root;

--
-- Name: record_books_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.record_books_id_seq
    START WITH 3
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.record_books_id_seq OWNER TO root;

--
-- Name: record_books; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.record_books (
    id integer DEFAULT nextval('public.record_books_id_seq'::regclass) NOT NULL,
    student_id integer NOT NULL,
    serial_number character varying(50) NOT NULL
);


ALTER TABLE public.record_books OWNER TO root;

--
-- Name: student_course; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.student_course (
    student_id integer NOT NULL,
    course_id integer NOT NULL
);


ALTER TABLE public.student_course OWNER TO root;

--
-- Name: students_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.students_id_seq
    START WITH 3
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.students_id_seq OWNER TO root;

--
-- Name: students; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.students (
    id integer DEFAULT nextval('public.students_id_seq'::regclass) NOT NULL,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL
);


ALTER TABLE public.students OWNER TO root;

--
-- Data for Name: courses; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.courses (id, name) FROM stdin;
1	Java 11 Complete Edition
2	Spring MVC
3	Spring Security
4	Spring Data
5	Hibernate ORM
\.


--
-- Data for Name: faculties; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.faculties (id, name) FROM stdin;
1	IT Faculty
2	Faculty of Arts
3	Faculty of Science
4	School of Business, Economics and Law
\.


--
-- Data for Name: phones; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.phones (id, student_id, number) FROM stdin;
1	1	1112223344
2	2	5556667788
3	2	0009990011
\.


--
-- Data for Name: record_books; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.record_books (id, student_id, serial_number) FROM stdin;
1	1	hardy_768
2	2	depp_240
\.


--
-- Data for Name: student_course; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.student_course (student_id, course_id) FROM stdin;
1	1
1	2
1	5
2	1
2	4
\.


--
-- Data for Name: students; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.students (id, first_name, last_name) FROM stdin;
2	Johnny	Depp
1	Tom	Hardy
\.


--
-- Name: courses_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.courses_id_seq', 6, false);


--
-- Name: faculties_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.faculties_id_seq', 5, false);


--
-- Name: phones_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.phones_id_seq', 4, false);


--
-- Name: record_books_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.record_books_id_seq', 3, false);


--
-- Name: students_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.students_id_seq', 3, false);


--
-- Name: courses courses_pk; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.courses
    ADD CONSTRAINT courses_pk PRIMARY KEY (id);


--
-- Name: phones phones_pk; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.phones
    ADD CONSTRAINT phones_pk PRIMARY KEY (id);


--
-- Name: record_books record_books_pk; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.record_books
    ADD CONSTRAINT record_books_pk PRIMARY KEY (id);


--
-- Name: student_course student_course_ck; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.student_course
    ADD CONSTRAINT student_course_ck UNIQUE (student_id, course_id);


--
-- Name: students students_pk; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.students
    ADD CONSTRAINT students_pk PRIMARY KEY (id);


--
-- Name: phones phone_student_fk; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.phones
    ADD CONSTRAINT phone_student_fk FOREIGN KEY (student_id) REFERENCES public.students(id);


--
-- Name: record_books record_book_student_fk; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.record_books
    ADD CONSTRAINT record_book_student_fk FOREIGN KEY (student_id) REFERENCES public.students(id);


--
-- Name: student_course student_course_course_fk; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.student_course
    ADD CONSTRAINT student_course_course_fk FOREIGN KEY (course_id) REFERENCES public.courses(id);


--
-- Name: student_course student_course_student_fk; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.student_course
    ADD CONSTRAINT student_course_student_fk FOREIGN KEY (student_id) REFERENCES public.students(id);


--
-- PostgreSQL database dump complete
--