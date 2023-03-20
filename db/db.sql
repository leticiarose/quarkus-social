CREATE DATABASE quarkus-social;

CREATE TABLE users(
	id bigserial primary key,
	name varchar(100) not null,
	age integer not null
)

CREATE TABLE posts(
    id bigserial primary key,
    post_text varchar(150) not null,
    dateTime timestamp not null,
    user_id bigint not null references  users(id)
);

CREATE TABLE FOLLOWERS(
    id bigserial primary key,
    user_id bigint not null references USERS(id),
    follower_id bigint not null references USERS(id)
);