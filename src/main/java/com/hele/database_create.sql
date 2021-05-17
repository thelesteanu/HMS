drop database hms;

create database hms;

use hms;

drop table if exists reservation;
drop table if exists room;
drop table if exists user;
drop table if exists hotel;


CREATE TABLE hotel(
	  id bigint primary key AUTO_INCREMENT,
    hotel_name varchar(50) not null ,
    location varchar(255) not null ,
    description varchar(500),
    employee_number bigint,
    availability boolean,
    earnings bigint
);

CREATE TABLE user (
    id bigint primary key key AUTO_INCREMENT,
    username varchar(50) not null,
    password varchar(500) not null,
    first_name varchar(50) not null ,
    last_name varchar(50) not null ,
    email varchar(50) unique ,
    address varchar(255),
    birth_date Date,
    gender ENUM('MALE','FEMALE') not null ,
    bio varchar(500),
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    role varchar(10),
    hotel_id bigint ,
    constraint user_ibfk_1 foreign key (hotel_id) references hotel(id)
);

CREATE TABLE room(
	  id bigint primary key AUTO_INCREMENT,
    room_number varchar(10),
    size bigint,
    smoking boolean default false,
    pet_friendly boolean default false,
    reserved boolean default false,
    price bigint,
    hotel_id bigint,
    foreign key (hotel_id) references hotel(id)
);

CREATE TABLE reservation(
	  id bigint primary key AUTO_INCREMENT,
    start_date date,
    end_date date,
    room_id bigint,
    foreign key (room_id) references room(id),
    user_id bigint,
    foreign key (user_id) references user(id)
);

