drop database if exists ehospital;
create database ehospital;
use ehospital;


drop table if exists admin;
create table admin (
	adminid bigint not null auto_increment,
	address varchar(100),
	age integer,
	contact_no integer,
	email varchar(50) not null unique,
	name varchar(50) not null,
	password varchar(15) not null,
	username varchar(20) not null unique,
    active boolean not null,
	id bigint,
    primary key (adminid)
-- 	CONSTRAINT foreign key (id) references adminimage (id)
) engine=InnoDB;


drop table if exists doctor;
create table doctor (
	doctorid bigint not null auto_increment,
	address varchar(100) not null,
	age integer not null,
	contact_no integer,
	department varchar(50) not null,
	email varchar(50) not null unique,
	name varchar(50) not null,
	password varchar(15) not null,
	specialization varchar(100) not null,
	username varchar(20) not null unique,
    active boolean not null,
	id bigint,
    primary key (doctorid)
--     CONSTRAINT foreign key (id) references doctorimage (id)
) engine=InnoDB;

  
drop table if exists user;
create table user (
	userid bigint not null auto_increment,
	address varchar(100),
	age integer,
	contact_no integer,
	email varchar(50) not null unique,
	name varchar(50) not null,
	password varchar(15) not null,
	username varchar(20) not null unique,
    active boolean not null,
	id bigint,
    primary key (userid)
-- 	CONSTRAINT foreign key (id) references userimage (id)
) engine=InnoDB;


drop table if exists schedule;
create table schedule (
   scheduleid bigint not null auto_increment,
	date date not null,
    from_time time not null,
	to_time time not null,
	doctorid bigint not null,
	primary key (scheduleid),
	CONSTRAINT foreign key (doctorid) references doctor (doctorid) ON DELETE CASCADE
) engine=InnoDB;


drop table if exists booking;
create table booking (
   bookingid bigint not null auto_increment,
	contact_no integer,
	date date not null,
	department varchar(50) not null,
	email varchar(50) not null,
	time time not null,
	doctorid bigint not null,
	scheduleid bigint not null,
    userid bigint not null,
	primary key (bookingid),
	CONSTRAINT foreign key (doctorid) references doctor (doctorid) ON DELETE CASCADE,
	CONSTRAINT foreign key (scheduleid) references schedule (scheduleid) ON DELETE CASCADE,
	CONSTRAINT foreign key (userid) references user (userid) ON DELETE CASCADE
) engine=InnoDB;


drop table if exists doctorimage;
create table doctorimage (
	id bigint not null auto_increment,
	doctorid bigint unique,
	name varchar(255),
	pic_byte blob,
	type varchar(255),
	primary key (id),
	CONSTRAINT foreign key (doctorid) references doctor (doctorid) ON DELETE CASCADE
) engine=InnoDB;


drop table if exists adminimage;
create table adminimage (
	id bigint not null auto_increment,
	adminid bigint unique,
	name varchar(255),
	pic_byte blob,
	type varchar(255),
	primary key (id),
	CONSTRAINT foreign key (adminid) references admin (adminid) ON DELETE CASCADE
) engine=InnoDB;


drop table if exists userimage;
create table userimage (
	id bigint not null auto_increment,
	userid bigint unique,
	name varchar(255),
	pic_byte blob,
	type varchar(255),
	primary key (id),
	CONSTRAINT foreign key (userid) references user (userid) ON DELETE CASCADE
) engine=InnoDB;


create index DEPT_INDX_1 on doctor (department);
create index USERNAME_INDX_1 on doctor (username);
create index EMAIL_INDX_1 on doctor (email);
create index USERNAME_INDX_2 on user (username);
create index EMAIL_INDX_2 on user (email);