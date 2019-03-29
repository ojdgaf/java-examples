DROP TABLE IF EXISTS "courses" CASCADE;
DROP SEQUENCE IF EXISTS courses_id_seq CASCADE;
CREATE SEQUENCE courses_id_seq START 6;

CREATE TABLE "public"."courses" (
    "id" integer DEFAULT nextval('courses_id_seq') NOT NULL,
    "name" character varying(50) NOT NULL,
    CONSTRAINT "courses_pk" PRIMARY KEY ("id")
) WITH (oids = false);

INSERT INTO "courses" ("id", "name") VALUES
(1, 'Java 11 Complete Edition'),
(2, 'Spring MVC'),
(3, 'Spring Security'),
(4, 'Spring Data'),
(5, 'Hibernate ORM');




DROP TABLE IF EXISTS "students" CASCADE;
DROP SEQUENCE IF EXISTS students_id_seq CASCADE;
CREATE SEQUENCE students_id_seq START 3;

CREATE TABLE "public"."students" (
    "id" integer DEFAULT nextval('students_id_seq') NOT NULL,
    "first_name" character varying(50) NOT NULL,
    "last_name" character varying(50) NOT NULL,
    CONSTRAINT "students_pk" PRIMARY KEY ("id")
) WITH (oids = false);

INSERT INTO "students" ("id", "first_name", "last_name") VALUES
(2, 'Johnny',   'Depp'),
(1,	'Tom',	'Hardy');



DROP TABLE IF EXISTS "record_books" CASCADE;
DROP SEQUENCE IF EXISTS record_books_id_seq CASCADE;
CREATE SEQUENCE record_books_id_seq START 3;

CREATE TABLE "public"."record_books" (
    "id" integer DEFAULT nextval('record_books_id_seq') NOT NULL,
    "student_id" integer NOT NULL,
    "serial_number" character varying(50) NOT NULL,
    CONSTRAINT "record_books_pk" PRIMARY KEY ("id"),
    CONSTRAINT "record_book_student_fk" FOREIGN KEY (student_id) REFERENCES students(id) NOT DEFERRABLE
) WITH (oids = false);

INSERT INTO "record_books" ("id", "student_id", "serial_number") VALUES
(1,	1,	'hardy_768'),
(2,	2,	'depp_240');




DROP TABLE IF EXISTS "phones" CASCADE;
DROP SEQUENCE IF EXISTS phones_id_seq CASCADE;
CREATE SEQUENCE phones_id_seq START 4;

CREATE TABLE "public"."phones" (
    "id" integer DEFAULT nextval('phones_id_seq') NOT NULL,
    "student_id" integer NOT NULL,
    "number" character varying(15) NOT NULL,
    CONSTRAINT "phones_pk" PRIMARY KEY ("id"),
    CONSTRAINT "phone_student_fk" FOREIGN KEY (student_id) REFERENCES students(id) NOT DEFERRABLE
) WITH (oids = false);

INSERT INTO "phones" ("id", "student_id", "number") VALUES
(1,	1,	'1112223344'),
(2,	2,	'5556667788'),
(3,	2,	'0009990011');



DROP TABLE IF EXISTS "student_course" CASCADE;
CREATE TABLE "public"."student_course" (
    "student_id" integer NOT NULL,
    "course_id" integer NOT NULL,
    CONSTRAINT "student_course_ck" UNIQUE ("student_id", "course_id"),
    CONSTRAINT "student_course_course_fk" FOREIGN KEY (course_id) REFERENCES courses(id) NOT DEFERRABLE,
    CONSTRAINT "student_course_student_fk" FOREIGN KEY (student_id) REFERENCES students(id) NOT DEFERRABLE
) WITH (oids = false);

INSERT INTO "student_course" ("student_id", "course_id") VALUES
(1,	1),
(1,	2),
(1,	5),
(2,	1),
(2,	4);