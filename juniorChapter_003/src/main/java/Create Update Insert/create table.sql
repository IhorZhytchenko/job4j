create table rules (
	id serial primary key,
	description text
);
create table roles (
	id serial primary key,
	name character varying (500)
); 
create table roles_rules (
	role_id integer references roles(id),
	rule_id integer references rules(id),
	primary key(role_id, rule_id)
);
create table users (
	id serial primary key,
	login character varying (500),
	password character varying (500),
	role_id integer references roles(id)
);
create table categories (
	id serial primary key,
	name character varying (500)
);
create table states (
	id serial primary key,
	name character varying (500)
);
create table items (
	id serial primary key,
	description text,
	user_id integer references users(id),
	category_id integer references categories(id),
	state_id integer references states(id)
);
create table comments (
	id serial primary key,
	description text,
	item_id integer references items(id)
);
create table attaches (
	id serial primary key,
	bytes bytea,
	item_id integer references items(id)
);
 
 