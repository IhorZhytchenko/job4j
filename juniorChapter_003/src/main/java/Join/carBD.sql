create table transmission (
	id serial primary key,
	name varchar(500)
);
create table engine (
	id serial primary key,
	name varchar(500)
);
create table body (
	id serial primary key,
	name varchar(500)
);
create table car (
	id serial primary key,
	model varchar(500),
	transmission_id integer references transmission(id),
	engine_id integer references engine(id),
	body_id integer references body(id)	
);

insert into transmission (name) values ('mechanical'),('robotic'),('automatic'),('variator');

insert into engine (name) values ('diesel 2.0'),('diesel 3.5'),('gas 2.0'),('gas 5.5'),('electric 187kw');

insert into body (name) values ('sedan'),('hatchback'),('wagon'),('SUV');

insert into car (model, transmission_id, engine_id, body_id) values
('Audi A6', 3, 3, 1), ('BMW X5', 1, 2, 4), ('Nissan Leaf', 2, 5, 2);

select c.model, t.name as transmission, e.name as engine, b.name as body from car as c 
left outer join transmission as t on t.id = c.transmission_id
left outer join engine as e on e.id = c.engine_id
left outer join body as b on b.id = c.body_id;

select t.name from transmission as t left outer join car as c on c.transmission_id = t.id where c.id is null;

select e.name from engine as e left outer join car as c on c.engine_id = e.id where c.id is null;

select b.name from body as b left outer join car as c on c.body_id = b.id where c.id is null;
 
 