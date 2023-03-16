CREATE DATABASE quarkus-social;

CREATE TABLE users(
	id bigserial primary key,
	name varchar(100) not null,
	age integer not null
)
