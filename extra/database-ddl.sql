CREATE TABLE IF NOT EXISTS tb_address
(
    mentor_id bigint unique
        constraint fk_mentor references tb_mentor,
    country   varchar(100) not null,
    region    varchar(100),
    city      varchar(100),
    district  varchar(100) not null,
    street    varchar(100) not null,
    apartment varchar(50)  not null,
    date_created timestamp
);

CREATE TABLE IF NOT EXISTS tb_course
(
    id               serial primary key,
    name             varchar(30) not null,
    course_format_id bigint      not null
        constraint fk_course
            references tb_course_format,
    date_created     timestamp   not null
);

CREATE TABLE IF NOT EXISTS tb_course_format
(
    id                    serial primary key,
    name                  varchar(30) not null,
    duration_in_week      integer     not null,
    is_online             boolean default false,
    lesson_duration       integer     not null,
    lesson_count_per_week integer     not null,
    date_created          timestamp   not null
);

CREATE TABLE IF NOT EXISTS tb_group
(
    id           serial primary key,
    name         varchar(50) not null,
    room         varchar(50) not null,
    start_time   time,
    mentor_id    bigint
        constraint fk_mentor_id references tb_mentor (id),
    course_id    bigint
        constraint fk_group_id references tb_course (id),
    date_created timestamp   not null
);

CREATE TABLE IF NOT EXISTS tb_student
(
    id           serial primary key,
    name         varchar(50) not null,
    surname      varchar(50) not null,
    phone        varchar(12) not null unique,
    email        varchar(50) not null unique,
    dob          date,
    date_created timestamp   not null
);

CREATE TABLE IF NOT EXISTS student_group
(
    student_id bigint not null
        constraint fk_student_id references tb_student (id),
    group_id   bigint not null
        constraint fk_group_id references tb_group (id)
);

CREATE TABLE IF NOT EXISTS tb_manager
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(30)  NOT NULL,
    surname      VARCHAR(50)  NOT NULL,
    phone        VARCHAR(12)  NOT NULL,
    email        VARCHAR(100) NOT NULL,
    salary       DECIMAL      NOT NULL,
    date_created TIMESTAMP    NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_mentor
(
    id           serial primary key,
    name         varchar(50) not null,
    surname      varchar(50) not null,
    phone        varchar(12) not null unique,
    email        varchar(50) not null unique,
    experience   numeric     not null
        constraint tb_mentor_experience_check
            check (experience > (1)::numeric),
    salary_for_lesson numeric check (salary_for_lesson > 0),
    date_created timestamp   not null
);
