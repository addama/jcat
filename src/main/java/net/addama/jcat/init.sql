drop table item;

create table item(
	id 			integer primary key autoincrement,
	name 		varchar(100) not null,
	slot 		varchar(50) not null,
	slotlevel 	tinyint not null,
	group1 		varchar(50) not null,
	group2 		varchar(50) not null,
	brand 		varchar(50),
	notes 		varchar(250),
	created 	timestamp not null default now(),
	purchased 	timestamp,
	price 		decimal,
	formality 	varchar(25),
	material 	varchar(50),
	warmth 		varchar(25),
	fit 		varchar(25),
	sizing 		varchar(5),
	sleeve 		varchar(25),
	pattern 	varchar(25),
	color1 		varchar(50) not null,
	color2 		varchar(50),
	color3 		varchar(50)
);

insert into item (name, slot, slotlevel, group1, group2, created, color1) values (
	"item name",
	"slot",
	0,
	"group1",
	"group2",
	1529532576,
	"beige"
);