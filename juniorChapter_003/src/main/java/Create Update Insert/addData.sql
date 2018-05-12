insert into roles(name) values ('Пользователь'), ('Модератор'), ('Админ');

insert into rules(description) values 
('Создавать заявку'), ('Изменять статус заявки'), 
('Изменять роли пользователей');

insert into roles_rules(role_id, rule_id) values 
(1,1), (2,1), (2,2), (3,1), (3,2), (3,3);

insert into users(login, password, role_id) values
('user1', '12345', 3), ('user2', '11111', 2), ('user3', '33333', 1), ('user4', '99999', 1);

insert into categories(name) values ('Категория 1'), ('Категория 2'), ('Категория 3');

insert into states(name) values ('Новая'), ('В работе'), ('Закрыта');

insert into items(description, user_id, category_id, state_id) values
('Заявка 1', 3, 2, 1), ('Заявка 2', 4, 2, 1);

insert into comments(description, item_id) values 
('Коментарий 1 к заявке 1', 1), ('Коментарий 2 к заявке 1', 1), ('Коментарий 1 к заявке 2', 2);